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
import org.smallbun.framework.annotation.SystemLog;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 系统菜单控制器
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
@RestController
@RequestMapping(value = "/menu")
public class SysMenuController extends BaseController {
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
		return new ModelAndView("/modules/manage/menu/menu_list.html");
	}

	/**
	 * 菜单页面
	 * @return ModelAndView
	 */
	@RequestMapping(value = "form")
	public ModelAndView form(SysMenuVO menu, Model model) {
		model.addAttribute("menu", menu);
		return new ModelAndView("/modules/manage/menu/menu_form.html");
	}

	/**
	 * 获取当前用户具有的用户菜单
	 *
	 * @return AjaxResult
	 */
	@PostMapping(value = "/userMenus")
	public AjaxResult menus() {
		return AjaxResult.builder().result(menuService.userMenus()).build();
	}

	/**
	 * 保存或更新
	 * @param menu 类型实体对象
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@DemoEnvironment
	@PostMapping(value = "/saveOrUpdate")
	public AjaxResult saveOrUpdate(@Validated SysMenuVO menu) {
		return AjaxResult.builder().result(menuService.saveOrUpdate(menu)).build();
	}

	/**
	 * 删除单条记录
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@DemoEnvironment
	@PostMapping(value = "/removeById")
	public AjaxResult removeById(@NotNull @RequestParam(value = "id") String id) {
		return AjaxResult.builder().result(menuService.removeById(id)).build();
	}

	/**
	 * 删除多条记录
	 * @param ids 主键ID集合
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@DemoEnvironment
	@PostMapping(value = "/removeByIds")
	public AjaxResult saveOrUpdate(@NotNull @RequestParam(value = "ids") List<String> ids) {
		return AjaxResult.builder().result(menuService.removeByIds(ids)).build();
	}


	/**
	 * 查询所有的菜单
	 *
	 * @return AjaxResult
	 */
	@RequestMapping(value = "/page")
	@AutoQueryDictValue
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
	@SystemLog(value = "查询所有菜单")
	@PostMapping(value = "/list")
	@AutoQueryDictValue
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
