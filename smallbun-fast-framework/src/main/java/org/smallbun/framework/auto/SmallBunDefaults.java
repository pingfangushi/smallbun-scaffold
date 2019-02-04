
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


/**
 * SmallBun快速开发平台默认配置
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on 2018/5/11 13:24
 */
public interface SmallBunDefaults {


	/**
	 * HTTP
	 */
	interface Http {

		SmallBunProperties.Http.Version version = SmallBunProperties.Http.Version.V_1_1;

		interface Cache {
			/**
			 * 4 年 (包括闰日)
			 */
			int timeToLiveInDays = 1461;
		}
	}

	/**
	 * 验证码
	 */
	interface DefaultKaptcha {
		/**
		 * 是否有边框
		 */
		String border = "no";
		/**
		 * 图像宽度
		 */
		String imageWidth = "240";
		/**
		 * 图像高度
		 */
		String imageHeight = "80";
		/**
		 * 文本生产者字体颜色
		 */
		String textProducerFontColor = "black";
		/**
		 * 文字制作者字体大小
		 */
		String textProducerFontSize = "60";
		/**
		 * 文字制片人实现
		 */
		String textProducerImpl = "com.google.code.kaptcha.impl.ShadowGimpy";
		/**
		 * 文本生产者字符长度
		 */
		String textProducerCharLength = "5";
		/**
		 * 文本生产者字体名称
		 */
		String textProducerFontNames = "宋体,楷体,微软雅黑";
	}

	/**
	 * 演示环境
	 */
	interface Demo {
		boolean OPEN = false;
	}
}

