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

package org.smallbun.fast.manage.menu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smallbun.fast.manage.menu.dao.SysMenuMapper;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.menu.service.SysMenuService;
import org.smallbun.fast.manage.menu.vo.SysMenuVO;
import org.smallbun.fast.manage.user.details.LoginUserDetails;
import org.smallbun.fast.manage.user.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 系统菜单业务逻辑实现类
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on 2018/5/2 13:01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {


	/**
	 * 获取当前用户具有的用户菜单
	 *
	 * @return List<SysMenuEntity>
	 */
	@Override
	public List<SysMenuVO> userMenus() {
		//获取当前用户
		LoginUserDetails user = UserUtil.getLoginUser();
		//获取用户所属的菜单
		assert user != null;
		List<SysMenuEntity> list = user.getMenus();
		//循环得到目录和菜单
		final List<SysMenuEntity> permissions = new ArrayList<>();
		for (SysMenuEntity l : list) {
			if (l.getType().equals(0) | l.getType().equals(1)) {
				permissions.add(l);
			}
		}
		//设置子菜单
		setChildren(permissions);
		//过滤菜单，拿到所有的父菜单
		List<SysMenuEntity> result = new ArrayList<>();
		for (SysMenuEntity p : permissions) {
			//判断是否是目录
			if (!p.getParentId().equals(0L)) {continue;}
			result.add(p);
		}
		return mappingList(result, new ArrayList<>(), SysMenuVO.class);
	}

	/**
	 * 根据用户ID进行查询所具有的菜单功能权限
	 *
	 * @param userId {@link Long} 用户ID
	 * @return {@link SysMenuEntity}
	 */
	@Override
	public List<SysMenuEntity> findByUserId(Serializable userId) {
		return baseMapper.findByUserId(userId);
	}

	/**
	 * 根据角色ID进行查询所具有的菜单功能权限
	 *
	 * @param roleId {@link Long} 角色ID
	 * @return {@link SysMenuEntity}
	 */
	@Override
	public List<SysMenuEntity> findByRoleId(Serializable roleId) {
		return baseMapper.findByRoleId(roleId);
	}


	/**
	 * 设置子菜单
	 *
	 * @param permissions permissions
	 */
	private void setChildren(List<SysMenuEntity> permissions) {
		permissions.parallelStream().forEach((SysMenuEntity per) -> {
			List<SysMenuEntity> child = new ArrayList<>();
			for (SysMenuEntity p : permissions) {
				if (p.getParentId().equals(per.getId())) {
					child.add(p);
				}
			}
			per.setChildren(child);
		});
	}
}
