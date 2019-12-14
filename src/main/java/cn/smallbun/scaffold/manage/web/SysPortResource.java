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
import cn.smallbun.scaffold.framework.logger.annotation.Logger;
import cn.smallbun.scaffold.framework.logger.enmus.Platform;
import cn.smallbun.scaffold.framework.mybatis.page.Page;
import cn.smallbun.scaffold.framework.mybatis.page.PageModel;
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import cn.smallbun.scaffold.framework.web.BaseResource;
import cn.smallbun.scaffold.manage.constant.ManageConstant;
import cn.smallbun.scaffold.manage.pojo.PortVO;
import cn.smallbun.scaffold.manage.service.ISysPortService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.smallbun.scaffold.framework.logger.enmus.Operate.*;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.mapping;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.pageMapping;
import static cn.smallbun.scaffold.manage.web.SysPortResource.API;

/**
 * <p>
 * 系统员工岗位表 前端控制器
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Validated
@RestController
@Api(tags = API)
@Logger(module = API)
@RequestMapping(ManageConstant.MANAGE_API_PATH + "/port")
public class SysPortResource extends BaseResource {

	final static String API = "员工岗位API";

	/**
	 * 添加
	 * @param port {@link PortVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "新增岗位", action = ADD, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:port:add')")
	@ApiOperation(value = "新增岗位", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult add(@Validated(value = {AddGroup.class}) @RequestBody PortVO port) {
		return new ApiRestResult<>().result(iSysPortService.save(port)).build();
	}

	/**
	 * 修改岗位
	 *
	 * @param port {@link PortVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "修改岗位", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:port:update')")
	@ApiOperation(value = "修改岗位", produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult updateById(@Validated(value = {UpdateGroup.class}) @RequestBody PortVO port) {
		return new ApiRestResult<>().result(iSysPortService.updateById(port)).build();
	}

	/**
	 * 根据岗位ID删除并清除缓存
	 *
	 * @param ids {@link String} ids
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "根据岗位ID删除信息", action = REMOVE, platform = Platform.MANAGE)
	@ApiOperation(value = "根据岗位ID删除信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:port:remove')")
	public ApiRestResult removeByIds(@PathVariable String ids) {
		return new ApiRestResult<>()
				.result(iSysPortService.removeByIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)))).build();
	}

	/**
	 * 分页查询岗位信息
	 *
	 * @param pageModel {@link PageModel} 对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "分页查询岗位信息", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:port:fetch')")
	@ApiOperation(value = "分页查询岗位信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Page<PortVO>> getPage(PageModel pageModel, PortVO port) {
		// 转换为VO
		Page<PortVO> page = pageMapping(iSysPortService.page(pageModel, Wrappers.query(port)), PortVO.class);
		return new ApiRestResult<Page<PortVO>>().result(page).build();
	}


	/**
	 * 通过ID查询岗位信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "通过岗位ID查询信息", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:port:fetch')")
	@ApiOperation(value = "通过岗位ID查询信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<PortVO> getById(@PathVariable String id) {
		// 转换为VO
		PortVO port = mapping(iSysPortService.getById(id), new PortVO());
		return new ApiRestResult<PortVO>().result(port).build();
	}

	/**
	 *	注入岗位业务接口
	 */
	private final ISysPortService iSysPortService;

	public SysPortResource(ISysPortService iSysPortService) {
		this.iSysPortService = iSysPortService;
	}
}
