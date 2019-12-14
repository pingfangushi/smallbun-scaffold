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
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import cn.smallbun.scaffold.framework.web.BaseResource;
import cn.smallbun.scaffold.manage.constant.ManageConstant;
import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import cn.smallbun.scaffold.manage.entity.SysGroupEntity;
import cn.smallbun.scaffold.manage.enums.GroupStatus;
import cn.smallbun.scaffold.manage.pojo.GroupVO;
import cn.smallbun.scaffold.manage.service.ISysGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static cn.smallbun.scaffold.framework.logger.enmus.Operate.*;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.listMapping;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.mapping;
import static cn.smallbun.scaffold.framework.mybatis.utils.NodeHelp.getNodeList;
import static cn.smallbun.scaffold.manage.web.SysGroupResource.API;

/**
 * <p>
 * 系统组织表 前端控制器，统一使用 REST风格
 *
 * GET（FETCH）：从服务器取出资源（一项或多项）。
 * POST（CREATE）：在服务器新建一个资源。
 * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
 * PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
 *
 * 所有的注入全部都放到最下面 （强制）
 *
 * 注意，Resource 不能做任何业务逻辑处理（强制）
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Validated
@RestController
@Api(tags = API)
@Logger(module = API)
@RequestMapping(ManageConstant.MANAGE_API_PATH + "/group")
public class SysGroupResource extends BaseResource {

	final static String API = "组织组织API";

	/**
	 * 添加
	 * @param org {@link GroupVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "新增组织", action = ADD, platform = Platform.MANAGE)
	@ApiOperation(value = "新增组织", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 1)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:group:add')")
	public ApiRestResult<Boolean> add(@Validated(value = {AddGroup.class}) @RequestBody SysGroupEntity org) {
		return new ApiRestResult<Boolean>().result(sysOrgService.save(org)).build();
	}

	/**
	 * 修改组织
	 *
	 * @param org {@link GroupVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "修改组织", action = UPDATE, platform = Platform.MANAGE)
	@ApiOperation(value = "修改组织", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 2)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:group:update')")
	public ApiRestResult<Boolean> updateById(@Validated(value = {UpdateGroup.class}) @RequestBody SysGroupEntity org) {
		return new ApiRestResult<Boolean>().result(sysOrgService.updateById(org)).build();
	}

	/**
	 * 根据组织ID删除并清除缓存
	 *
	 * @param ids {@link String} ids
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "根据ID删除组织", action = REMOVE, platform = Platform.MANAGE)
	@ApiOperation(value = "根据ID删除组织", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 3)
	@DeleteMapping(value = "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:group:remove')")
	public ApiRestResult removeByIds(@PathVariable String ids) {
		return new ApiRestResult<>()
				.result(sysOrgService.removeByIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)))).build();
	}


	/**
	 * List
	 *
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "查询组织列表", action = FETCH, platform = Platform.MANAGE)
	@ApiOperation(value = "查询组织列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 4)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:group:fetch')")
	public ApiRestResult<List<GroupVO>> getList(@Valid SysGroupEntity org) {
		QueryWrapper<SysGroupEntity> query = Wrappers.query(org);
		query.orderByAsc("sort_");
		List<GroupVO> list = listMapping(sysOrgService.list(query), GroupVO.class);
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(this::fillingVo);
		}
		return new ApiRestResult<List<GroupVO>>().result(getNodeList(list)).build();
	}

	/**
	 * Tree
	 *
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "查询组织树结构", action = FETCH, platform = Platform.MANAGE)
	@ApiOperation(value = "查询组织树结构", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 5)
	@GetMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:group:fetch')")
	public ApiRestResult<List<GroupVO>> getTree(@Valid SysGroupEntity org) {
		return getList(org);
	}

	/**
	 * 通过ID查询组织信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "根据ID查询组织", action = FETCH, platform = Platform.MANAGE)
	@ApiOperation(value = "根据ID查询组织", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 6)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:group:fetch')")
	public ApiRestResult<GroupVO> getById(@PathVariable String id) {
		return new ApiRestResult<GroupVO>().result(fillingVo(mapping(sysOrgService.getById(id), new GroupVO())))
				.build();
	}

	/**
	 * 组织唯一验证
	 *
	 * @param value {@link SysDictItemEntity} value
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "唯一组织验证", action = FETCH, platform = Platform.MANAGE)
	@ApiOperation(value = "唯一组织验证", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 7)
	@GetMapping(value = "unique", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:group:unique')")
	public ApiRestResult<Boolean> unique(SysGroupEntity value) {
		return new ApiRestResult<Boolean>().result(sysOrgService.unique(value)).build();
	}

	/**
	 * 根据ID更新角色类型状态
	 * @param id id
	 * @param status 状态
	 * @return 结果
	 */
	@Logger(feature = "根据ID更新组织类型状态", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:group:update')")
	@ApiOperation(value = "根据ID更新组织类型状态", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 9)
	@PutMapping(value = "/{id}/status/{status}")
	public ApiRestResult updateStatusById(@PathVariable String id, @PathVariable GroupStatus status) {
		return new ApiRestResult<>().result(sysOrgService.updateStatusById(id, status)).build();
	}

	/**
	 * 填充VO字段
	 * @param vo {@link GroupVO}
	 * @return {@link GroupVO}
	 */
	private GroupVO fillingVo(GroupVO vo) {
		if (!Objects.isNull(vo)) {
			vo.setValue(vo.getId());
			vo.setTitle(vo.getName());
			vo.setKey(vo.getId());
			return vo;
		}
		return null;
	}

	/**
	 *注入组织业务接口
	 */
	private final ISysGroupService sysOrgService;

	public SysGroupResource(ISysGroupService sysOrgService) {
		this.sysOrgService = sysOrgService;
	}
}
