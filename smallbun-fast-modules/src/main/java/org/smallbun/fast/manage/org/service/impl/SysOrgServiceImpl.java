package org.smallbun.fast.manage.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.smallbun.fast.common.base.BaseTreeDataServiceImpl;
import org.smallbun.fast.manage.org.dao.SysOrgMapper;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.org.service.SysOrgService;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 系统部门接口
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/3
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysOrgServiceImpl extends BaseTreeDataServiceImpl<SysOrgMapper, SysOrgEntity> implements SysOrgService {


	/**
	 * 根据角色id获取菜单
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<SysOrgEntity> findByRoleId(Serializable id) {
		return baseMapper.findByRoleId(id);
	}

	/**
	 * 唯一
	 * @param org
	 * @return
	 */
	@Override
	public AjaxResult unique(SysOrgEntity org) {
		//构建查询条件
		LambdaQueryWrapper<SysOrgEntity> queryWrapper;
		queryWrapper = new QueryWrapper<SysOrgEntity>().lambda().eq(true, SysOrgEntity::getOrgCode, org.getOrgCode())
				.eq(true, SysOrgEntity::getOrgName, org.getOrgName());

		return uniqueResult(org.getId(), queryWrapper);
	}
}
