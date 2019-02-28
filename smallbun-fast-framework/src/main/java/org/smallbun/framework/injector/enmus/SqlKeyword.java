/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.smallbun.framework.injector.enmus;


/**
 *
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2019/2/28 -11:49
 */
public enum SqlKeyword {

	/**
	 * 左连接
	 */
	LEFT_JOIN("LEFT JOIN"),

	/**
	 * 右连接
	 */
	RIGHT_JOIN("RIGHT JOIN");

	private final String keyword;

	SqlKeyword(final String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}}