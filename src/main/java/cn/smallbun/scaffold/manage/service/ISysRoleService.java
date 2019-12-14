package cn.smallbun.scaffold.manage.service;

import cn.smallbun.scaffold.manage.entity.SysRoleEntity;
import cn.smallbun.scaffold.manage.enums.RoleStatus;
import cn.smallbun.scaffold.manage.pojo.RoleAuthVO;
import cn.smallbun.scaffold.manage.pojo.UpdateAuthorizeBO;
import cn.smallbun.scaffold.framework.mybatis.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
public interface ISysRoleService extends BaseService<SysRoleEntity> {
	/**
	 * 保存角色和用户
	 * @param userId userId
	 * @param roleIds roleIds
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	boolean saveRoleUser(String userId, List<String> roleIds);

	/**
	 * 删除角色-用户关联根据用户ID
	 * @param id id
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	boolean removeByUserId(String id);

	/**
	 * 根据用户查询角色
	 * @param id id
	 * @return {@link List<SysRoleEntity>}
	 */
	List<SysRoleEntity> getByUserId(String id);

	/**
	 * 根据角色获取拥有的权限
	 * @param id id
	 * @return List<RoleAuthVO>
	 */
	List<RoleAuthVO> getRoleAuthById(String id);

	/**
	 * 更新权限信息
	 * @param auth auth
	 * @return Boolean
	 */
	boolean updateAuthorize(UpdateAuthorizeBO auth);


	/**
	 * 根据角色ID更新状态
	 * @param id id
	 * @param status status
	 * @return boolean
	 */
	boolean updateStatusById(String id, RoleStatus status);
}
