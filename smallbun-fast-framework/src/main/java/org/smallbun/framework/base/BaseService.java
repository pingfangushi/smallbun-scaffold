package org.smallbun.framework.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 自定义业务逻辑层
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 21:00
 */
public interface BaseService<T> extends IService<T> {
	/**
	 * 数据权限进行过滤分页
	 * @param page {@link IPage}
	 * @param queryWrapper {@link Wrapper}
	 * @return {@link IPage}
	 */
	IPage<T> selectDataScopePage(IPage<T> page, Wrapper<T> queryWrapper);

	/**
	 * 唯一记录
	 * @param t
	 * @return
	 */
	Boolean unique(T t);
}
