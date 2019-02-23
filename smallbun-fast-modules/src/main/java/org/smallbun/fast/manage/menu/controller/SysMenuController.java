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

package org.smallbun.fast.manage.menu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.common.PageFactory;
import org.smallbun.fast.common.utils.AutoMapperUtil;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.menu.service.SysMenuService;
import org.smallbun.fast.manage.menu.vo.SysMenuVO;
import org.smallbun.framework.annotation.AutoQueryDictValue;
import org.smallbun.framework.annotation.DemoEnvironment;
import org.smallbun.framework.annotation.LogAnnotation;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.constant.OperateLogConstant;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.smallbun.framework.constant.ERROR_MSG_CONSTANT.ID_NOT_BLANK_MSG;
import static org.smallbun.framework.constant.OperateLogConstant.*;
import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 系统菜单控制器
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
@Validated
@RestController
@RequestMapping(value = "/menu")
public class SysMenuController extends BaseController {

	/**
	 * 模块名称
	 */
	private static final String MODEL = "字典类型";
	/**
	 * HTML页面路径前缀
	 */
	private static final String HTML_PREFIX = "/modules/manage/menu/";


	@Autowired
	public SysMenuController(SysMenuService menuService) {
		this.menuService = menuService;
	}

	@ModelAttribute
	public SysMenuVO model(HttpServletRequest request) {
		return menuService.model(request);
	}

	/**
	 * 菜单页面
	 * @return ModelAndView
	 */
	@RequestMapping(value = {"/", ""})
	public ModelAndView menu() {
		return new ModelAndView(HTML_PREFIX + "menu_list.html");
	}

	/**
	 * 菜单页面
	 * @return ModelAndView
	 */
	@RequestMapping(value = "form")
	@PreAuthorize("hasAuthority('manage:dict:add') or hasAuthority('manage:dict:edit')")
	public ModelAndView form(SysMenuVO menu, Model model) {
		model.addAttribute("menu", menu);
		return new ModelAndView(HTML_PREFIX + "menu_form.html");
	}

	/**
	 * 保存或更新
	 * @param menu 类型实体对象
	 * @return AjaxResult
	 */
	@DemoEnvironment
	@PostMapping(value = "/saveOrUpdate")
	@PreAuthorize("hasAuthority('manage:dict:add') or hasAuthority('manage:dict:edit')")
	@LogAnnotation(model = MODEL, action = OperateLogConstant.ADD_UPDATE)
	public AjaxResult saveOrUpdate(@Valid SysMenuVO menu) {
		return AjaxResult.builder().result(menuService.saveOrUpdate(menu)).build();
	}

	/**
	 * 删除单条记录
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@DemoEnvironment
	@PostMapping(value = "/removeById")
	@PreAuthorize("hasAuthority('manage:dict:del')")
	@LogAnnotation(model = DEL_MODEL + MODEL, action = OperateLogConstant.DEL)
	public AjaxResult removeById(
			@NotBlank(message = ID_NOT_BLANK_MSG) @RequestParam(value = "id", required = false) String id) {
		return AjaxResult.builder().result(menuService.removeById(id)).build();
	}

	/**
	 * 删除多条记录
	 * @param ids 主键ID集合
	 * @return AjaxResult
	 */
	@DemoEnvironment
	@PostMapping(value = "/removeByIds")
	@PreAuthorize("hasAuthority('manage:dict:del')")
	@LogAnnotation(model = DEL_MODEL + MODEL, action = OperateLogConstant.DEL)
	public AjaxResult removeByIds(
			@NotBlank(message = ID_NOT_BLANK_MSG) @RequestParam(value = "ids", required = false) List<String> ids) {
		return AjaxResult.builder().result(menuService.removeByIds(ids)).build();
	}


	/**
	 * 查询所有的菜单
	 *
	 * @return AjaxResult
	 */
	@AutoQueryDictValue
	@RequestMapping(value = "/page")
	@LogAnnotation(model = MODEL + SELECT_PAGE_MODEL, action = OperateLogConstant.SELECT_PAGE)
	public PageableResult page(Page<SysMenuEntity> page, SysMenuVO vo) {
		return PageableResult.builder()
				.page(menuService.page(new PageFactory<SysMenuEntity>().defaultPage(page), new QueryWrapper<>(vo)))
				.build();
	}

	/**
	 * 查询所有的菜单
	 *
	 * @return AjaxResult
	 */
	@AutoQueryDictValue
	@PostMapping(value = "/list")
	@LogAnnotation(model = MODEL + SELECT_LIST_MODEL, action = OperateLogConstant.SELECT_PAGE)
	public AjaxResult list(SysMenuVO vo) {
		return AjaxResult.builder().result(excludeZtreeChildrenField(
				mappingList(menuService.list(new QueryWrapper<SysMenuEntity>(vo).orderByAsc("sort")), new ArrayList<>(),
						SysMenuVO.class))).build();
	}


	/**
	 * 获取tree
	 * @return {@link AjaxResult}
	 */
	@PostMapping(value = "/tree")
	public AjaxResult tree() {
		return AjaxResult.builder().result(AutoMapperUtil
				.mappingTreeList(menuService.tree(new QueryWrapper<SysMenuEntity>().orderByAsc("sort")),
						new ArrayList<>(), SysMenuVO.class)).build();
	}

	/**
	 * 唯一
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = UNIQUE)
	public AjaxResult unique(SysMenuVO vo) {
		return AjaxResult.builder().result(menuService.unique(vo)).build();
	}


	/**
	 * 注入系统菜单业务逻辑层
	 */
	private final SysMenuService menuService;
}
