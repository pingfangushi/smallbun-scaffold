package cn.smallbun.scaffold.manage.mapper;

import cn.smallbun.scaffold.manage.entity.SysAuthorizeItemEntity;
import cn.smallbun.scaffold.framework.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统操作权限表 Mapper 接口
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-09
 */
@Mapper
public interface SysAuthorizeItemMapper extends BaseMapper<SysAuthorizeItemEntity> {

	/**
	 * 根据权限ID查询具有的权限
	 * @param auth 主权限ID
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getByAuthId(@Param(value = "authId") String auth);
	/**
	 * 根据角色查询权限项
	 * @param ids ids
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getAuthorizeItemsByRole(@Param("ids") List<String> ids);

	/**
	 * 查询所有权限
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getAuthorizeItems();
}
