/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.smallbun.fast.manage.menu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.menu.service.SysMenuService;
import org.smallbun.fast.manage.menu.vo.SysMenuVO;
import org.smallbun.framework.annotation.SystemLog;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.toolkit.AutoMapperUtil.mapping;
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
	public SysMenuVO model(String id) {
		return StringUtils.isEmpty(id) ? new SysMenuVO() : mapping(menuService.getById(id), new SysMenuVO());
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
	@PostMapping(value = "/removeById")
	public AjaxResult removeById(@NotNull String id) {
		return AjaxResult.builder().result(menuService.removeById(id)).build();
	}

	/**
	 * 删除多条记录
	 * @param id 主键ID集合
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/removeByIds")
	public AjaxResult saveOrUpdate(@NotNull List<String> id) {
		return AjaxResult.builder().result(menuService.removeByIds(id)).build();
	}


	/**
	 * 查询所有的菜单
	 *
	 * @return AjaxResult
	 */
	@RequestMapping(value = "/page")
	public PageableResult page(IPage<SysMenuEntity> page, SysMenuVO vo) {
		return PageableResult.builder().page(menuService.page(page, new QueryWrapper<>(vo))).build();
	}

	/**
	 * 查询所有的菜单
	 *
	 * @return AjaxResult
	 */
	@SystemLog(value = "查询所有菜单")
	@PostMapping(value = "/list")
	public AjaxResult list(SysMenuVO vo) {
		return AjaxResult.builder()
				.result(mappingList(menuService.list(new QueryWrapper<>(vo)), new ArrayList<SysMenuVO>(),
						SysMenuVO.class)).build();
	}

	/**
	 * 注入系统菜单业务逻辑层
	 */
	private final SysMenuService menuService;
}
