package org.smallbun.fast.manage.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.role.entity.SysRoleOrgEntity;
import org.smallbun.framework.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 角色-部门
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/23 21:45
 */
@Repository
@Mapper
public interface SysRoleOrgMapper extends BaseMapper<SysRoleOrgEntity> {
	/**
	 * 根据角色获取记录
	 * @param id id
	 * @return {@link List}
	 */
	List<SysRoleOrgEntity> findByRoleId(Serializable id);
}
