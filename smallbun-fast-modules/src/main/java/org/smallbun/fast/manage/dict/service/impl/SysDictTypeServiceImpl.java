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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
		//如果存在ID，为修改，否则为新增
		if (!StringUtils.isEmpty(dictType.getId())) {
			queryWrapper = new QueryWrapper<>(dictType).lambda()
					.eq(false, SysDictTypeEntity::getTypeName, dictType.getTypeName())
					.eq(false, SysDictTypeEntity::getId, dictType.getId());
		} else {
			queryWrapper = new QueryWrapper<>(dictType).lambda()
					.eq(false, SysDictTypeEntity::getTypeName, dictType.getTypeName());
		}
		return !StringUtils.isEmpty(dictType.getId()) ?
				CollectionUtils.isEmpty(baseMapper.selectList(queryWrapper)) ?
						AjaxResult.builder().result(false).build() :
						AjaxResult.builder().result(true).build() :
				//为空
				CollectionUtils.isEmpty(baseMapper.selectList(queryWrapper)) ?
						AjaxResult.builder().result(true).build() :
						AjaxResult.builder().result(false).build();
	}
}
