/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.smallbun.scaffold.manage.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * Default
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/21 21:09
 */
public enum DictDefault implements IEnum<String> {
	/**
	 * 是
	 */
	YES("0", "是"),
	/**
	 * 不是
	 */
	NO("1", "不是");

	private String code;
	private String desc;

	DictDefault(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 枚举数据库存储值
	 */
	@Override
	public String getValue() {
		return this.code;
	}

	public static DictDefault getStatus(String code) {
		DictDefault[] values = values();
		for (DictDefault status : values) {
			if (String.valueOf(status.getCode()).equals(code)) {
				return status;
			}
		}
		return null;
	}
}
