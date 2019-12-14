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

package cn.smallbun.scaffold.manage.web;


import cn.smallbun.scaffold.framework.common.result.ApiRestResult;
import cn.smallbun.scaffold.framework.common.toolkit.StringUtil;
import cn.smallbun.scaffold.framework.configurer.SmallBunProperties;
import cn.smallbun.scaffold.framework.demo.annotation.DemoEnvironment;
import cn.smallbun.scaffold.framework.logger.annotation.Logger;
import cn.smallbun.scaffold.framework.logger.enmus.Platform;
import cn.smallbun.scaffold.framework.mybatis.page.Page;
import cn.smallbun.scaffold.framework.mybatis.page.PageModel;
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import cn.smallbun.scaffold.framework.web.BaseResource;
import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import cn.smallbun.scaffold.manage.entity.SysUserEntity;
import cn.smallbun.scaffold.manage.enums.UserStatus;
import cn.smallbun.scaffold.manage.pojo.UserAO;
import cn.smallbun.scaffold.manage.pojo.UserVO;
import cn.smallbun.scaffold.manage.service.ISysUserService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.smallbun.scaffold.framework.logger.enmus.Operate.*;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.mapping;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.pageMapping;
import static cn.smallbun.scaffold.manage.constant.ManageConstant.MANAGE_API_PATH;
import static cn.smallbun.scaffold.manage.web.SysUserResource.API;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Validated
@RestController
@Api(tags = API)
@Logger(module = API)
@RequestMapping(MANAGE_API_PATH + "/user")
public class SysUserResource extends BaseResource {

	final static String API = "用户管理API";

	/**
	 * 添加
	 * @param user {@link UserAO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "新增用户", action = ADD, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:user:add')")
	@ApiOperation(value = "新增用户", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 1)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult add(@Validated(AddGroup.class) @RequestBody UserAO user) {
		return new ApiRestResult<>().result(iSysUserService.save(user)).build();
	}

	/**
	 * 修改用户
	 *
	 * @param user {@link UserAO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "修改用户", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:user:update')")
	@ApiOperation(value = "修改用户", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 2)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult updateById(@Validated(UpdateGroup.class) @RequestBody UserAO user) {
		return new ApiRestResult<>().result(iSysUserService.updateById(user)).build();
	}

	/**
	 * 根据用户ID删除并清除缓存
	 *
	 * @param ids {@link String} ids
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "根据ID删除用户", action = REMOVE, platform = Platform.MANAGE)
	@ApiOperation(value = "根据ID删除用户", produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 3)
	@PreAuthorize("hasAuthority('manage:interface:user:remove')")
	public ApiRestResult removeByIds(@PathVariable String ids) {
		return new ApiRestResult<>()
				.result(iSysUserService.removeByIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)))).build();
	}

	/**
	 * 分页查询用户信息
	 *
	 * @param pageModel {@link PageModel}
	 * @param user {@link SysUserEntity}
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "分页查询用户列表", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:user:fetch')")
	@ApiOperation(value = "分页查询用户列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 4)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Page<UserVO>> getPage(PageModel pageModel, SysUserEntity user) {
		// 转换为VO
		Page<UserVO> page = pageMapping(iSysUserService.page(pageModel, user), UserVO.class);
		return new ApiRestResult<Page<UserVO>>().result(page).build();
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "根据ID查询用户", action = FETCH, platform = Platform.MANAGE)
	@ApiOperation(value = "根据ID查询用户")
	@PreAuthorize("hasAuthority('manage:interface:user:fetch')")
	@ApiOperationSupport(order = 5)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<UserVO> getById(@PathVariable String id) {
		// 转换为VO
		UserVO user = mapping(iSysUserService.getById(id), new UserVO());
		return new ApiRestResult<UserVO>().result(user).build();
	}

	/**
	 * 用户唯一验证
	 *
	 * @param value {@link SysDictItemEntity} value
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "唯一用户验证", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:user:unique')")
	@ApiOperation(value = "唯一用户验证", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 6)
	@GetMapping(value = "unique", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Boolean> unique(SysUserEntity value) {
		//全局用户
		if (StringUtils.isNotBlank(value.getUsername())) {
			if (value.getUsername().equals(properties.getSecurity().getUsername())) {
				return new ApiRestResult<Boolean>().result(false).build();
			}
		}
		return new ApiRestResult<Boolean>().result(iSysUserService.unique(value)).build();
	}

	/**
	 * 设置用户密码
	 *
	 * @param id {@link String} 用户ID
	 * @param passWord {@link String} 密码
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "设置用户密码", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:user:update:password')")
	@ApiOperation(value = "设置用户密码", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 7)
	@PutMapping(value = "password", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Boolean> updatePassWord(String id, String passWord) {
		return new ApiRestResult<Boolean>().result(iSysUserService.updatePassWord(id, passWord)).build();
	}

	/**
	 * 根据ID更新用户类型状态
	 * @param id id
	 * @param status 状态
	 * @return 结果
	 */
	@Logger(feature = "根据ID更新用户类型状态", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:user:update')")
	@ApiOperation(value = "根据ID更新用户类型状态", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 9)
	@PutMapping(value = "/{id}/status/{status}")
	public ApiRestResult updateStatusById(@PathVariable String id, @PathVariable UserStatus status) {
		return new ApiRestResult<>().result(iSysUserService.updateStatusById(id, status)).build();
	}

	/**
	 *	注入用户业务接口
	 */
	private final ISysUserService iSysUserService;
	/**
	 * SmallBunProperties
	 */
	private final SmallBunProperties properties;


	public SysUserResource(ISysUserService iSysUserService, SmallBunProperties properties) {
		this.iSysUserService = iSysUserService;
		this.properties = properties;
	}
}
