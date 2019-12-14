package cn.smallbun.scaffold.manage.mapper;

import cn.smallbun.scaffold.manage.entity.SysRoleEntity;
import cn.smallbun.scaffold.manage.pojo.RoleAuthBO;
import cn.smallbun.scaffold.framework.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
	/**
	 * 保存角色-用户关联
	 * @param userId userId
	 * @param roleIds roleIds
	 * @return boolean
	 */
	boolean saveRoleUser(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);

	/**
	 * 删除角色-用户关联根据用户ID
	 * @param id id
	 * @return boolean
	 */
	boolean removeByUserId(@Param("id") String id);

	/**
	 * 根据用户ID查询角色信息
	 * @param id id
	 * @return {@link List<SysRoleEntity>}
	 */
	List<SysRoleEntity> findByUserId(String id);

	/**
	 * 添加角色权限关联关系
	 * @param id ID
	 * @param type 类型
	 * @param auth auth
	 * @return Long
	 */
	Long saveRoleAuthorize(@Param("id") String id, @Param("type") String type, @Param("auth") String auth);

	/**
	 * 删除角色权限关联关系
	 * @param id ID
	 * @param type 类型
	 * @param auth auth
	 * @return Long
	 */
	Long removeRoleAuthorize(@Param("id") String id, @Param("type") String type, @Param("auth") String auth);

	/**
	 * 根据角色ID获取权限关联关系
	 * @param id 角色ID
	 * @return {@link List<  RoleAuthBO  >}
	 */
	List<RoleAuthBO> getRoleAuthById(@Param("id") String id);
}
