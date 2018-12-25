package org.smallbun.fast.manage.org.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 组织部门Mapper接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/3
 */
@Mapper
@Repository
public interface SysOrgMapper extends BaseMapper<SysOrgEntity> {
	/**
	 * 根据角色id获取对应可访问数据的部门
	 * @param id
	 * @return
	 */
	List<SysOrgEntity> findByRoleId(Serializable id);
}
