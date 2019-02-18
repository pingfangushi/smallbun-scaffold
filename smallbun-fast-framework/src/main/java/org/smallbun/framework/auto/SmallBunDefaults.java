
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
	 * 项目信息
	 */
	interface Project {
		/**
		 * 名称
		 */
		String name = "SmallBun-企业级脚手架";
		/**
		 * 版本
		 */
		String version = "v1.0.0 Alpha";
		/**
		 *
		 */
		String poweredBy = "http://www.smallbun.org";
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

	/***
	 * 用户相关
	 */
	interface User {
		/**
		 * 注册默认密码
		 */
		String REGISTER_DEFAULT_PASSWORD = "$2a$10$SkPLa0RwRFrjyv1YterZtucAtjrPgYXi6zGXbjmEpolt10AcKZBqW";
	}
}

