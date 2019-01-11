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
import org.smallbun.framework.security.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.smallbun.fast.manage.user.util.UserUtil.getSession;

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
		sessionRegistry.getAllPrincipals().forEach(g -> sessionRegistry.getAllSessions(g, false).forEach(s -> {
			//获取登录用户
			activeUserStore.getUsers().stream().filter(u -> u.getSessionId().equals(s.getSessionId())).findAny()
					.ifPresent(t -> {
						//userDetails
						LoginUserDetails details = (LoginUserDetails) s.getPrincipal();
						list.add(UserDetailsVO.builder()
								//会话ID
								.sessionId(t.getSessionId())
								//最后操作时间
								.lastAccessTime(s.getLastRequest())
								//用户名
								.username(details.getUsername())
								//OS操作系统
								.os(t.getOs())
								//登录IP
								.logInIp(t.getLogInIp())
								//登录时间
								.logInTime(t.getLogInTime())
								//浏览器
								.browser(t.getBrowser())
								//登录地址
								.logInAddress(t.getLogInAddress())
								//归属部门
								.orgName(details.getSysUser().getOrg().getOrgName()).build());
					});
		}));
		return list;
	}

	/**
	 * 下线所有用户，除下达任务的人除外
	 */
	@Override
	public void expireUserSessions() {
		for (LoginUserDetails details : sessionRegistry.getAllPrincipals().stream().map(u -> (LoginUserDetails) u)
				.collect(Collectors.toList())) {
			//排除不是当前用户sessionId的用户
			sessionRegistry.getAllSessions(details, true).stream().filter(sessionInformation -> !sessionInformation
					.equals(sessionRegistry.getSessionInformation(getSession().getId())))
					.forEach(SessionInformation::expireNow);
		}
	}

	/**
	 * 下线指定用户
	 * @param sessionId {@link String}
	 */
	@Override
	public void expireUserSession(String sessionId) {
		sessionRegistry.getSessionInformation(sessionId).expireNow();
	}

	@Autowired
	private SessionRegistry sessionRegistry;
	@Autowired
	private ActiveUserStore activeUserStore;

}
