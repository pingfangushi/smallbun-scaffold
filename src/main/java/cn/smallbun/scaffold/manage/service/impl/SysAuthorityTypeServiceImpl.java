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
import cn.smallbun.scaffold.manage.entity.SysAuthorityTypeEntity;
import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import cn.smallbun.scaffold.manage.mapper.SysAuthorityTypeMapper;
import cn.smallbun.scaffold.manage.service.ISysAuthorityTypeService;
import cn.smallbun.scaffold.manage.service.ISysAuthorizeItemService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统业务信息维护表 服务实现类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-07
 */
@Service
@CacheConfig(cacheNames = {SysAuthorityTypeServiceImpl.CACHE_NAME})
public class SysAuthorityTypeServiceImpl extends BaseServiceImpl<SysAuthorityTypeMapper, SysAuthorityTypeEntity>
		implements ISysAuthorityTypeService {
	/**
	 * cache name
	 */
	static final String CACHE_NAME = "authorize::type";

	public SysAuthorityTypeServiceImpl(RedisClient redisClient, ISysAuthorizeItemService itemService) {
		this.redis = redisClient;
		this.itemService = itemService;
	}

	/**
	 * 查询list
	 * @return {@link List <SysAuthorityEntity>}
	 */
	@Override
	public List<SysAuthorityTypeEntity> getAuthoritys() {
		return baseMapper.getAuthoritys();
	}

	/**
	 * 查询list
	 * @return {@link List<  SysDictItemEntity  >}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysAuthorityTypeEntity> list() {
		if (redis.hasKey(CACHE_NAME)) {
			return (List<SysAuthorityTypeEntity>) redis.get(CACHE_NAME);
		}
		List<SysAuthorityTypeEntity> list = super.list();
		redis.set(CACHE_NAME, list);
		return list;
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
	public SysAuthorityTypeEntity getById(Serializable id) {
		List<SysAuthorityTypeEntity> list = this.list().stream().filter(i -> i.getId().equals(id.toString()))
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
	public boolean save(SysAuthorityTypeEntity entity) {
		boolean save = super.save(entity);
		if (save) {
			List<SysAuthorityTypeEntity> list = this.list();
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
	public boolean updateById(SysAuthorityTypeEntity entity) {
		boolean update = super.updateById(entity);
		if (update) {
			List<SysAuthorityTypeEntity> list = this.list();
			//获取数据
			List<SysAuthorityTypeEntity> values = list.stream().filter(i -> !i.getId().equals(entity.getId()))
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
			List<SysAuthorityTypeEntity> list = this.list();
			List<SysAuthorityTypeEntity> values = Lists.newArrayList();
			idList.forEach(j -> {
				values.clear();
				values.addAll(list.stream().filter(i -> !i.getId().equals(j.toString())).collect(Collectors.toList()));
			});
			redis.set(CACHE_NAME, values);
			itemService.removeByTypes(idList);
			return true;
		}
		return false;
	}

	/**
	 * redis client
	 */
	private final RedisClient redis;
	/**
	 * ISysAuthorizeItemService
	 */
	private final ISysAuthorizeItemService itemService;
}
