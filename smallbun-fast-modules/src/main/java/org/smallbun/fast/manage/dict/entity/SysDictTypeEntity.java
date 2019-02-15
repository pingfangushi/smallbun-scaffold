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

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.smallbun.fast.common.entity.DataEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 系统字典类型
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Getter
@Setter
@TableName("sys_dict_type")
public class SysDictTypeEntity extends DataEntity<Long> {

	/**
	 * 类型名称
	 */
	@NotBlank(message = "类型名称不能为空")
	@TableField(condition = SqlCondition.LIKE)
	private String typeName;
	/**
	 * 类型编码
	 */
	@NotBlank(message = "类型编码不能为空")
	private String typeCode;
	/**
	 * 字典值
	 */
	@TableField(exist = false)
	private List<SysDictValueEntity> dictValue;

}
