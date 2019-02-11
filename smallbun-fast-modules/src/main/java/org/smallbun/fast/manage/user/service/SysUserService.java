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

package org.smallbun.fast.manage.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.fast.manage.user.vo.SysUserVO;
import org.smallbun.framework.base.BaseService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * <br>
 * Created by 2689170096@qq.com on  2018/7/27 8:38
 */
public interface SysUserService extends BaseService<SysUserEntity> {

	/**
	 * model
	 * @param request
	 * @return
	 */
	SysUserVO model(HttpServletRequest request);

	/**
	 * 根据用户名查询用户
	 * @param username username
	 * @return SysUserEntity
	 */
	SysUserEntity findByUsername(String username);

	/**
	 * 保存或更新
	 * @param vo
	 * @return
	 */
	boolean saveOrUpdate(SysUserVO vo);

	/**
	 * 自定义分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	IPage<SysUserEntity> page(Page<SysUserEntity> page, SysUserVO vo);

	/**
	 *修改密码
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param confirmPassword 确认密码
	 * @return
	 */
	boolean changePassword(String oldPassword, String newPassword, String confirmPassword);

	/**
	 * 验证原密码
	 * @param oldPassword
	 * @return
	 */
	boolean verifyOldPassword(String oldPassword);

	/**
	 * 更新头像
	 * @param id
	 * @param url
	 * @return
	 */
	boolean updateHeadPortrait(String id, String url);

	/**
	 * changPassword
	 * @param password
	 * @return
	 */
	boolean changPassword(String password);
}
