package cn.smallbun.scaffold.manage.mapper;

import cn.smallbun.scaffold.manage.entity.SysAuthorityTypeEntity;
import cn.smallbun.scaffold.framework.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 系统权限类型 Mapper 接口
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-07
 */
@Mapper
public interface SysAuthorityTypeMapper extends BaseMapper<SysAuthorityTypeEntity> {
	/**
	 * 查询list
	 * @return {@link List< SysAuthorityTypeEntity >}
	 */
	List<SysAuthorityTypeEntity> getAuthoritys();
}
