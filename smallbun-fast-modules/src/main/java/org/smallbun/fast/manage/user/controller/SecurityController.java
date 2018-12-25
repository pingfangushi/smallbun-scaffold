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

package org.smallbun.fast.manage.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.smallbun.fast.manage.core.service.SecurityService;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Security Controller
 * <p>
 * Provide access to verification fast, private key and other interfaces
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/5/1
 */
@Slf4j
@RestController
public class SecurityController extends BaseController {
	/**
	 * get public key
	 *
	 * @return Result Objects
	 */
	@RequestMapping(value = "key")
	public AjaxResult publicKey() {
		return AjaxResult.builder().result(securityService.publicKey()).build();
	}


	/**
	 * get captcha
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping(value = "captcha")
	public void captchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		securityService.captchaImage(request, response);
	}

	/**
	 * 注入安全业务逻辑接口
	 */
	@Autowired
	private SecurityService securityService;
}
