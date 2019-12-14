/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.smallbun.scaffold.manage.service.impl;

import cn.smallbun.scaffold.framework.mybatis.service.BaseServiceImpl;
import cn.smallbun.scaffold.framework.redis.RedisClient;
import cn.smallbun.scaffold.manage.entity.SysAuthorizeItemEntity;
import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import cn.smallbun.scaffold.manage.mapper.SysAuthorizeItemMapper;
import cn.smallbun.scaffold.manage.service.ISysAuthorizeItemService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


/**
 * <p>
 * 系统操作权限表 服务实现类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-09
 */
@Service
@CacheConfig(cacheNames = {SysAuthorizeItemServiceImpl.CACHE_NAME})
public class SysAuthorizeItemServiceImpl extends BaseServiceImpl<SysAuthorizeItemMapper, SysAuthorizeItemEntity>
		implements ISysAuthorizeItemService {
	/**
	 * cache name
	 */
	static final String CACHE_NAME = "authorize::item";

	public SysAuthorizeItemServiceImpl(RedisClient redisClient) {
		this.redis = redisClient;
	}

	/**
	 * 查询list
	 * @return {@link List<SysDictItemEntity>}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysAuthorizeItemEntity> list() {
		if (redis.hasKey(CACHE_NAME)) {
			return (List<SysAuthorizeItemEntity>) redis.get(CACHE_NAME);
		}
		List<SysAuthorizeItemEntity> list = super.list();
		redis.set(CACHE_NAME, list);
		return list;
	}

	/**
	 * 通过角色获取权限项
	 * @param ids ids
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	@Override
	public List<SysAuthorizeItemEntity> getAuthorizeItemsByRole(List<String> ids) {
		return baseMapper.getAuthorizeItemsByRole(ids);
	}

	/**
	 * 查询所有权限项目
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	@Override
	public List<SysAuthorizeItemEntity> getAuthorizeItems() {
		return baseMapper.getAuthorizeItems();
	}

	/**
	 * 初始化操作
	 */
	@Override
	public void initialize() {
		logger.info("Load authority data into the cache start");
		redis.del(CACHE_NAME);
		this.list();
		logger.info("Load authority data into the cache end");
	}

	/**
	 * 根据ID查询数据
	 * @param id id
	 * @return {@link SysDictItemEntity}
	 */
	@Override
	public SysAuthorizeItemEntity getById(Serializable id) {
		List<SysAuthorizeItemEntity> list = this.list().stream().filter(i -> i.getId().equals(id.toString()))
				.collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}


	/**
	 * 重写save，新增值以后放入redis缓存
	 * @param entity {@link SysDictItemEntity}
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(SysAuthorizeItemEntity entity) {
		boolean save = super.save(entity);
		if (save) {
			List<SysAuthorizeItemEntity> list = this.list();
			list.add(entity);
			redis.set(CACHE_NAME, list);
			return true;
		}
		return false;
	}

	/**
	 * 更具ID更新
	 * @param entity {@link SysDictItemEntity}
	 * @return boolean
	 */
	@Override
	public boolean updateById(SysAuthorizeItemEntity entity) {
		boolean update = super.updateById(entity);
		if (update) {
			List<SysAuthorizeItemEntity> list = this.list();
			//获取数据
			List<SysAuthorizeItemEntity> values = list.stream().filter(i -> !i.getId().equals(entity.getId()))
					.collect(Collectors.toList());
			//从数据库根据ID查询放入缓存
			values.add(super.getById(entity.getId()));
			redis.set(CACHE_NAME, values);
			return true;
		}
		return false;
	}

	/**
	 * 根据IDs移除数据
	 * @param idList ids
	 * @return boolean
	 */
	@Override
	@SuppressWarnings({"DuplicatedCode"})
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		boolean remove = super.removeByIds(idList);
		//删除数据
		if (remove) {
			List<SysAuthorizeItemEntity> list = this.list();
			List<SysAuthorizeItemEntity> values = Lists.newArrayList();
			idList.forEach(j -> {
				values.clear();
				values.addAll(list.stream().filter(i -> !i.getId().equals(j.toString())).collect(Collectors.toList()));
			});
			redis.set(CACHE_NAME, values);
			return true;
		}
		return false;
	}

	/**
	 * 根据权限类型删除数据
	 * @param idList idList
	 * @return boolean
	 */
	@Override
	public boolean removeByTypes(Collection<? extends Serializable> idList) {
		AtomicBoolean flag = new AtomicBoolean(false);
		idList.forEach(i -> {
			//循环根据字典类型删除字典值数据
			flag.set(remove(new LambdaQueryWrapper<SysAuthorizeItemEntity>()
					.eq(SysAuthorizeItemEntity::getType, i.toString())));
			//删除成功,对缓存进行操作
			if (flag.get()) {
				List<SysAuthorizeItemEntity> list = this.list();
				idList.forEach(j -> redis.set(CACHE_NAME,
						list.stream().filter(ii -> !ii.getAuthorize().equals(j.toString()))
								.collect(Collectors.toList())));
			}
		});
		return flag.get();
	}

	/**
	 * RedisClient
	 */
	private final RedisClient redis;
}
