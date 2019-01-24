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

package org.smallbun.fast.manage.user.details;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.framework.constant.SystemConstant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义UserDetails
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
@Data
@Builder
public class LoginUserDetails implements UserDetails {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	@JSONField(serialize = false)
	private String password;
	/**
	 * 当前用户拥有的系统菜单
	 */
	@JSONField(serialize = false)
	private List<SysMenuEntity> menus;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 用户实体类
	 */
	private SysUserEntity sysUser;
	/**
	 * 登录IP
	 */
	private String logInIp;
	/**
	 * 登录地址
	 */
	private String logInAddress;
	/**
	 * 登录时间
	 */
	private String logInTime;

	/**
	 * 返回授予用户的权限。, 无法返回<fast> null </ fast>
	 *
	 * @return 当局按自然键排序（从不<fast> null </ fast>）
	 */
	@Override
	@JSONField(serialize = false)
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return menus.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
	}

	/**
	 * 账户是否未过期
	 *
	 * @return
	 */
	@JSONField(serialize = false)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 账户是否未锁定
	 *
	 * @return
	 */
	@JSONField(serialize = false)
	@Override
	public boolean isAccountNonLocked() {
		return !getStatus().equals(String.valueOf(SystemConstant.LOCKED));
	}

	/**
	 * 密码是否未过期
	 *
	 * @return
	 */
	@JSONField(serialize = false)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 账户是否激活
	 *
	 * @return
	 */
	@Override
	@JSONField(serialize = false)
	public boolean isEnabled() {
		return !getStatus().equals(String.valueOf(SystemConstant.DISABLED));
	}

	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof LoginUserDetails) {
			return username.equals(((LoginUserDetails) rhs).getUsername());
		}
		return false;
	}

	/**
	 * Returns the hashcode of the {@code username}.
	 */
	@Override
	public int hashCode() {
		return username.hashCode();
	}

	/**
	 * toString
	 *
	 * @return username
	 */
	@Override
	public String toString() {
		return username;
	}
}
