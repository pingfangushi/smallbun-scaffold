/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.fast.manage.dict.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.manage.dict.dao.SysDictValueMapper;
import org.smallbun.fast.manage.dict.entity.SysDictValueEntity;
import org.smallbun.fast.manage.dict.service.SysDictValueService;
import org.smallbun.fast.manage.dict.vo.SysDictValueVO;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mapping;

/**
 * 系统字典数据 服务实现类
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictValueServiceImpl extends BaseServiceImpl<SysDictValueMapper, SysDictValueEntity>
		implements SysDictValueService {
	/**
	 * model
	 * @param request
	 * @return
	 */
	@Override
	public SysDictValueVO model(HttpServletRequest request) {
		if (!request.getRequestURI().contains(UNIQUE)) {
			return StringUtils.isEmpty(request.getParameter(ID)) ?
					new SysDictValueVO() :
					mapping(getById(request.getParameter(ID)), new SysDictValueVO());
		}
		return new SysDictValueVO();
	}

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
	 * 根据type code 查询字典值
	 * @param typeCode
	 * @return
	 */
	@Override
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
	public String findLabelByTypeCodeAndValue(String typeCode, String dictValue) {
		return baseMapper.findLabelByTypeCodeAndValue(typeCode, dictValue);
	}


}
