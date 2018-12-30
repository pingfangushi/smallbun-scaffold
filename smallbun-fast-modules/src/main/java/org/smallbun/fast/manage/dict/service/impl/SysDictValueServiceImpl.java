package org.smallbun.fast.manage.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.manage.dict.dao.SysDictValueMapper;
import org.smallbun.fast.manage.dict.entity.SysDictValueEntity;
import org.smallbun.fast.manage.dict.service.SysDictValueService;
import org.smallbun.fast.manage.dict.vo.SysDictValueVO;
import org.smallbun.framework.base.BaseServiceImpl;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统字典数据 服务实现类
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Service
@Transactional(rollbackFor = Exception.class)
@CacheConfig(cacheNames = "dict")
public class SysDictValueServiceImpl extends BaseServiceImpl<SysDictValueMapper, SysDictValueEntity>
		implements SysDictValueService {

	/**
	 * 分页查询
	 * @param page page
	 * @return SysDictValueEntity
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IPage<SysDictValueEntity> page(Page<SysDictValueEntity> page, SysDictValueVO vo) {
		return baseMapper.page(page, vo);
	}

	/**
	 * 查询唯一
	 * @param dictValue
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public AjaxResult unique(SysDictValueEntity dictValue) {
		//构建查询条件
		LambdaQueryWrapper<SysDictValueEntity> queryWrapper;
		queryWrapper = new QueryWrapper<SysDictValueEntity>().lambda()
				.eq(false, SysDictValueEntity::getDictValue, dictValue.getDictType())
				.eq(false, SysDictValueEntity::getDictValue, dictValue.getDictValue());
		return uniqueResult(dictValue.getId(), queryWrapper);

	}


	/**
	 * 根据type code 查询字典值
	 * @param typeCode
	 * @return
	 */
	@Override
	@Cacheable(key = "#typeCode")
	public List<SysDictValueEntity> findByTypeCode(String typeCode) {
		return baseMapper.findByTypeCode(typeCode);
	}

	/**
	 * 根据type code 和值查询label
	 * @param typeCode typeCode
	 * @param dictValue dictValue
	 * @return {@link SysDictValueEntity}
	 */
	@Override
	@Cacheable(key = "T(String).valueOf(#typeCode).concat('-').concat(#dictValue)")
	public String findLabelByTypeCodeAndValue(String typeCode, String dictValue) {
		return baseMapper.findLabelByTypeCodeAndValue(typeCode, dictValue);
	}

}
