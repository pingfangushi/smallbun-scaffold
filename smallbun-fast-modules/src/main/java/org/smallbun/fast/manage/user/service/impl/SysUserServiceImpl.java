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

package org.smallbun.fast.manage.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.smallbun.fast.manage.user.dao.SysUserMapper;
import org.smallbun.fast.manage.user.details.LoginUserDetails;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.fast.manage.user.service.SysUserService;
import org.smallbun.fast.manage.user.vo.UserDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * <br>
 * Created by 2689170096@qq.com on  2018/7/27 8:38
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {


	/**
	 * 根据用户名查询用户
	 * @param username username
	 * @return SysUserEntity
	 */
	@Override
	public SysUserEntity findByUsername(String username) {
		return baseMapper.findByUsername(username);
	}

	/**
	 * 从会话注册表中获取用户
	 * @return {@link List < LoginUserDetails >}
	 */
	@Override
	public List<UserDetailsVO> getUsersFromSessionRegistry() {
		List<UserDetailsVO> list = Lists.newArrayList();
		for (Object principal : sessionRegistry.getAllPrincipals()) {
			if (principal instanceof LoginUserDetails) {
				List<SessionInformation> allSessions = sessionRegistry.getAllSessions(principal, false);
				for (SessionInformation information : allSessions) {
					//会话ID
					information.getSessionId();
					System.out.println("====" + information.getSessionId());
					//最后操作时间
					information.getLastRequest();
				}
			}
		}
		//获取httpServletRequest对象
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		//获取SecurityContextImpl
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		//获取WebAuthenticationDetails
		WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl.getAuthentication()
				.getDetails();
		//根据sessionID 获取Session的基本信息，如最后一次访问时间，是否过期等
		SessionInformation sessionInformation = sessionRegistry.getSessionInformation(details.getSessionId());
		System.out.println("----" + details.getSessionId());
		return list;
	}

	/**
	 * 下线所有用户，除下达任务的人除外
	 * @param id
	 */
	@Override
	public void expireUserSessions(String id) {
		for (Object principal : sessionRegistry.getAllPrincipals()) {
			if (principal instanceof LoginUserDetails) {
				LoginUserDetails userDetails = (LoginUserDetails) principal;
				if (userDetails.getSysUser().getId().equals(id)) {
					for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
						information.expireNow();
					}
				}
			}
		}
	}

	@Autowired
	private SessionRegistry sessionRegistry;

}
