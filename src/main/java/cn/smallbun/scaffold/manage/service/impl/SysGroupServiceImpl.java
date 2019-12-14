package cn.smallbun.scaffold.manage.service.impl;

import cn.smallbun.scaffold.manage.mapper.SysGroupMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import cn.smallbun.scaffold.framework.mybatis.domain.IdEntity;
import cn.smallbun.scaffold.framework.mybatis.service.BaseServiceImpl;
import cn.smallbun.scaffold.manage.entity.SysGroupEntity;
import cn.smallbun.scaffold.manage.enums.GroupStatus;
import cn.smallbun.scaffold.manage.service.ISysGroupService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统机构表 服务实现类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Service
public class SysGroupServiceImpl extends BaseServiceImpl<SysGroupMapper, SysGroupEntity> implements ISysGroupService {

	/**
	 * 根据ID更新组织机构信息
	 * @param id ID
	 * @param status 状态
	 * @return boolean
	 */
	@Override
	public boolean updateStatusById(String id, GroupStatus status) {
		return update(new LambdaUpdateWrapper<SysGroupEntity>()
				// ID
				.eq(IdEntity::getId, id)
				// 设置装填
				.set(SysGroupEntity::getStatus, status));
	}
}
