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

package org.smallbun.fast.manage.org.service.impl;

import org.smallbun.fast.common.base.BaseTreeDataServiceImpl;
import org.smallbun.fast.manage.org.dao.SysOrgMapper;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.org.service.SysOrgService;
import org.smallbun.fast.manage.org.vo.SysOrgVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mapping;

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
	 * model
	 * @param request
	 * @return
	 */
	@Override
	public SysOrgVO model(HttpServletRequest request) {
		if (!request.getRequestURI().contains(UNIQUE)) {
			return StringUtils.isEmpty(request.getParameter(ID)) ?
					new SysOrgVO() :
					mapping(getById(request.getParameter(ID)), new SysOrgVO());
		}
		return new SysOrgVO();
	}

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

}
