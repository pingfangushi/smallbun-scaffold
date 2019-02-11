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

package org.smallbun.framework.permission.constant;

/**
 * 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 16:49
 */
public enum DataScopeConstant {
	/**
	 * 所有数据
	 */
	DATA_SCOPE_ALL("1", "所有数据"),
	/**
	 * 所在公司及以下数据
	 */
	DATA_SCOPE_COMPANY_AND_CHILD("2", "所在公司及以下数据"),
	/**
	 *所在公司数据
	 */
	DATA_SCOPE_COMPANY("3", "所在公司数据"),
	/**
	 * 所在部门及以下数据
	 */
	DATA_SCOPE_ORG_AND_CHILD("4", "所在部门及以下数据"),
	/**
	 * 所在部门数据
	 */
	DATA_SCOPE_ORG("5", "所在部门数据"),
	/**
	 * 仅本人数据
	 */
	DATA_SCOPE_SELF("8", "仅本人数据"),
	/**
	 * 按明细设置
	 */
	DATA_SCOPE_CUSTOM("9", "按明细设置");

	DataScopeConstant(String number, String describe) {
		this.number = number;
		this.describe = describe;
	}

	private String number;
	private String describe;

	public String getNumber() {
		return number;
	}

	public String getDescribe() {
		return describe;
	}
}
