package org.smallbun.fast.manage.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.smallbun.fast.manage.role.entity.SysRoleMenuEntity;
import org.smallbun.framework.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/23 14:28
 */
@Mapper
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {
	/**
	 * 根据角色id获取记录
	 * @param id
	 * @return
	 */
	List<SysRoleMenuEntity> findByRoleId(Serializable id);
}
