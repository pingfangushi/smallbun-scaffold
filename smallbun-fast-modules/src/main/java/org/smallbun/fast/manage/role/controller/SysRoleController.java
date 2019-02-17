/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.fast.manage.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.common.PageFactory;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.service.SysRoleService;
import org.smallbun.fast.manage.role.vo.SysRoleVO;
import org.smallbun.framework.annotation.AutoQueryDictValue;
import org.smallbun.framework.annotation.DemoEnvironment;
import org.smallbun.framework.annotation.LogAnnotation;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.constant.OperateLogConstant;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 角色管理Controller
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/9 22:01
 */
@Validated
@RestController
@RequestMapping(value = "/role")
public class SysRoleController extends BaseController {
	public SysRoleController(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	@ModelAttribute
	protected SysRoleVO model(HttpServletRequest request) {
		return sysRoleService.model(request);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping(value = {"", "/"})
	public ModelAndView role() {
		return new ModelAndView("modules/manage/role/role_list.html");
	}

	/**
	 * form表单
	 * @return 地址
	 */
	@LogAnnotation(model = "", action = OperateLogConstant.OPEN_VIEW_FORM)
	@GetMapping(value = "/form")
	@PreAuthorize("hasAuthority('manage:role:add') or hasAuthority('manage:role:edit')")
	public ModelAndView form(SysRoleVO vo, Model model) {
		model.addAttribute("role", vo);
		return new ModelAndView("modules/manage/role/role_form.html");
	}

	/**
	 * 保存或更新
	 * @param vo 实体对象
	 * @return AjaxResult
	 */
	@LogAnnotation(model = "", action = OperateLogConstant.ADD_UPDATE)
	@DemoEnvironment
	@RequestMapping(value = "/saveOrUpdate")
	@PreAuthorize("hasAuthority('manage:role:add') or hasAuthority('manage:role:edit')")
	public AjaxResult saveOrUpdate(@Valid SysRoleVO vo) {
		return AjaxResult.builder().result(sysRoleService.saveOrUpdate(vo)).build();
	}

	/**
	 * 删除单条记录
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@LogAnnotation(model = "", action = OperateLogConstant.DEL)
	@DemoEnvironment
	@PreAuthorize("hasAuthority('manage:role:del')")
	@PostMapping(value = "/removeById")
	public AjaxResult removeById(
			@NotBlank(message = "id不能为空") @RequestParam(value = "id", required = false) String id) {
		return AjaxResult.builder().result(sysRoleService.removeById(id)).build();
	}

	/**
	 * 删除多条记录
	 * @param ids 主键ID集合
	 * @return AjaxResult
	 */
	@LogAnnotation(model = "", action = OperateLogConstant.DEL)
	@DemoEnvironment
	@PreAuthorize("hasAuthority('manage:role:del')")
	@PostMapping(value = "/removeByIds")
	public AjaxResult saveOrUpdate(
			@NotBlank(message = "id不能为空") @RequestParam(value = "ids", required = false) List<String> ids) {
		return AjaxResult.builder().result(sysRoleService.removeByIds(ids)).build();
	}

	/**
	 * 分页查询
	 * @return PageableResult
	 */
	@LogAnnotation(model = "", action = OperateLogConstant.SELECT_PAGE)
	@AutoQueryDictValue
	@PostMapping(value = "/page")
	public PageableResult page(Page<SysRoleEntity> page, SysRoleVO vo) {
		return PageableResult.builder().page(pageVOFilling(
				sysRoleService.page(new PageFactory<SysRoleEntity>().defaultPage(page), new QueryWrapper<>(vo)),
				SysRoleVO.class)).build();
	}

	/**
	 * 查询全部记录
	 * @return SysDictTypeEntity
	 */
	@LogAnnotation(model = "", action = OperateLogConstant.SELECT_LIST)
	@AutoQueryDictValue
	@PostMapping(value = "/list")
	public AjaxResult list() {
		return AjaxResult.builder()
				.result(mappingList(sysRoleService.list(new QueryWrapper<>()), new ArrayList<>(), SysRoleVO.class))
				.build();
	}

	/**
	 * 唯一
	 * @param vo vo
	 * @return AjaxResult
	 */
	@PostMapping(value = UNIQUE)
	public AjaxResult unique(SysRoleVO vo) {
		return AjaxResult.builder().result(sysRoleService.unique(vo)).build();
	}

	/**
	 * 注入用户角色service接口
	 */
	private final SysRoleService sysRoleService;
}
