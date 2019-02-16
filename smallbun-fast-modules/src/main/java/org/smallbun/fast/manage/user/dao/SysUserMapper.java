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

package org.smallbun.fast.manage.user.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.fast.manage.user.vo.SysUserVO;
import org.smallbun.framework.base.BaseMapper;

import java.io.Serializable;
import java.util.List;

/**
 * 用户数据接口
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
	/**
	 * 根据用户名获取用户
	 *
	 * @param username 用户名
	 * @return 用户
	 */
	SysUserEntity findByUsername(@Param("username") String username);

	/**
	 * 根据用户名获取用户
	 *
	 * @param roleId 角色ID
	 * @return 用户集合
	 */
	List<SysUserEntity> findByRoleId(@Param("roleId") String roleId);

	/**
	 * 更新密码
	 *
	 * @param id       用户ID
	 * @param password 密码
	 * @return 成功条数
	 */
	int changePassword(@Param("id") Long id, @Param("password") String password);

	/**
	 * 自定义分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	IPage<SysUserEntity> page(Page<SysUserEntity> page, @Param("p") SysUserVO vo);

	/**
	 * 根据id进行查询用户
	 * @param id
	 * @return
	 */
	SysUserEntity findById(@Param("id") Serializable id);

	/**
	 * 更改最后登录信息
	 * @param sysUser
	 * @return
	 */
	boolean updateLastLoginInfo(@Param("info") SysUserEntity sysUser);

	/**
	 * 更新头像
	 * @param id
	 * @param headPortraitUrl
	 * @return
	 */
	boolean updateHeadPortrait(@Param("id") String id, @Param("headPortraitUrl") String headPortraitUrl);
}
