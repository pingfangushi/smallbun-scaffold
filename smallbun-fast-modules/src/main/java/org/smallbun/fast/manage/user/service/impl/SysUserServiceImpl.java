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
import org.smallbun.framework.base.BaseServiceImpl;
import org.smallbun.framework.exception.BusinessExecption;
import org.smallbun.framework.security.LoggedUser;
import org.smallbun.framework.security.LoggedUserBindingListener;
import org.smallbun.framework.security.LoginSuccessHandler;
import org.smallbun.framework.toolkit.AutoMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.smallbun.framework.constant.CoreConstant.EX000102;
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
	public SysUserServiceImpl(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
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
		if (!new BCryptPasswordEncoder()
				.matches(oldPassword, Objects.requireNonNull(UserUtil.getLoginUser()).getPassword())) {
			throw new BusinessExecption(EX000102, "旧密码输入错误");
		}
		//2.新密码
		if (!newPassword.equals(confirmPassword)) {
			throw new BusinessExecption("两次输入密码不一致");
		}
		//修改密码
		int i = baseMapper.changePassword(UserUtil.getLoginUser().getSysUser().getId(),
				new BCryptPasswordEncoder().encode(newPassword));
		return i > 0;
	}

	/**
	 * 验证原密码
	 * @param oldPassword
	 * @return
	 */
	@Override
	public boolean verifyOldPassword(String oldPassword) {
		return new BCryptPasswordEncoder()
				.matches(oldPassword, Objects.requireNonNull(UserUtil.getLoginUser()).getPassword());
	}

	/**
	 * 更新头像
	 * @param id
	 * @param url
	 * @return
	 */
	@Override
	public boolean updateHeadPortrait(String id, String url) {
		return baseMapper.updateHeadPortrait(id, url);
	}

	/**
	 * changPassword
	 * @param password
	 * @return
	 */
	@Override
	public boolean changPassword(String password) {
		//修改密码
		int i = baseMapper.changePassword(Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser().getId(),
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

}
