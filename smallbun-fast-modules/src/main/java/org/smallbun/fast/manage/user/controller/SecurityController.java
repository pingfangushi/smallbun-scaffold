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
	@Autowired
	public SecurityController(SecurityService securityService) {
		this.securityService = securityService;
	}

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
	private final SecurityService securityService;
}
