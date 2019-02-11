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

import java.sql.Timestamp;
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
