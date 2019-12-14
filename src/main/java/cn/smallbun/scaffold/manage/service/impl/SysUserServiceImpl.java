/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.smallbun.scaffold.manage.service.impl;

import cn.smallbun.scaffold.framework.configurer.SmallBunProperties;
import cn.smallbun.scaffold.framework.mybatis.page.PageModel;
import cn.smallbun.scaffold.framework.mybatis.service.BaseServiceImpl;
import cn.smallbun.scaffold.manage.entity.SysGroupEntity;
import cn.smallbun.scaffold.manage.entity.SysRoleEntity;
import cn.smallbun.scaffold.manage.entity.SysUserEntity;
import cn.smallbun.scaffold.manage.enums.UserStatus;
import cn.smallbun.scaffold.manage.mapper.SysUserMapper;
import cn.smallbun.scaffold.manage.pojo.UserAO;
import cn.smallbun.scaffold.manage.service.ISysGroupService;
import cn.smallbun.scaffold.manage.service.ISysRoleService;
import cn.smallbun.scaffold.manage.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {
	public SysUserServiceImpl(ISysRoleService iSysRoleService, ISysGroupService iSysGroupService,
			SmallBunProperties properties) {
		this.iSysRoleService = iSysRoleService;
		this.iSysGroupService = iSysGroupService;
		this.properties = properties;
	}

	/**
	 * getById
	 * @param id id
	 * @return {@link SysUserEntity}
	 */
	@Override
	public SysUserEntity getById(Serializable id) {
		SysUserEntity user = super.getById(id);
		// 具有角色
		List<SysRoleEntity> roles = iSysRoleService.getByUserId(id.toString());
		user.setRoles(roles);
		// 组织机构
		SysGroupEntity group = iSysGroupService.getById(user.getGroupId());
		user.setGroup(group);
		return user;
	}

	/**
	 * 保存操作
	 * @param ao {@link SysUserEntity}
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(UserAO ao) {
		//默认密码
		ao.setPasswordHash(properties.getUser().getRegisterDefaultPassword());
		boolean save = super.save(ao);
		if (save) {
			save = iSysRoleService.saveRoleUser(ao.getId(), ao.getRoleIds());
		}
		return save;
	}

	/**
	 * 根据ID更新数据
	 * @param ao {@link UserAO}
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateById(UserAO ao) {
		//更新基本信息
		super.updateById(ao);
		//更新角色关联关系
		iSysRoleService.removeByUserId(ao.getId());
		//保存用户和角色
		iSysRoleService.saveRoleUser(ao.getId(), ao.getRoleIds());
		return true;
	}

	/**
	 * 根据用户名查询用户
	 *
	 * @param username username
	 * @return SysUserEntity
	 */
	@Override
	public SysUserEntity getByUserName(String username) {
		return baseMapper.findByUserName(username);
	}

	/**
	 * 分页查询
	 * @param pageModel {@link PageModel}
	 * @param user {@link SysUserEntity}
	 * @return {@link IPage<SysUserEntity>}
	 */
	@Override
	public IPage<SysUserEntity> page(PageModel pageModel, SysUserEntity user) {
		return baseMapper.page(getPage(pageModel), user);
	}

	/**
	 * 更新密码
	 * @param id 用户ID
	 * @param passWord 密码
	 * @return 是否成功
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updatePassWord(String id, String passWord) {
		return update(new LambdaUpdateWrapper<SysUserEntity>()
				// ID
				.eq(SysUserEntity::getId, id)
				// 密码
				.set(SysUserEntity::getPasswordHash, new BCryptPasswordEncoder().encode(passWord)));
	}

	/**
	 * 根据ID更新状态
	 * @param id ID
	 * @param status 状态
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateStatusById(String id, UserStatus status) {
		return update(new LambdaUpdateWrapper<SysUserEntity>()
				// ID
				.eq(SysUserEntity::getId, id)
				// 设置装填
				.set(SysUserEntity::getStatus, status));
	}

	/**
	 * ISysRoleService
	 */
	private final ISysRoleService iSysRoleService;
	/**
	 * ISysGroupService
	 */
	private final ISysGroupService iSysGroupService;
	/**
	 * SmallBunProperties
	 */
	private final SmallBunProperties properties;
}
