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

package org.smallbun.fast.manage.menu.service;

import org.smallbun.fast.common.base.BaseTreeService;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.menu.vo.SysMenuVO;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单业务逻辑接口
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on 2018/5/2 13:01
 */
public interface SysMenuService extends BaseTreeService<SysMenuEntity> {
	/**
	 * 获取当前用户具有的用户菜单
	 *
	 * @return
	 */
	List<SysMenuVO> userMenus();

	/**
	 * 根据用户ID进行查询所具有的菜单功能权限
	 *
	 * @param userId {@link Long} 用户ID
	 * @return {@link SysMenuEntity}
	 */
	List<SysMenuEntity> findByUserId(Serializable userId);

	/**
	 * 根据角色ID进行查询所具有的菜单功能权限
	 *
	 * @param roleId {@link Long} 角色ID
	 * @return {@link SysMenuEntity}
	 */
	List<SysMenuEntity> findByRoleId(Serializable roleId);

	/**
	 * model
	 * @param request
	 * @return
	 */
	SysMenuVO model(HttpServletRequest request);
}
