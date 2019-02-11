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

package org.smallbun.fast.manage.org.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.framework.annotation.DictValue;

import java.io.Serializable;

/**
 * VO
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/23 23:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOrgVO extends SysOrgEntity {
	/**
	 * 这个字段为id，为了防止前端两个tree进行节点选择错误，而进行区分
	 */
	private Serializable orgId;
	/**
	 * 机构类型(字典值)
	 */
	@DictValue(valueField = "orgType", typeCode = "ORG_TYPE")
	private String orgTypeName;
	/**
	 * 机构等级（字典值）
	 */
	@DictValue(valueField = "grade", typeCode = "ORG_LEVEL")
	private String gradeName;
	/**
	 * 是否可用
	 */
	@DictValue(valueField = "useable", typeCode = "WHETHER_USEABLE")
	private String useableName;

	public Serializable getOrgId() {
		return getId();
	}
}
