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

package org.smallbun.fast.manage.notify.service.impl;

import org.smallbun.fast.common.utils.AutoMapperUtil;
import org.smallbun.fast.manage.notify.dao.SysNotifyMapper;
import org.smallbun.fast.manage.notify.entity.SysNotifyEntity;
import org.smallbun.fast.manage.notify.service.SysNotifyService;
import org.smallbun.fast.manage.notify.vo.SysNotifyVO;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;

/**
 * 通知公告实现类
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/14 19:24
 */
@Service
public class SysNotifyServiceImpl extends BaseServiceImpl<SysNotifyMapper, SysNotifyEntity>
		implements SysNotifyService {

	@Override
	public SysNotifyVO model(HttpServletRequest request) {
		if (!request.getRequestURI().contains(UNIQUE)) {
			return StringUtils.isEmpty(request.getParameter(ID)) ?
					new SysNotifyVO() :
					AutoMapperUtil.mapping(getById(request.getParameter(ID)), new SysNotifyVO());
		}
		return new SysNotifyVO();
	}
}
