/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.framework.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.baomidou.mybatisplus.core.toolkit.TableInfoHelper.getAllFields;
import static com.baomidou.mybatisplus.core.toolkit.TableInfoHelper.getTableInfo;
import static org.smallbun.framework.toolkit.ReflectionUtil.getFieldAll;

/**
 * 继承mybatis plus 封装的service impl
 *  增加适合自己业务方案的逻辑
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 21:02
 */
@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T>
		implements BaseService<T> {
	/**
	 * 主键ID
	 */
	public static final String ID = "id";


	/**
	 * mybatis plus bean 转 map
	 * @param org
	 * @return
	 */
	protected Map<String, Object> beanToMap(T org) {
		//构建查询条件
		Map<String, Object> map = Maps.newHashMap();
		TableInfo tableInfo = getTableInfo(org.getClass());
		tableInfo.getFieldList().forEach(u -> getAllFields(org.getClass()).forEach(s -> {
			try {
				//暴力访问
				s.setAccessible(true);
				//主键
				if (tableInfo.getKeyProperty().equals(s.getName())) {
					map.put(tableInfo.getKeyColumn(), s.get(org));
				}
				//其余字段
				if (u.getProperty().equals(s.getName())) {
					map.put(u.getColumn(), s.get(org));
				}

			} catch (IllegalAccessException e) { e.printStackTrace(); }
		}));
		return map;
	}

	/**
	 * mybatis plus bean 转 map,排除ID字段
	 * @param org
	 * @return
	 */
	private Map<String, Object> beanToMapExcludeId(T org) {
		//构建查询条件
		Map<String, Object> map = Maps.newHashMap();
		TableInfo tableInfo = getTableInfo(org.getClass());
		tableInfo.getFieldList().forEach(u -> getAllFields(org.getClass()).forEach(s -> {
			try {
				//暴力访问
				s.setAccessible(true);
				//其余字段
				if (u.getProperty().equals(s.getName())) {
					map.put(u.getColumn(), s.get(org));
				}

			} catch (IllegalAccessException e) { e.printStackTrace(); }
		}));
		return map;
	}


	/**
	 * 唯一记录
	 * @param t t
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean unique(T t) {
		//构建查询条件,将实体转换为map ，不包含ID
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>().allEq(beanToMapExcludeId(t), false);
		//查询
		List<T> ts = baseMapper.selectList(queryWrapper);
		//表示为不是唯一
		boolean flag = false;
		//如果没有记录，唯一
		if (CollectionUtils.isEmpty(ts)) {
			flag = true;
		}
		//如果有记录
		else {
			//循环字段
			for (T u : ts) {
				for (Field f : getFieldAll(u)) {
					//获取ID字段的值，和当前ID字段值进行对比，如果ID相同，可以通过，如果不同，flag任为false
					if (ID.equals(f.getName())) {
						try {
							f.setAccessible(true);
							if (!StringUtils.isEmpty(t.getId())) {
								if (t.getId().equals(f.get(u))) {
									flag = true;
								}
							}
						} catch (IllegalAccessException e) {
							log.error("method uniqueResult Exception{}", (Object) e.getStackTrace());
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 数据权限进行过滤分页
	 *
	 * @param page         {@link IPage}
	 * @param queryWrapper {@link Wrapper}
	 * @author SanLi
	 * Created by 2689170096@qq.com on 2018/11/14 21:01
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IPage<T> selectDataScopePage(IPage<T> page, Wrapper<T> queryWrapper) {
		return baseMapper.selectDataScopePage(page, queryWrapper);
	}
}
