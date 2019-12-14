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
import cn.smallbun.scaffold.framework.demo.annotation.DemoEnvironment;
import cn.smallbun.scaffold.framework.logger.annotation.Logger;
import cn.smallbun.scaffold.framework.mybatis.page.Page;
import cn.smallbun.scaffold.framework.mybatis.page.PageModel;
import cn.smallbun.scaffold.framework.web.BaseResource;
import cn.smallbun.scaffold.manage.entity.SysLoggerLoginEntity;
import cn.smallbun.scaffold.manage.entity.SysLoggerOperateEntity;
import cn.smallbun.scaffold.manage.pojo.LoggerLoginVO;
import cn.smallbun.scaffold.manage.pojo.LoggerOperateVO;
import cn.smallbun.scaffold.manage.service.ISysLoggerLoginService;
import cn.smallbun.scaffold.manage.service.ISysLoggerOperateService;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.mapping;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.pageMapping;
import static cn.smallbun.scaffold.manage.constant.ManageConstant.MANAGE_API_PATH;
import static cn.smallbun.scaffold.manage.web.SysLoggerResource.API;

/**
 * <p>
 * 系统登录日志表 前端控制器
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-02
 */
@Logger(module = API)
@Validated
@RestController
@RequestMapping(MANAGE_API_PATH + "/logger")
@Api(tags = API)
public class SysLoggerResource extends BaseResource {
	final static String API = "系统日志API";


	/**
	 * 清空系统登录日志
	 *
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@ApiOperation(value = "清空登录日志", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 1)
	@DeleteMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:logger:remove')")
	public ApiRestResult logicLoginRemoveAll() {
		boolean remove = iSysLoggerLoginService.update(new LambdaUpdateWrapper<SysLoggerLoginEntity>()
				.set(SysLoggerLoginEntity::getIsDeleted, config.getGlobalConfig().getDbConfig().getLogicDeleteValue()));
		return new ApiRestResult<>().result(remove).build();
	}

	/**
	 * 分页查询登录日志信息
	 *
	 * @param pageModel {@link PageModel} 对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@PreAuthorize("hasAuthority('manage:interface:logger:fetch')")
	@ApiOperation(value = "分页查询登录日志列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 2)
	@GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Page<LoggerLoginVO>> getLoginPage(PageModel pageModel, SysLoggerLoginEntity log) {
		// 转换为VO
		Page<LoggerLoginVO> page = pageMapping(iSysLoggerLoginService.page(pageModel, Wrappers.query(log)),
				LoggerLoginVO.class);
		return new ApiRestResult<Page<LoggerLoginVO>>().result(page).build();
	}


	/**
	 * 通过ID查询登录日志信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@PreAuthorize("hasAuthority('manage:interface:logger:fetch')")
	@ApiOperation(value = "根据ID查询登录日志", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 3)
	@GetMapping(value = "/login/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<LoggerLoginVO> getLoginById(@PathVariable String id) {
		// 转换为VO
		LoggerLoginVO log = mapping(iSysLoggerLoginService.getById(id), new LoggerLoginVO());
		return new ApiRestResult<LoggerLoginVO>().result(log).build();
	}

	/**
	 * 清空操作日志
	 *
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@ApiOperation(value = "清空操作日志", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 1)
	@DeleteMapping(value = "/operate", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:logger:remove')")
	public ApiRestResult logicOperateRemoveAll() {
		boolean remove = iSysLoggerOperateService.update(new LambdaUpdateWrapper<SysLoggerOperateEntity>()
				.set(SysLoggerOperateEntity::getIsDeleted,
						config.getGlobalConfig().getDbConfig().getLogicDeleteValue()));
		return new ApiRestResult<>().result(remove).build();
	}


	/**
	 * 分页查询操作日志信息
	 *
	 * @param pageModel {@link PageModel} 对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@PreAuthorize("hasAuthority('manage:interface:logger:fetch')")
	@ApiOperation(value = "分页查询操作日志列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 2)
	@GetMapping(value = "/operate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Page<LoggerOperateVO>> getOperatePage(PageModel pageModel, SysLoggerOperateEntity log) {
		// 转换为VO
		return new ApiRestResult<Page<LoggerOperateVO>>()
				.result(pageMapping(iSysLoggerOperateService.page(pageModel, Wrappers.query(log)),
						LoggerOperateVO.class)).build();
	}


	/**
	 * 通过ID查询操作日志信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@PreAuthorize("hasAuthority('manage:interface:logger:fetch')")
	@ApiOperation(value = "根据ID查询操作日志", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 3)
	@GetMapping(value = "/operate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<LoggerOperateVO> getOperateById(@PathVariable String id) {
		// 转换为VO
		LoggerOperateVO log = mapping(iSysLoggerOperateService.getById(id), new LoggerOperateVO());
		return new ApiRestResult<LoggerOperateVO>().result(log).build();
	}

	/**
	 *注入登录日志业务接口
	 */
	private final ISysLoggerLoginService iSysLoggerLoginService;
	/**
	 * ISysLoggerOperateService
	 */
	private final ISysLoggerOperateService iSysLoggerOperateService;
	/**
	 * MybatisPlusProperties
	 */
	private final MybatisPlusProperties config;

	public SysLoggerResource(ISysLoggerLoginService iSysLoggingLoginService,
			ISysLoggerOperateService iSysLoggerOperateService, MybatisPlusProperties mybatisPlusProperties) {
		this.iSysLoggerLoginService = iSysLoggingLoginService;
		this.iSysLoggerOperateService = iSysLoggerOperateService;
		this.config = mybatisPlusProperties;
	}
}
