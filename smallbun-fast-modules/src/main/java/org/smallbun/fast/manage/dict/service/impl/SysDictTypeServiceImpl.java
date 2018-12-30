package org.smallbun.fast.manage.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.smallbun.fast.manage.dict.dao.SysDictTypeMapper;
import org.smallbun.fast.manage.dict.entity.SysDictTypeEntity;
import org.smallbun.fast.manage.dict.service.SysDictTypeService;
import org.smallbun.framework.base.BaseServiceImpl;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统字典类型 服务实现类
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeMapper, SysDictTypeEntity>
		implements SysDictTypeService {

	/**
	 * 唯一条件
	 * @param dictType dictType
	 * @return AjaxResult
	 */
	@Override
	public AjaxResult unique(SysDictTypeEntity dictType) {
		//构建查询条件
		LambdaQueryWrapper<SysDictTypeEntity> queryWrapper;
		queryWrapper = new QueryWrapper<SysDictTypeEntity>().lambda()
				.eq(true, SysDictTypeEntity::getTypeCode, dictType.getTypeCode());
		return uniqueResult(dictType.getId(), queryWrapper);
	}
}
