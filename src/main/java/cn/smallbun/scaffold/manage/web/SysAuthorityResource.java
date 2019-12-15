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
import cn.smallbun.scaffold.framework.demo.annotation.DemoEnvironment;
import cn.smallbun.scaffold.framework.logger.annotation.Logger;
import cn.smallbun.scaffold.framework.logger.enmus.Platform;
import cn.smallbun.scaffold.framework.mybatis.page.Page;
import cn.smallbun.scaffold.framework.mybatis.page.PageModel;
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import cn.smallbun.scaffold.framework.web.BaseResource;
import cn.smallbun.scaffold.manage.constant.ManageConstant;
import cn.smallbun.scaffold.manage.entity.SysAuthorityTypeEntity;
import cn.smallbun.scaffold.manage.entity.SysAuthorizeItemEntity;
import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import cn.smallbun.scaffold.manage.pojo.AuthorityVO;
import cn.smallbun.scaffold.manage.pojo.AuthorizeItemVO;
import cn.smallbun.scaffold.manage.service.ISysAuthorityTypeService;
import cn.smallbun.scaffold.manage.service.ISysAuthorizeItemService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.smallbun.scaffold.framework.logger.enmus.Operate.*;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.*;

/**
 * <p>
 * 系统业务信息维护表 前端控制器
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-07
 */
@Validated
@Api(tags = SysAuthorityResource.API)
@Logger(module = SysAuthorityResource.API)
@RestController
@RequestMapping(ManageConstant.MANAGE_API_PATH + "/authority")
public class SysAuthorityResource extends BaseResource {

	final static String API = "权限管理API";


	/**
	 * 添加
	 * @param authority {@link AuthorityVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "新增系统权限", action = ADD, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:authority:add')")
	@ApiOperation(value = "新增系统权限", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 1)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Boolean> add(
			@Validated(value = {AddGroup.class}) @RequestBody SysAuthorityTypeEntity authority) {
		return ApiRestResult.ok(typeService.save(authority)).build();
	}


	/**
	 * 修改系统权限
	 *
	 * @param authority {@link AuthorityVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "修改系统权限", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:authority:update')")
	@ApiOperation(value = "修改系统权限", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 2)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Boolean> updateById(
			@Validated(value = {UpdateGroup.class}) @RequestBody SysAuthorityTypeEntity authority) {
		return ApiRestResult.ok(typeService.updateById(authority)).build();
	}

	/**
	 * 根据系统权限ID删除并清除缓存
	 *
	 * @param ids {@link String} ids
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "根据ID删除权限", action = REMOVE, platform = Platform.MANAGE)
	@ApiOperation(value = "根据ID删除权限", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 3)
	@DeleteMapping(value = "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:authority:remove')")
	public ApiRestResult removeByIds(@PathVariable String ids) {
		return ApiRestResult.ok(typeService.removeByIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT))))
				.build();
	}

	/**
	 * 分页查询系统权限信息
	 *
	 * @param pageModel {@link PageModel} 对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "分页查询权限列表", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:authority:fetch')")
	@ApiOperation(value = "分页查询权限列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 4)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Page<AuthorityVO>> getPage(PageModel pageModel, SysAuthorityTypeEntity authority) {
		// 转换为VO
		Page<AuthorityVO> page = pageMapping(typeService.page(pageModel, Wrappers.query(authority)), AuthorityVO.class);
		return ApiRestResult.ok(page).build();
	}


	/**
	 * 通过ID查询系统权限信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "通过ID查询权限", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:authority:fetch')")
	@ApiOperation(value = "通过ID查询权限", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 5)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<AuthorityVO> getById(@PathVariable String id) {
		// 转换为VO
		AuthorityVO authority = mapping(typeService.getById(id), new AuthorityVO());
		return ApiRestResult.ok(authority).build();
	}

	/**
	 * 系统权限唯一验证
	 *
	 * @param value {@link SysDictItemEntity} value
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "唯一权限验证", action = FETCH, platform = Platform.MANAGE)
	@ApiOperation(value = "唯一权限验证", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 6)
	@PreAuthorize("hasAuthority('manage:interface:authority:unique')")
	@GetMapping(value = "unique", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Boolean> unique(SysAuthorityTypeEntity value) {
		return ApiRestResult.ok(typeService.unique(value)).build();
	}

	/**
	 * 添加
	 * @param authority {@link SysAuthorizeItemEntity} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "新增权限项", action = ADD, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:authority:add')")
	@ApiOperation(value = "新增权限项", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 7)
	@PostMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult addItem(@Validated(value = {AddGroup.class}) @RequestBody SysAuthorizeItemEntity authority) {
		return ApiRestResult.ok(itemService.save(authority)).build();
	}


	/**
	 * 修改操作权限
	 *
	 * @param authority {@link SysAuthorizeItemEntity} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "修改权限项", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:authority:update')")
	@ApiOperation(value = "修改权限项", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 8)
	@PutMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult updateItemById(
			@Validated(value = {UpdateGroup.class}) @RequestBody SysAuthorizeItemEntity authority) {
		return ApiRestResult.ok(itemService.updateById(authority)).build();
	}

	/**
	 * 根据操作权限ID删除并清除缓存
	 *
	 * @param ids {@link String} ids
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "根据ID删除权限项", action = REMOVE, platform = Platform.MANAGE)
	@ApiOperation(value = "根据ID删除权限项", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 9)
	@DeleteMapping(value = "item/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:authority:remove')")
	public ApiRestResult removeItemByIds(@PathVariable String ids) {
		return ApiRestResult.ok(itemService.removeByIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT))))
				.build();
	}

	/**
	 * 查询操作权限信息
	 *
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "查询权限项列表", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:authority:fetch')")
	@ApiOperation(value = "查询权限项列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 10)
	@GetMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<List<AuthorizeItemVO>> getItemList(SysAuthorizeItemEntity authority) {
		// 转换为VO
		List<AuthorizeItemVO> list = listMapping(itemService.list(Wrappers.query(authority)), AuthorizeItemVO.class);
		return ApiRestResult.ok(list).build();
	}


	/**
	 * 通过ID查询操作权限信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "根据ID查询权限项", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:authority:fetch')")
	@ApiOperation(value = "根据ID查询权限项", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 11)
	@GetMapping(value = "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<AuthorizeItemVO> getItemById(@PathVariable String id) {
		// 转换为VO
		AuthorizeItemVO authority = mapping(itemService.getById(id), new AuthorizeItemVO());
		return ApiRestResult.ok(authority).build();
	}

	/**
	 * 操作权限唯一验证
	 *
	 * @param value {@link SysDictItemEntity} value
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "唯一权限项验证", action = FETCH, platform = Platform.MANAGE)
	@ApiOperation(value = "唯一权限项验证", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 12)
	@PreAuthorize("hasAuthority('manage:interface:authority:fetch')")
	@GetMapping(value = "item/unique", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Boolean> uniqueItem(SysAuthorizeItemEntity value) {
		return ApiRestResult.ok(itemService.unique(value)).build();
	}


	/**
	 *	注入操作权限项业务接口
	 */
	private final ISysAuthorizeItemService itemService;
	/**
	 *	注入系统权限业务接口
	 */
	private final ISysAuthorityTypeService typeService;


	public SysAuthorityResource(ISysAuthorizeItemService itemService, ISysAuthorityTypeService iSysModuleService) {
		this.itemService = itemService;
		this.typeService = iSysModuleService;
	}
}

