package org.smallbun.framework.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 21:02
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
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
	 * @param page
	 * @param queryWrapper
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
		return super.page(page, queryWrapper);
	}
}
