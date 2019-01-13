package org.smallbun.fast.manage.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.role.dao.SysRoleOrgMapper;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.entity.SysRoleOrgEntity;
import org.smallbun.fast.manage.role.service.SysRoleOrgService;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 角色-部门
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/23 21:47
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class SysRoleOrgServiceImpl extends BaseServiceImpl<SysRoleOrgMapper, SysRoleOrgEntity>
		implements SysRoleOrgService {

	/**
	 *  添加或更新角色-部门
	 * @param role entity
	 * @param orgList orgList
	 * @return boolean
	 */
	@Override
	public boolean saveOrUpdateRoleOrg(SysRoleEntity role, List<SysOrgEntity> orgList) {
		//根据角色ID获取记录
		int count = super
				.count(new QueryWrapper<SysRoleOrgEntity>().lambda().eq(SysRoleOrgEntity::getRole, role.getId()));
		//如果存在记录，删除所有记录
		if (count > 1) {
			super.remove(new QueryWrapper<SysRoleOrgEntity>().lambda().eq(SysRoleOrgEntity::getRole, role.getId()));
		}
		//添加记录
		List<SysRoleOrgEntity> list = Lists.newArrayList();
		orgList.forEach(org -> {
			SysRoleOrgEntity menuEntity = new SysRoleOrgEntity();
			menuEntity.setOrg(org);
			menuEntity.setRole(role);
			list.add(menuEntity);
		});
		//批量添加
		return super.saveBatch(list);
	}

	/**
	 * 根据角色id获取记录
	 * @param id id
	 * @return {@link List}
	 */
	@Override
	public List<SysRoleOrgEntity> findByRoleId(Serializable id) {
		return baseMapper.findByRoleId(id);
	}
}
