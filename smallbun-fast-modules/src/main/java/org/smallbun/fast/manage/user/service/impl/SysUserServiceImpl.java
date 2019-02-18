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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.service.SysRoleService;
import org.smallbun.fast.manage.user.dao.SysUserMapper;
import org.smallbun.fast.manage.user.details.LoginUserDetails;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.fast.manage.user.service.SysUserService;
import org.smallbun.fast.manage.user.util.UserUtil;
import org.smallbun.fast.manage.user.vo.SysUserVO;
import org.smallbun.framework.auto.SmallBunProperties;
import org.smallbun.framework.base.BaseServiceImpl;
import org.smallbun.framework.exception.BusinessExecption;
import org.smallbun.framework.security.LoggedUser;
import org.smallbun.framework.security.LoggedUserBindingListener;
import org.smallbun.framework.security.LoginSuccessHandler;
import org.smallbun.framework.toolkit.AutoMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.smallbun.fast.manage.user.util.UserUtil.getLoginUser;
import static org.smallbun.framework.constant.ExceptionConstant.EX000102;
import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.security.SecurityHandler.USER;

/**
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * <br>
 * Created by 2689170096@qq.com on  2018/7/27 8:38
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUserEntity>
		implements SysUserService, LoginSuccessHandler {


	@Autowired
	public SysUserServiceImpl(SysRoleService sysRoleService, SmallBunProperties smallBunProperties) {
		this.sysRoleService = sysRoleService;
		this.smallBunProperties = smallBunProperties;
	}

	/**
	 * model
	 * @param request
	 * @return
	 */
	@Override
	public SysUserVO model(HttpServletRequest request) {
		if (!request.getRequestURI().contains(UNIQUE)) {
			return StringUtils.isEmpty(request.getParameter(ID)) ?
					new SysUserVO() :
					AutoMapperUtil.mapping(getById(request.getParameter(ID)), new SysUserVO());
		}
		return new SysUserVO();
	}

	/**
	 * 重写getById
	 * @param id
	 * @return
	 */
	@Override
	public SysUserEntity getById(Serializable id) {
		return baseMapper.findById(id);
	}

	/**
	 * 保存或更新
	 * @param vo
	 * @return
	 */
	@Override
	public boolean saveOrUpdate(SysUserVO vo) {
		boolean flag;
		//如果没有密码，使用默认密码
		if (StringUtils.isEmpty(vo.getPassword())) {
			vo.setPassword(smallBunProperties.getUser().getRegisterDefaultPassword());
		}
		flag = super.saveOrUpdate(vo);
		if (flag) {
			//根据用户删除关联,保存用户和角色
			sysRoleService.delRoleUserByUserId(vo.getId());
			sysRoleService.saveRoleUser(vo.getId().toString(),
					vo.getRoleVOS().stream().filter(u -> !StringUtils.isEmpty(u.getId())).map(SysRoleEntity::getId)
							.map(Objects::toString).collect(Collectors.toList()));
		}
		return flag;
	}

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
	 * 自定义分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	@Override
	public IPage<SysUserEntity> page(Page<SysUserEntity> page, SysUserVO vo) {
		return baseMapper.page(page, vo);
	}

	/**
	 *修改密码
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param confirmPassword 确认密码
	 * @return
	 */
	@Override
	public boolean changePassword(String oldPassword, String newPassword, String confirmPassword) {
		//1.判断旧密码
		if (!new BCryptPasswordEncoder().matches(oldPassword, Objects.requireNonNull(getLoginUser()).getPassword())) {
			throw new BusinessExecption(EX000102, "旧密码输入错误");
		}
		//2.新密码
		if (!newPassword.equals(confirmPassword)) {
			throw new BusinessExecption("两次输入密码不一致");
		}
		//修改密码
		int i = baseMapper
				.changePassword(getLoginUser().getSysUser().getId(), new BCryptPasswordEncoder().encode(newPassword));
		return i > 0;
	}

	/**
	 * 验证原密码
	 * @param oldPassword
	 * @return
	 */
	@Override
	public boolean verifyOldPassword(String oldPassword) {
		return new BCryptPasswordEncoder().matches(oldPassword, Objects.requireNonNull(getLoginUser()).getPassword());
	}

	/**
	 * 更新头像
	 * @param id
	 * @param url
	 * @return
	 */
	@Override
	public boolean updateHeadPortrait(String id, String url) {
		boolean b = baseMapper.updateHeadPortrait(id, url);
		//更新用户头像
		if (b) {
			//更新LoginUserDetails
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
					auth.getCredentials(), auth.getAuthorities());
			//设置setDetails
			LoginUserDetails details = (LoginUserDetails) auth.getDetails();
			details.getSysUser().setHeadPortraitUrl(url);
			newAuth.setDetails(details);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		return b;
	}

	/**
	 * changPassword
	 * @param password
	 * @return
	 */
	@Override
	public boolean changPassword(String password) {
		//修改密码
		int i = baseMapper.changePassword(Objects.requireNonNull(getLoginUser()).getSysUser().getId(),
				new BCryptPasswordEncoder().encode(password));
		return i > 0;
	}


	/**
	 * 修改登录信息
	 * @param userDetails {@link UserDetails}
	 */
	@Override
	public void updateLoginInfo(UserDetails userDetails) {
		LoggedUserBindingListener activeUserStore = (LoggedUserBindingListener) UserUtil.getSession()
				.getAttribute(USER);
		LoggedUser loggedUser = activeUserStore.getLoggedUser();
		//强转LoginUserDetails
		LoginUserDetails user = (LoginUserDetails) userDetails;
		//设置ip
		user.getSysUser().setLastLoginIp(loggedUser.getLogInIp());
		//设置地址
		user.getSysUser().setLastLoginAddress(loggedUser.getLogInAddress());
		//设置登录时间
		user.getSysUser().setLastLoginTime(new Timestamp(loggedUser.getLogInTime().getTime()));
		//调用修改
		baseMapper.updateLastLoginInfo(user.getSysUser());
	}

	/**
	 * 注入角色业务逻辑接口
	 */
	private final SysRoleService sysRoleService;

	private final SmallBunProperties smallBunProperties;
}
