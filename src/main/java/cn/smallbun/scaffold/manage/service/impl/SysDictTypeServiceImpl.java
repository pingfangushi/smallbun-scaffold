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

import cn.smallbun.scaffold.framework.context.ApplicationContextHelp;
import cn.smallbun.scaffold.framework.mybatis.service.BaseServiceImpl;
import cn.smallbun.scaffold.framework.redis.RedisClient;
import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import cn.smallbun.scaffold.manage.entity.SysDictTypeEntity;
import cn.smallbun.scaffold.manage.enums.DictDefault;
import cn.smallbun.scaffold.manage.enums.DictStatus;
import cn.smallbun.scaffold.manage.mapper.SysDictTypeMapper;
import cn.smallbun.scaffold.manage.service.ISysDictItemService;
import cn.smallbun.scaffold.manage.service.ISysDictTypeService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统字典类型 服务实现类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeMapper, SysDictTypeEntity>
		implements ISysDictTypeService {
	/**
	 * cache name
	 */
	private static final String CACHE_NAME = "dict::type";

	/**
	 * 根据ID查询数据
	 * @param id id
	 * @return {@link SysDictTypeEntity}
	 */
	@Override
	public SysDictTypeEntity getById(Serializable id) {
		List<SysDictTypeEntity> list = list().stream().filter(i -> i.getId().equals(id.toString()))
				.collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}


	/**
	 * 重写save，新增值以后放入redis缓存
	 * @param entity {@link SysDictTypeEntity}
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(SysDictTypeEntity entity) {
		boolean save = super.save(entity);
		if (save) {
			List<SysDictTypeEntity> list = ApplicationContextHelp.getBean(ISysDictTypeService.class).list();
			list.add(entity);
			redis.set(CACHE_NAME, list);
			return true;
		}
		return false;
	}

	/**
	 * 根据ID更新数据
	 * @param entity {@link SysDictTypeEntity}
	 * @return boolean
	 */
	@Override
	public boolean updateById(SysDictTypeEntity entity) {
		boolean update = super.updateById(entity);
		if (update) {
			List<SysDictTypeEntity> list = this.list();
			//移除此数据
			List<SysDictTypeEntity> types = list.stream().filter(i -> !i.getId().equals(entity.getId()))
					.collect(Collectors.toList());
			//从数据库查询出来放入缓存
			types.add(super.getById(entity.getId()));
			redis.set(CACHE_NAME, types);
			return true;
		}
		return false;
	}

	/**
	 * 查询list
	 * @return {@link List<SysDictItemEntity>}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysDictTypeEntity> list() {
		if (redis.hasKey(CACHE_NAME)) {
			return (List<SysDictTypeEntity>) redis.get(CACHE_NAME);
		}
		List<SysDictTypeEntity> list = super.list();
		redis.set(CACHE_NAME, list);
		return list;
	}

	/**
	 * 加载字典类型信息到redis
	 */
	@Override
	public void initialize() {
		logger.info("Load dict type data into the cache start");
		redis.del(CACHE_NAME);
		this.list();
		logger.info("Load dict type data into the cache end");
	}

	/**
	 * 查询全部的字典，主要用于前端字典值展示,通过type获取到值，循环值渲染组件
	 * @return {@link List}
	 */
	@Override
	public List<Dict> dict() {
		//查询数据
		List<SysDictTypeEntity> typeList = ApplicationContextHelp.getBean(ISysDictTypeService.class).list();
		List<SysDictItemEntity> valueList = ApplicationContextHelp.getBean(ISysDictItemService.class).list();
		//处理数据，只选择启用的
		typeList = typeList.stream().filter(i -> DictStatus.ENABLE.equals(i.getStatus())).collect(Collectors.toList());
		List<Dict> list = Lists.newArrayList();
		for (SysDictTypeEntity i : typeList) {
			Dict dict = new Dict();
			//类型编码
			dict.setType(i.getCode());
			// 字典项
			List<Item> items = Lists.newArrayList();
			for (SysDictItemEntity j : valueList) {
				if (i.getId().equals(j.getType())) {
					// 设置字典项，颜色，标签，值
					Item item = new Item().setColor(j.getColor()).setLabel(j.getLabel()).setValue(j.getValue());
					items.add(item);
					// 设置默认值
					if (DictDefault.YES.equals(j.getIsDefault())) {
						dict.setDefaultValue(j.getValue());
					}
				}
			}
			// 设置字典项
			dict.setItems(items);
			// 添加字典
			list.add(dict);
		}
		return list;
	}

	/**
	 * 根据ID更新状态
	 * @param id id
	 * @param status 状态
	 * @return 是否成功
	 */
	@Override
	public boolean updateStatusById(String id, DictStatus status) {
		SysDictTypeEntity type = new SysDictTypeEntity();
		type.setStatus(status);
		type.setId(id);
		if (this.updateById(type)) {
			List<SysDictTypeEntity> list = this.list();
			list.stream().filter(i -> i.getId().equals(id)).forEach(i -> i.setStatus(status));
			ApplicationContextHelp.getBean(RedisClient.class).set(CACHE_NAME, list);
			return true;
		}
		return false;
	}


	/**
	 * 根据id集合删除数据
	 * @param idList ids
	 * @return boolean
	 */
	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		boolean remove = super.removeByIds(idList);
		//删除数据
		if (remove) {
			List<SysDictTypeEntity> list = this.list();
			List<SysDictTypeEntity> types = this.list();
			idList.forEach(j -> {
				types.clear();
				types.addAll(list.stream().filter(i -> !i.getId().equals(j.toString())).collect(Collectors.toList()));
			});
			redis.set(CACHE_NAME, types);
			//删除字典值数据
			return valueService.removeByTypes(idList);
		}
		return false;
	}

	/**
	 * 注入字典值service
	 */
	private final ISysDictItemService valueService;
	/**
	 * RedisClient
	 */
	private final RedisClient redis;

	public SysDictTypeServiceImpl(ISysDictItemService valueService, RedisClient redisClient) {
		this.valueService = valueService;
		this.redis = redisClient;
	}
}
