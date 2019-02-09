/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package org.smallbun.framework.auto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import static org.smallbun.framework.auto.SmallBunProperties.DEFAULT_PREFIX;


/**
 * SmallBun 特定的配置属性
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/25 20:43
 */
@Data
@ConfigurationProperties(value = DEFAULT_PREFIX)
public class SmallBunProperties {
	static final String DEFAULT_PREFIX = "smallbun";


	/**
	 * http
	 */
	@NestedConfigurationProperty
	private final Http http = new Http();
	/**
	 * 验证码
	 */
	@NestedConfigurationProperty
	private final DefaultKaptcha kaptcha = new DefaultKaptcha();
	/**
	 * 演示环境
	 */
	private final Demo demo = new Demo();

	private final Project project = new Project();

	public static class Project {
		/**
		 * 名称
		 */
		String name = SmallBunDefaults.Project.name;
		/**
		 * 版本
		 */
		String version = SmallBunDefaults.Project.version;
		/**
		 *
		 */
		String poweredBy = SmallBunDefaults.Project.poweredBy;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getPoweredBy() {
			return poweredBy;
		}

		public void setPoweredBy(String poweredBy) {
			this.poweredBy = poweredBy;
		}
	}

	public static class Http {
		/**
		 * http版本
		 */
		public enum Version {V_1_1, V_2_0}

		private final Cache cache = new Cache();

		/**
		 * HTTP 版本, 如果是 "V_1_1" (对于 HTTP/1.1) 或者 V_2_0 (对于 (HTTP/2)
		 */
		public Version version = SmallBunDefaults.Http.version;

		public Cache getCache() {
			return cache;
		}

		public Version getVersion() {
			return version;
		}

		public void setVersion(Version version) {
			this.version = version;
		}

		public static class Cache {

			private int timeToLiveInDays = SmallBunDefaults.Http.Cache.timeToLiveInDays;

			public int getTimeToLiveInDays() {
				return timeToLiveInDays;
			}

			public void setTimeToLiveInDays(int timeToLiveInDays) {
				this.timeToLiveInDays = timeToLiveInDays;
			}
		}
	}

	/**
	 * 验证码
	 */
	public static class DefaultKaptcha {
		String border = SmallBunDefaults.DefaultKaptcha.border;
		String imageWidth = SmallBunDefaults.DefaultKaptcha.imageWidth;
		String imageHeight = SmallBunDefaults.DefaultKaptcha.imageHeight;
		String textProducerFontColor = SmallBunDefaults.DefaultKaptcha.textProducerFontColor;
		String textProducerFontSize = SmallBunDefaults.DefaultKaptcha.textProducerFontSize;
		String textProducerImpl = SmallBunDefaults.DefaultKaptcha.textProducerImpl;
		String textProducerCharLength = SmallBunDefaults.DefaultKaptcha.textProducerCharLength;
		String textProducerFontNames = SmallBunDefaults.DefaultKaptcha.textProducerFontNames;

		public String getBorder() {
			return border;
		}

		public void setBorder(String border) {
			this.border = border;
		}

		public String getImageWidth() {
			return imageWidth;
		}

		public void setImageWidth(String imageWidth) {
			this.imageWidth = imageWidth;
		}

		public String getImageHeight() {
			return imageHeight;
		}

		public void setImageHeight(String imageHeight) {
			this.imageHeight = imageHeight;
		}

		public String getTextProducerFontColor() {
			return textProducerFontColor;
		}

		public void setTextProducerFontColor(String textProducerFontColor) {
			this.textProducerFontColor = textProducerFontColor;
		}

		public String getTextProducerFontSize() {
			return textProducerFontSize;
		}

		public void setTextProducerFontSize(String textProducerFontSize) {
			this.textProducerFontSize = textProducerFontSize;
		}

		public String getTextProducerImpl() {
			return textProducerImpl;
		}

		public void setTextProducerImpl(String textProducerImpl) {
			this.textProducerImpl = textProducerImpl;
		}

		public String getTextProducerCharLength() {
			return textProducerCharLength;
		}

		public void setTextProducerCharLength(String textProducerCharLength) {
			this.textProducerCharLength = textProducerCharLength;
		}

		public String getTextProducerFontNames() {
			return textProducerFontNames;
		}

		public void setTextProducerFontNames(String textProducerFontNames) {
			this.textProducerFontNames = textProducerFontNames;
		}
	}

	/**
	 * 演示环境
	 */
	public static class Demo {
		/**
		 * 是否开启
		 */
		boolean open = SmallBunDefaults.Demo.OPEN;
		/**
		 * 用户名
		 */
		String username;
		/**
		 * 密码
		 */
		String password;

		public boolean isOpen() {
			return open;
		}

		public void setOpen(boolean open) {
			this.open = open;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
