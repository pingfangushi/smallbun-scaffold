package org.smallbun.fast.manage.role.service;

import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.vo.SysRoleVO;
import org.smallbun.framework.base.BaseService;
import org.smallbun.framework.result.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/15 23:34
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	/**
	 * 根据编号获取id
	 * @param id
	 * @return
	 */
	@Override
	SysRoleVO getById(Serializable id);

	/**
	 * 唯一验证
	 * @param vo
	 * @return
	 */
	@Override
	Boolean unique(SysRoleEntity vo);

	/**
	 * model
	 * @param request
	 * @return
	 */
	SysRoleVO model(HttpServletRequest request);
}
