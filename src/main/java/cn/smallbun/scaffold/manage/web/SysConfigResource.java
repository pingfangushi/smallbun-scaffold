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
import cn.smallbun.scaffold.manage.pojo.ConfigVO;
import cn.smallbun.scaffold.manage.service.ISysConfigService;
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
import static cn.smallbun.scaffold.manage.constant.ManageConstant.MANAGE_API_PATH;

/**
 * <p>
 * 系统参数配置配置表 前端控制器
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Logger(module = SysConfigResource.API)
@Validated
@RestController
@RequestMapping(MANAGE_API_PATH + "/config")
@Api(tags = SysConfigResource.API)
public class SysConfigResource extends BaseResource {
	final static String API = "参数配置API";

	/**
	 * 添加
	 * @param port {@link ConfigVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "新增参数配置", action = ADD, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:config:add')")
	@ApiOperation(value = "新增参数配置", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult add(@Validated(value = {AddGroup.class}) @RequestBody ConfigVO port) {
		return new ApiRestResult<>().result(sysConfigService.save(port)).build();
	}

	/**
	 * 修改参数配置
	 *
	 * @param port {@link ConfigVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "修改参数配置", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:config:update')")
	@ApiOperation(value = "修改参数配置", produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult updateById(@Validated(value = {UpdateGroup.class}) @RequestBody ConfigVO port) {
		return new ApiRestResult<>().result(sysConfigService.updateById(port)).build();
	}

	/**
	 * 根据参数配置ID删除并清除缓存
	 *
	 * @param ids {@link String} ids
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "根据参数配置ID删除信息", action = REMOVE, platform = Platform.MANAGE)
	@ApiOperation(value = "根据参数配置ID删除信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:config:remove')")
	public ApiRestResult removeByIds(@PathVariable String ids) {
		return new ApiRestResult<>()
				.result(sysConfigService.removeByIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)))).build();
	}

	/**
	 * 分页查询参数配置信息
	 *
	 * @param pageModel {@link PageModel} 对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "分页查询参数配置信息", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:config:fetch')")
	@ApiOperation(value = "分页查询参数配置信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Page<ConfigVO>> getPage(PageModel pageModel, ConfigVO config) {
		// 转换为VO
		Page<ConfigVO> page = pageMapping(sysConfigService.page(pageModel, Wrappers.query(config)), ConfigVO.class);
		return new ApiRestResult<Page<ConfigVO>>().result(page).build();
	}

	/**
	 * 通过ID查询参数配置信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "通过参数配置ID查询信息", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:config:fetch')")
	@ApiOperation(value = "通过参数配置ID查询信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<ConfigVO> getById(@PathVariable String id) {
		// 转换为VO
		ConfigVO config = mapping(sysConfigService.getById(id), new ConfigVO());
		return new ApiRestResult<ConfigVO>().result(config).build();
	}


	/**
	 *注入参数配置配置业务接口
	 */
	private final ISysConfigService sysConfigService;

	public SysConfigResource(ISysConfigService sysConfigService) {
		this.sysConfigService = sysConfigService;
	}
}
