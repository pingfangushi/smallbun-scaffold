package org.smallbun.fast.manage.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 部门service接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/3
 */
public interface SysOrgService extends IService<SysOrgEntity> {
	/**
	 * 根据角色id获取菜单
	 * @param id
	 * @return
	 */
	List<SysOrgEntity> findByRoleId(Serializable id);
}
