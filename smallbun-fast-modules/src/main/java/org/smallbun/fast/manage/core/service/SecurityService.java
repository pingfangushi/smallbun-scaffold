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

package org.smallbun.fast.manage.core.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 安全业务逻辑接口
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/5/2
 */
public interface SecurityService {
	/**
	 * get public key
	 *
	 * @return public key
	 */
	String publicKey();

	/**
	 * 绘制验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	void captchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
