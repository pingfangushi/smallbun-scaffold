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


import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.user.details.LoginUserDetails;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.fast.manage.user.service.SysUserService;
import org.smallbun.framework.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 自定义用户逻辑实现
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
@Service(value = "UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	private static final String USER_DOES_NOT_EXIST = "用户不存在";
	private static final String USER_IS_LOCKED = "用户被锁定,请联系管理员";
	private static final String USER_HAS_BEEN_REVOKED = "用户已作废";

	@Autowired
	public UserDetailsServiceImpl(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUserEntity user = sysUserService.findByUsername(username);
		if (ObjectUtils.isEmpty(user)) {
			logger.info("------------------------{}------------------------", USER_DOES_NOT_EXIST);
			throw new UsernameNotFoundException(USER_DOES_NOT_EXIST);
		}
		if (user.getUserStatus().equals(SystemConstant.LOCKED)) {
			logger.info("------------------------{}------------------------", USER_IS_LOCKED);
			throw new LockedException(USER_IS_LOCKED);
		}
		if (user.getUserStatus().equals(SystemConstant.DISABLED)) {
			logger.info("------------------------{}------------------------", USER_HAS_BEEN_REVOKED);
			throw new DisabledException(USER_HAS_BEEN_REVOKED);
		}
		//获取菜单
		List<SysMenuEntity> menus = Lists.newArrayList();
		for (SysRoleEntity role : user.getRoleList()) {
			menus.addAll(role.getMenuList());
		}
		logger.info("------------------------用户{}：具有的菜单{}------------------------", user.getUsername(), menus);
		logger.info("------------------------用户{}：具有的角色{}------------------------", user.getUsername(),
				user.getRoleList());
		return LoginUserDetails.builder().username(user.getUsername()).password(user.getPassword())
				.status(user.getUserStatus()).sysUser(user)
				//查询用户具有的菜单
				.menus(menus).build();
	}

	/**
	 * 注入用户业务逻辑接口
	 */
	private final SysUserService sysUserService;


}
