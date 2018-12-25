package org.smallbun.fast.manage.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.role.dao.SysRoleMenuMapper;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.entity.SysRoleMenuEntity;
import org.smallbun.fast.manage.role.service.SysRoleMenuService;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/15 23:42
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenuEntity>
		implements SysRoleMenuService {


	/**
	 * 根据角色编号获取记录
	 * @param id id
	 * @return list
	 */
	@Override
	public List<SysRoleMenuEntity> findByRoleId(Serializable id) {
		return baseMapper.findByRoleId(id);
	}

	/**
	 * 添加角色-菜单
	 * 首先查询当前用户具有的菜单，如果有进行删除，然后进行添加
	 * @param menus {@link SysMenuEntity}
	 * @param role {@link SysRoleEntity}
	 * @return {@link Boolean}
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveOrUpdateRoleMenu(SysRoleEntity role, List<SysMenuEntity> menus) {
		//根据角色ID获取记录
		int count = super
				.count(new QueryWrapper<SysRoleMenuEntity>().lambda().eq(SysRoleMenuEntity::getRole, role.getId()));
		//如果存在记录，删除所有记录
		if (count > 1) {
			super.remove(new QueryWrapper<SysRoleMenuEntity>().lambda().eq(SysRoleMenuEntity::getRole, role.getId()));
		}
		//添加记录
		List<SysRoleMenuEntity> list = Lists.newArrayList();
		for (SysMenuEntity menu : menus) {
			SysRoleMenuEntity menuEntity = new SysRoleMenuEntity();
			menuEntity.setMenu(menu);
			menuEntity.setRole(role);
			list.add(menuEntity);
		}
		//批量添加
		return super.saveBatch(list);
	}
}
