package org.smallbun.framework.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 21:02
 */
@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
	private static final String ID = "id";

	/**
	 * 唯一验证公共返回
	 * 如果存在id通过
	 * @param id id  {@link Serializable}
	 * @param queryWrapper {@link Wrapper}
	 * @return {@link AjaxResult}
	 */
	@Transactional(rollbackFor = Exception.class)
	protected AjaxResult uniqueResult(Serializable id, Wrapper<T> queryWrapper) {
		T one = baseMapper.selectOne(queryWrapper);
		boolean flag = false;
		//如果没有记录，可以添加
		if (StringUtils.isEmpty(one)) {
			flag = true;
		}
		//如果有记录
		else {
			List<Field> fieldList = Lists.newArrayList();
			Class aClass = one.getClass();
			//当父类为null的时候说明到达了最上层的父类(Object类).
			while (aClass != null) {
				fieldList.addAll(Arrays.asList(aClass.getDeclaredFields()));
				//得到父类,然后赋给自己
				aClass = aClass.getSuperclass();
			}
			//循环字段
			for (Field f : fieldList) {
				//获取ID字段的值，和当前ID字段值进行对比，如果ID相同，可以通过，如果不同，flag任为false
				if (ID.equals(f.getName())) {
					try {
						f.setAccessible(true);
						if (id.equals(f.get(one))) {
							flag = true;
						}
					} catch (IllegalAccessException e) {
						log.error("method uniqueResult Exception{}", (Object) e.getStackTrace());
					}
				}
			}
		}
		return AjaxResult.builder().result(flag).build();
	}

	/**
	 * 数据权限进行过滤分页
	 * @author SanLi
	 * Created by 2689170096@qq.com on 2018/11/14 21:01
	 * @param page {@link IPage}
	 * @param queryWrapper {@link Wrapper}
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IPage<T> selectDataScopePage(IPage<T> page, Wrapper<T> queryWrapper) {
		return baseMapper.selectDataScopePage(page, queryWrapper);
	}

	/**
	 *
	 * @param page {@link IPage}
	 * @param queryWrapper  {@link Wrapper}
	 * @return {@link IPage}
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
		return super.page(page, queryWrapper);
	}
}
