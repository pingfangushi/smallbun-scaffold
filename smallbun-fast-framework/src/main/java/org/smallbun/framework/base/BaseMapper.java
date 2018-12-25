package org.smallbun.framework.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

/**
 * 自定义Mapper
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 20:54
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {
	/**
	 * 分页查询
	 * @param page {@link IPage}
	 * @param queryWrapper {@link Wrapper}
	 * @return {@link IPage }
	 */
	IPage<T> selectDataScopePage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

	/**
	 * <p>
	 * 根据 entity 条件，查询全部记录（并翻页）
	 * </p>
	 *  @param page         分页查询条件（可以为 RowBounds.DEFAULT）
	 * @param queryWrapper 实体对象封装操作类（可以为 null）
	 * @return {@link IPage}
	 */
	@Override
	IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
