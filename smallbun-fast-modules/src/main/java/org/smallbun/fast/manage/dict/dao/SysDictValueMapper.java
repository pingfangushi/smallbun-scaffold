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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.smallbun.fast.manage.dict.entity.SysDictValueEntity;
import org.smallbun.fast.manage.dict.vo.SysDictValueVO;
import org.smallbun.framework.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统字典数据 Mapper 接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Mapper
public interface SysDictValueMapper extends BaseMapper<SysDictValueEntity> {
	/**
	 * 分页查询
	 * @param page {@link Page<SysDictValueEntity>}
	 * @param vo {@link SysDictValueVO}
	 * @return {@link Page<SysDictValueEntity>}
	 */
	IPage<SysDictValueEntity> page(Page<SysDictValueEntity> page, @Param("p") SysDictValueVO vo);

	/**
	 * 根据类别获取字典值
	 * @param typeId 字典类型
	 * @return {@link SysDictValueEntity}
	 */
	@Select("select * from sys_dict_value where dict_type=#{typeId}")
	List<SysDictValueEntity> findByTypeId(String typeId);

	/**
	 * 根据类型编码查询字典值
	 * @param typeCode
	 * @return
	 */
	List<SysDictValueEntity> findByTypeCode(String typeCode);

	/**
	 * 根据字典类型和字典值查询字典名称
	 * @param typeCode
	 * @param dictValue
	 * @return
	 */
	String findLabelByTypeCodeAndValue(@Param("typeCode") String typeCode,
			@Param("dictValue") String dictValue);
}
