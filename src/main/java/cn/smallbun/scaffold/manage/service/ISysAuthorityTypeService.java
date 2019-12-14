package cn.smallbun.scaffold.manage.service;

import cn.smallbun.scaffold.manage.entity.SysAuthorityTypeEntity;
import cn.smallbun.scaffold.framework.initialize.InitInterface;
import cn.smallbun.scaffold.framework.mybatis.service.BaseService;

import java.util.List;

/**
 * <p>
 * 系统业务信息维护表 服务类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-07
 */
public interface ISysAuthorityTypeService extends BaseService<SysAuthorityTypeEntity>, InitInterface {
	/**
	 * 查询list
	 * @return {@link List< SysAuthorityTypeEntity >}
	 */
	List<SysAuthorityTypeEntity> getAuthoritys();


}
