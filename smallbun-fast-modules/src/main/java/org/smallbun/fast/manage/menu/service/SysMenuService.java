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
