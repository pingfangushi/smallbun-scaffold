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

package org.smallbun.fast.manage.user.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.fast.manage.user.vo.SysUserVO;
import org.smallbun.framework.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 用户数据接口
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
@Mapper
@Repository
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
