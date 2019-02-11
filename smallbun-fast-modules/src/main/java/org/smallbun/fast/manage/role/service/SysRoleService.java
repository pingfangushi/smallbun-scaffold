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

package org.smallbun.fast.manage.role.service;

import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.vo.SysRoleVO;
import org.smallbun.framework.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/15 23:34
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	/**
	 * 根据编号获取id
	 * @param id
	 * @return
	 */
	@Override
	SysRoleVO getById(Serializable id);

	/**
	 * 唯一验证
	 * @param vo
	 * @return
	 */
	@Override
	Boolean unique(SysRoleEntity vo);

	/**
	 * model
	 * @param request
	 * @return
	 */
	SysRoleVO model(HttpServletRequest request);

	/**
	 * 保存角色和用户
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	boolean saveRoleUser(String userId, List<String> roleIds);

	/**
	 * 删除角色-用户关联根据用户ID
	 * @param id
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	boolean delRoleUserByUserId(Long id);
}
