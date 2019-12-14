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
import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import cn.smallbun.scaffold.manage.entity.SysDictTypeEntity;
import cn.smallbun.scaffold.manage.enums.DictDefault;
import cn.smallbun.scaffold.manage.enums.DictStatus;
import cn.smallbun.scaffold.manage.pojo.DictTypeVO;
import cn.smallbun.scaffold.manage.pojo.DictValueVO;
import cn.smallbun.scaffold.manage.service.ISysDictItemService;
import cn.smallbun.scaffold.manage.service.ISysDictTypeService;
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
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.mapping;
import static cn.smallbun.scaffold.framework.mybatis.utils.MappingHelp.pageMapping;
import static cn.smallbun.scaffold.manage.constant.ManageConstant.MANAGE_API_PATH;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Validated
@RestController
@Logger(module = "数据字典API")
@RequestMapping(MANAGE_API_PATH + "/dict")
@Api(tags = "数据字典API")
public class SysDictResource extends BaseResource {
	/**
	 * 添加
	 * @param type {@link DictTypeVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "新增字典类型", action = ADD, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:add')")
	@ApiOperation(value = "新增字典类型", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 1)
	@PostMapping(value = "type", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult add(@Validated(value = {AddGroup.class}) @RequestBody SysDictTypeEntity type) {
		return new ApiRestResult<>().result(typeService.save(type)).build();
	}

	/**
	 * 修改字典类型
	 *
	 * @param type {@link DictTypeVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "修改字典类型", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:update')")
	@ApiOperation(value = "修改字典类型", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 2)
	@PutMapping(value = "type", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult updateById(@Validated(value = {UpdateGroup.class}) @RequestBody SysDictTypeEntity type) {
		return new ApiRestResult<>().result(typeService.updateById(type)).build();
	}

	/**
	 * 根据ID更新字典类型状态
	 * @param id id
	 * @param status 状态
	 * @return 结果
	 */
	@Logger(feature = "根据ID更新字典类型状态", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:update')")
	@ApiOperation(value = "根据ID更新字典类型状态", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 3)
	@PutMapping(value = "type/{id}/status/{status}")
	public ApiRestResult updateStatusById(@PathVariable String id, @PathVariable DictStatus status) {
		return new ApiRestResult<>().result(typeService.updateStatusById(id, status)).build();
	}

	/**
	 * 根据字典类型ID删除并清除缓存
	 *
	 * @param ids {@link String} ids
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "根据ID删除字典类型", action = REMOVE, platform = Platform.MANAGE)
	@ApiOperation(value = "根据ID删除字典类型", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 4)
	@DeleteMapping(value = "type/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:dict:remove')")
	public ApiRestResult removeTypeByIds(@PathVariable String ids) {
		return new ApiRestResult<>()
				.result(typeService.removeByIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)))).build();
	}

	/**
	 * 分页查询字典类型信息
	 *
	 * @param pageModel {@link PageModel} 对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "分页查询字典类型列表", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:fetch')")
	@ApiOperation(value = "分页查询字典类型列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 5)
	@GetMapping(value = "type", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Page<DictTypeVO>> getPage(PageModel pageModel, SysDictTypeEntity type) {
		// 转换为VO
		Page<DictTypeVO> page = pageMapping(typeService.page(pageModel, Wrappers.query(type)), DictTypeVO.class);
		return new ApiRestResult<Page<DictTypeVO>>().result(page).build();
	}

	/**
	 * 通过ID查询字典类型信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "通过ID查询字典类型", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:fetch')")
	@ApiOperation(value = "通过ID查询字典类型", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 6)
	@GetMapping(value = "type/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<DictTypeVO> getTypeById(@PathVariable String id) {
		// 转换为VO
		DictTypeVO dictType = mapping(typeService.getById(id), new DictTypeVO());
		return new ApiRestResult<DictTypeVO>().result(dictType).build();
	}

	/**
	 * 字典类型唯一验证
	 *
	 * @param value {@link SysDictItemEntity} value
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "唯一字典类型验证", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:unique')")
	@ApiOperation(value = "唯一字典类型验证", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 7)
	@GetMapping(value = "type/unique", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Boolean> unique(SysDictTypeEntity value) {
		return new ApiRestResult<Boolean>().result(typeService.unique(value)).build();
	}

	/**
	 * 添加
	 * @param value {@link DictValueVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "新增字典值", action = ADD, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:add')")
	@ApiOperation(value = "新增字典值", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 8)
	@PostMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult add(@Validated(value = {AddGroup.class}) @RequestBody SysDictItemEntity value) {
		return new ApiRestResult<>().result(valueService.save(value)).build();
	}

	/**
	 * 修改字典值
	 *
	 * @param value {@link DictValueVO} 添加对象
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "修改字典值", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:update')")
	@ApiOperation(value = "修改字典值", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 9)
	@PutMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult updateById(@Validated(value = {UpdateGroup.class}) @RequestBody SysDictItemEntity value) {
		return new ApiRestResult<>().result(valueService.updateById(value)).build();
	}

	/**
	 * 根据字典值ID删除并清除缓存
	 *
	 * @param ids {@link String} ids
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@DemoEnvironment
	@Logger(feature = "根据ID删除字典值", action = REMOVE, platform = Platform.MANAGE)
	@ApiOperation(value = "根据ID删除字典值", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 10)
	@DeleteMapping(value = "item/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('manage:interface:dict:remove')")
	public ApiRestResult removeValueByIds(@PathVariable String ids) {
		return new ApiRestResult<>()
				.result(valueService.removeByIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT))));
	}

	/**
	 * 分页查询字典值信息
	 *
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "分页查询字典值列表", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:fetch')")
	@ApiOperation(value = "分页查询字典值列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 11)
	@GetMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Page<DictValueVO>> getPage(PageModel model, SysDictItemEntity value) {
		Page<DictValueVO> page = pageMapping(valueService.page(model, Wrappers.query(value)), DictValueVO.class);
		return new ApiRestResult<Page<DictValueVO>>().result(page).build();
	}


	/**
	 * 通过ID查询字典值信息
	 *
	 * @param id {@link String} id
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "根据ID查询字典值", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:fetch')")
	@ApiOperation(value = "根据ID查询字典值", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 12)
	@GetMapping(value = "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<DictValueVO> getById(@PathVariable String id) {
		// 转换为VO
		DictValueVO dictType = mapping(valueService.getById(id), new DictValueVO());
		return new ApiRestResult<DictValueVO>().result(dictType).build();
	}

	/**
	 * 字典值唯一验证
	 *
	 * @param value {@link SysDictItemEntity} value
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@Logger(feature = "唯一字典值验证", action = FETCH, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:unique')")
	@ApiOperation(value = "唯一字典值验证", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 13)
	@GetMapping(value = "item/unique", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<Boolean> unique(SysDictItemEntity value) {
		return new ApiRestResult<Boolean>().result(valueService.unique(value)).build();
	}

	/**
	 * 根据ID设置字典默认值
	 * @param id id
	 * @param isDefault 是否是默认值
	 * @return 结果
	 */
	@Logger(feature = "根据ID更新字典值是否为默认", action = UPDATE, platform = Platform.MANAGE)
	@PreAuthorize("hasAuthority('manage:interface:dict:update')")
	@ApiOperation(value = "根据ID更新字典值是否为默认", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 14)
	@PutMapping(value = "item/{id}/default/{isDefault}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult updateDefaultById(@PathVariable String id, @PathVariable DictDefault isDefault) {
		// 只有设置默认值的时候进行验证
		if (DictDefault.YES.equals(isDefault)) {
			if (valueService.isExistDefault(valueService.getById(id).getType())) {
				return new ApiRestResult<>().status("S2000").message("该类型下已设置默认项,请取消后在进行设置!").build();
			}
		}
		return new ApiRestResult<>().result(valueService.updateIsDefaultById(id, isDefault)).build();
	}


	/**
	 * 查询全部的字典，主要用于前端字典值展示,通过type获取到值，循环值渲染组件
	 * {
	 *   "values": [
	 *      {
	 *       "label": "身份证",
	 *       "value": "0"
	 *      }
	 *     ],
	 *   "type": "card_code"
	 * },
	 * @return {@link ApiRestResult} 通用返回对象
	 */
	@ApiOperation(value = "特定格式字典数据", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 15)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiRestResult<List<ISysDictTypeService.Dict>> query() {
		return new ApiRestResult<List<ISysDictTypeService.Dict>>().result(typeService.dict()).build();
	}


	/**
	 *注入字典数据业务接口
	 */
	private final ISysDictItemService valueService;
	/**
	 *注入字典类型业务接口
	 */
	private final ISysDictTypeService typeService;

	public SysDictResource(ISysDictTypeService typeService, ISysDictItemService valueService) {
		this.typeService = typeService;
		this.valueService = valueService;
	}
}
