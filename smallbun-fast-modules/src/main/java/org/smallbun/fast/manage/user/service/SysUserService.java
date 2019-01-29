/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.smallbun.fast.manage.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.manage.user.details.LoginUserDetails;
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
	 * 修改用户最后修改信息
	 * @param loginUser
	 */
	void updateLastLoginInfo(LoginUserDetails loginUser);

}
