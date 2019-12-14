/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.smallbun.scaffold.manage.service;

import cn.smallbun.scaffold.framework.mybatis.page.PageModel;
import cn.smallbun.scaffold.framework.mybatis.service.BaseService;
import cn.smallbun.scaffold.manage.entity.SysUserEntity;
import cn.smallbun.scaffold.manage.enums.UserStatus;
import cn.smallbun.scaffold.manage.pojo.UserAO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
public interface ISysUserService extends BaseService<SysUserEntity> {
	/**
	 * 添加
	 * @param ao ao
	 * @return boolean
	 */
	boolean save(UserAO ao);

	/**
	 * 根据ID更新数据
	 * @param ao {@link UserAO}
	 * @return boolean
	 */
	boolean updateById(UserAO ao);

	/**
	 * 根据用户名查询用户
	 *
	 * @param username username
	 * @return {@link SysUserEntity}
	 */
	SysUserEntity getByUserName(String username);

	/**
	 * 分页查询
	 * @param pageModel {@link PageModel}
	 * @param user {@link SysUserEntity}
	 * @return {@link IPage<SysUserEntity>}
	 */
	IPage<SysUserEntity> page(PageModel pageModel, SysUserEntity user);

	/**
	 * 更新密码
	 * @param id 用户ID
	 * @param passWord 密码
	 * @return 是否成功
	 */
	boolean updatePassWord(String id, String passWord);

	/**
	 * 根据ID更新状态
	 * @param id ID
	 * @param status 状态
	 * @return boolean
	 */
	boolean updateStatusById(String id, UserStatus status);
}
