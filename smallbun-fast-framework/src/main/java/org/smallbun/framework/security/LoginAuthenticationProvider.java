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

package org.smallbun.framework.security;

import org.smallbun.framework.constant.SystemConstant;
import org.smallbun.framework.toolkit.RsaUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

	public LoginAuthenticationProvider(UserDetailsService userDetailsService) {
		super();
		// 这个地方一定要对userDetailsService赋值，不然userDetailsService是null
		setUserDetailsService(userDetailsService);
	}


	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		HttpSession session = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		//上次生成的秘钥
		@SuppressWarnings("unchecked") HashMap<String, String> pDCurrentRSAKey = (HashMap<String, String>) session
				.getAttribute(SystemConstant.PD_CURRENT_RSA_KEY);
		//清空秘钥
		session.removeAttribute(SystemConstant.PD_CURRENT_RSA_KEY);
		//如果密码为空
		if (authentication.getCredentials() == null) {
			logger.debug("身份验证失败:未提供凭据");
			throw new BadCredentialsException(messages.getMessage("摘要用户细节认证提供者, 不好的信用史", "身份验证失败:未提供凭据"));
		}
		//自定义验证
		String presentedPassword = authentication.getCredentials().toString();
		try {
			String decryptPassword = RsaUtil
					.decrypt(RsaUtil.loadPrivateKey(pDCurrentRSAKey.get(SystemConstant.PRIVATE_KEY)),
							RsaUtil.strToBase64(presentedPassword));
			if (!new BCryptPasswordEncoder().matches(decryptPassword, userDetails.getPassword())) {
				logger.debug("身份验证失败：密码与存储的值不匹配");
				throw new BadCredentialsException(messages.getMessage("摘要用户细节认证提供者, 不好的信用史", "身份验证失败：密码与存储的值不匹配"));
			}
		} catch (Exception e) {
			throw new BadCredentialsException(messages.getMessage(e.getLocalizedMessage(), e.getMessage()));
		}

	}
}
