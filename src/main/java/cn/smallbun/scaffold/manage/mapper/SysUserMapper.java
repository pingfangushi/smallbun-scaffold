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

package cn.smallbun.scaffold.manage.mapper;

import cn.smallbun.scaffold.framework.mybatis.mapper.BaseMapper;
import cn.smallbun.scaffold.framework.mybatis.page.PageModel;
import cn.smallbun.scaffold.manage.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
	/**
	 * 根据用户名获取用户
	 *
	 * @param username 用户名
	 * @return 用户
	 */
	SysUserEntity findByUserName(@Param("username") String username);

	/**
	 * 分页
	 * @param page {@link PageModel} pageModel
	 * @param user {@link SysUserEntity} user
	 * @return {@link List<SysUserEntity>}
	 */
	IPage<SysUserEntity> page(Page<SysUserEntity> page, @Param("user") SysUserEntity user);
}
