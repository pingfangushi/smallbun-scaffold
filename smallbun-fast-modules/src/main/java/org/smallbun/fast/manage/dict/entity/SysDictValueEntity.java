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

package org.smallbun.fast.manage.dict.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.common.entity.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 系统字典数据
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict_value")
@JsonIgnoreProperties(value = {"handler"})
public class SysDictValueEntity extends DataEntity<Long> {


	/**
	 * 所属字典类型(关联sys_dict_type表)
	 */
	@TableField(exist = false)
	private SysDictTypeEntity sysDictType;
	/**
	 * 所属字典类型
	 */
	@NotNull(message = "字典类型不能为空")
	private String dictType;
	/**
	 * 标签
	 */
	@NotNull(message = "字典标签不能为空")
	private String dictLabel;

	/**
	 * 值
	 */
	@NotNull(message = "字典值不能为空")
	private String dictValue;

	/**
	 * 排序
	 */
	private Integer sort;
}
