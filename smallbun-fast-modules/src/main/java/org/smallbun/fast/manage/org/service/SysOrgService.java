package org.smallbun.fast.manage.org.service;

import org.smallbun.fast.common.base.BaseTreeService;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.org.vo.SysOrgVO;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 部门service接口
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/3
 */
public interface SysOrgService extends BaseTreeService<SysOrgEntity> {
	/**
	 * model
	 * @param request
	 * @return
	 */
	SysOrgVO model(HttpServletRequest request);

	/**
	 * 根据角色id获取菜单
	 *
	 * @param id
	 * @return
	 */
	List<SysOrgEntity> findByRoleId(Serializable id);

	/**
	 * 唯一
	 * @param org
	 * @return
	 */
	@Override
	Boolean unique(SysOrgEntity org);
}
