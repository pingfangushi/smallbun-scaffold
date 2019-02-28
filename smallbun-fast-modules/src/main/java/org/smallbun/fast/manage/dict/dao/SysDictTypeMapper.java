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

package org.smallbun.fast.manage.dict.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.smallbun.fast.manage.dict.entity.SysDictTypeEntity;
import org.smallbun.framework.base.BaseMapper;
import org.smallbun.framework.permission.annotation.DataScopeFilter;

/**
 *  系统字典类型 Mapper 接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictTypeEntity> {
	/**
	 * 分页查询，数据权限过滤
	 * @param page
	 * @param queryWrapper
	 * @return
	 */
	@Override
	@DataScopeFilter
	@SuppressWarnings("MybatisMapperMethodInspection")
	IPage<SysDictTypeEntity> selectPage(IPage<SysDictTypeEntity> page,
			@Param(Constants.WRAPPER) Wrapper<SysDictTypeEntity> queryWrapper);
}
