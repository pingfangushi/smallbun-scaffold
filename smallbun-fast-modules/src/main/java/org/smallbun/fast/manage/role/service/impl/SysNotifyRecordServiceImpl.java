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

package org.smallbun.fast.manage.role.service.impl;

import org.smallbun.fast.manage.notify.dao.SysNotifyRecordMapper;
import org.smallbun.fast.manage.notify.entity.SysNotifyRecordEntity;
import org.smallbun.fast.manage.role.service.SysNotifyRecordService;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知通告发送记录 服务实现类
 * </p>
 *
 * @author SanLi
 * @since 2019-03-07
 */
@Service
public class SysNotifyRecordServiceImpl extends BaseServiceImpl<SysNotifyRecordMapper, SysNotifyRecordEntity>
		implements SysNotifyRecordService {

	/**
	 * 根据通知公告id删除记录
	 * @param id
	 */
	@Override
	public void delByNotifyId(String id) {
		baseMapper.delByNotifyId(id);
	}
}
