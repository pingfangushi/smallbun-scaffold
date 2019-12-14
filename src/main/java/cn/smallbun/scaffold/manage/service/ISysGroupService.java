package cn.smallbun.scaffold.manage.service;

import cn.smallbun.scaffold.framework.mybatis.service.BaseService;
import cn.smallbun.scaffold.manage.entity.SysGroupEntity;
import cn.smallbun.scaffold.manage.enums.GroupStatus;

/**
 * <p>
 * 系统机构表 服务类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
public interface ISysGroupService extends BaseService<SysGroupEntity> {
	/**
	 * 根据ID更新组织机构信息
	 * @param id ID
	 * @param status 状态
	 * @return boolean
	 */
	boolean updateStatusById(String id, GroupStatus status);
}
