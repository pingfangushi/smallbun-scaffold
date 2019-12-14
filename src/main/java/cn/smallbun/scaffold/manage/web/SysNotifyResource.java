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
import cn.smallbun.scaffold.manage.pojo.NotifyVO;
import cn.smallbun.scaffold.manage.service.ISysNotifyService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.smallbun.scaffold.framework.logger.enmus.Operate.*;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.*;

/**
 * <p>
 * 系统通知通告表 前端控制器
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-15
 */
@Logger(module = SysNotifyResource.API)
@Validated
@RestController
@RequestMapping(ManageConstant.MANAGE_API_PATH + "/notify")
@Api(tags = SysNotifyResource.API)
public class SysNotifyResource extends BaseResource {

	final static String API = "通知通告API";

	/**
	 * 添加
	 * @param notify {@link NotifyVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "新增通知公告", action = ADD, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:notify:add')")
	@ApiOperation(value = "新增通知公告", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult add(@Validated(value = {AddGroup.class}) @RequestBody NotifyVO notify) {
		return new ApiRestResult<>().result(iSysNotifyService.save(notify)).build();
	}

	/**
	 * 修改通知公告
	 *
	 * @param notify {@link NotifyVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "修改通知公告", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:notify:update')")
	@ApiOperation(value = "修改通知公告", produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult updateById(@Validated(value = {UpdateGroup.class}) @RequestBody NotifyVO notify) {
		return new ApiRestResult<>().result(iSysNotifyService.updateById(notify)).build();
	}

	/**
	 * 根据通知公告ID删除并清除缓存
	 *
	 * @param ids {@link String} ids
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "根据通知公告ID删除信息", action = REMOVE, platform = Platform.MANAGE)
	@ApiOperation(value = "根据通知公告ID删除信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:notify:remove')")
	public ApiRestResult removeByIds(@PathVariable String ids) {
		return new ApiRestResult<>()
				.result(iSysNotifyService.removeByIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)))).build();
	}

	/**
	 * 分页查询岗位信息
	 *
	 * @param pageModel {@link PageModel} 对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "分页查询岗位信息", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:notify:fetch')")
	@ApiOperation(value = "分页查询岗位信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Page<NotifyVO>> getPage(PageModel pageModel, NotifyVO notify) {
		// 转换为VO
		Page<NotifyVO> page = pageMapping(iSysNotifyService.page(pageModel, Wrappers.query(notify)), NotifyVO.class);
		return new ApiRestResult<Page<NotifyVO>>().result(page).build();
	}

	/**
	 * List
	 *
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "查询用户信息", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:notify:fetch')")
	@ApiOperation(value = "查询用户信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<List<NotifyVO>> getList(@Valid NotifyVO notify) {
		// 转换为VO
		List<NotifyVO> list = listMapping(iSysNotifyService.list(Wrappers.query(notify)), NotifyVO.class);
		return new ApiRestResult<List<NotifyVO>>().result(list).build();
	}

	/**
	 * 通过ID查询通知公告信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "通过通知公告ID查询信息", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:notify:fetch')")
	@ApiOperation(value = "通过通知公告ID查询信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<NotifyVO> getById(@PathVariable String id) {
		// 转换为VO
		NotifyVO sysLog = mapping(iSysNotifyService.getById(id), new NotifyVO());
		return new ApiRestResult<NotifyVO>().result(sysLog).build();
	}

	/**
	 *	注入通知公告业务接口
	 */
	private final ISysNotifyService iSysNotifyService;

	public SysNotifyResource(ISysNotifyService iSysNotifyService) {
		this.iSysNotifyService = iSysNotifyService;
	}
}
