package cn.smallbun.scaffold.manage.service;

import cn.smallbun.scaffold.manage.entity.SysAuthorizeItemEntity;
import cn.smallbun.scaffold.framework.initialize.InitInterface;
import cn.smallbun.scaffold.framework.mybatis.service.BaseService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 系统操作权限表 服务类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-09
 */
public interface ISysAuthorizeItemService extends BaseService<SysAuthorizeItemEntity>, InitInterface {
	/**
	 * 通过角色获取权限项
	 * @param ids ids
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getAuthorizeItemsByRole(List<String> ids);

	/**
	 * 查询所有权限项目
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getAuthorizeItems();

	/**
	 * removeByTypes
	 * @param idList {@link List}
	 * @return boolean
	 */
	boolean removeByTypes(Collection<? extends Serializable> idList);
}
