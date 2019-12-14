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

package cn.smallbun.scaffold.manage.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 当前用户
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/10/15 14:39
 */
@Data
public class CurrentUserDTO implements Serializable {
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 用户ID
	 */
	@JSONField(name = "userid")
	private String userId;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 个性签名
	 */
	private String signature;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 归属组织
	 */
	@JSONField(name = "group")
	private String groupInfo;
	/**
	 * 通知计数
	 */
	private Integer notifyCount;
	/**
	 * 未读计数
	 */
	private Integer unreadCount;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 归属区域
	 */
	private GeographicBean geographic;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 手机
	 */
	private String phone;
	/**
	 * 标签
	 */
	private List<TagsBean> tags;

	@Data
	public static class GeographicBean {
		/**
		 * province : {"label":"浙江省","key":"330000"}
		 * city : {"label":"杭州市","key":"330100"}
		 */

		private ProvinceBean province;
		private CityBean city;

		@Data
		public static class ProvinceBean {
			/**
			 * label : 浙江省
			 * key : 330000
			 */

			private String label;
			private String key;

		}

		@Data
		public static class CityBean {
			/**
			 * label : 杭州市
			 * key : 330100
			 */

			private String label;
			private String key;
		}
	}

	@Data
	public static class TagsBean {
		/**
		 * key : 0
		 * label : 很有想法的
		 */

		private String key;
		private String label;
	}
}
