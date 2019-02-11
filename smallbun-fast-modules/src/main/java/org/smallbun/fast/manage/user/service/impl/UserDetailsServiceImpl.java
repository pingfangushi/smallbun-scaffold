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

package org.smallbun.fast.manage.user.service.impl;


import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.menu.service.SysMenuService;
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
	public UserDetailsServiceImpl(SysUserService sysUserService, SysMenuService sysMenuService) {
		this.sysUserService = sysUserService;
		this.sysMenuService = sysMenuService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUserEntity user = sysUserService.findByUsername(username);
		if (ObjectUtils.isEmpty(user)) {
			logger.info("------------------------{}------------------------", USER_DOES_NOT_EXIST);
			throw new UsernameNotFoundException(USER_DOES_NOT_EXIST);
		}
		if (user.getUserStatus().equals(String.valueOf(SystemConstant.LOCKED))) {
			logger.info("------------------------{}------------------------", USER_IS_LOCKED);
			throw new LockedException(USER_IS_LOCKED);
		}
		if (user.getUserStatus().equals(String.valueOf(SystemConstant.DISABLED))) {
			logger.info("------------------------{}------------------------", USER_HAS_BEEN_REVOKED);
			throw new DisabledException(USER_HAS_BEEN_REVOKED);
		}
		//获取菜单
		List<SysMenuEntity> menus = Lists.newArrayList();
		for (SysRoleEntity role : user.getRoleList()) {
			menus.addAll(sysMenuService.findByRoleId(role.getId()));
		}
		logger.debug("------------------------用户{}：具有的菜单{}------------------------", user.getUsername(), menus);
		logger.debug("------------------------用户{}：具有的角色{}------------------------", user.getUsername(),
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
	/**
	 * 注入系统菜单业务逻辑接口
	 */
	private final SysMenuService sysMenuService;


}
