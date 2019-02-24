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

package org.smallbun.fast.manage.dict.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.common.PageFactory;
import org.smallbun.fast.manage.dict.entity.SysDictValueEntity;
import org.smallbun.fast.manage.dict.service.SysDictValueService;
import org.smallbun.fast.manage.dict.vo.SysDictTypeVO;
import org.smallbun.fast.manage.dict.vo.SysDictValueVO;
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

import static org.smallbun.framework.constant.ErrorMsgConstant.ID_NOT_BLANK_MSG;
import static org.smallbun.framework.constant.OperateLogConstant.*;
import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 系统字典数据 前端控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@RestController
@Validated
@RequestMapping("/dict/value")
public class SysDictValueController extends BaseController {

	/**
	 * 模块名称
	 */
	private static final String MODEL = "字典类型";
	/**
	 * HTML页面路径前缀
	 */
	private static final String HTML_PREFIX = "/modules/manage/dict/";

	@Autowired
	public SysDictValueController(SysDictValueService sysDictValueService) {
		this.sysDictValueService = sysDictValueService;
	}

	@ModelAttribute
	protected SysDictValueVO model(HttpServletRequest request) {
		return sysDictValueService.model(request);
	}

	/**
	 * 返回list页面
	 * @return {@link ModelAndView}
	 */
	@GetMapping(value = {"", "/"})
	public ModelAndView list(SysDictValueVO vo, Model model) {
		model.addAttribute("vo", vo);
		return new ModelAndView(HTML_PREFIX + "dict_value_list.html");
	}

	/**
	 * form表单
	 * @return 地址
	 */
	@GetMapping(value = "/form")
	@PreAuthorize("hasAuthority('manage:dict:add') or hasAuthority('manage:dict:edit')")
	@LogAnnotation(model = MODEL, action = OperateLogConstant.OPEN_VIEW_FORM)
	public ModelAndView form(SysDictValueVO vo, Model model) {
		model.addAttribute("dictValue", vo);
		return new ModelAndView(HTML_PREFIX + "dict_value_form.html");
	}

	/**
	 * 保存或更新
	 * @param vo 类型实体对象
	 * @return AjaxResult
	 */
	@DemoEnvironment
	@PostMapping(value = "/saveOrUpdate")
	@PreAuthorize("hasAuthority('manage:dict:add') or hasAuthority('manage:dict:edit')")
	@LogAnnotation(model = MODEL, action = OperateLogConstant.ADD_UPDATE)
	public AjaxResult saveOrUpdate(@Valid SysDictValueVO vo) {
		return AjaxResult.builder().result(sysDictValueService.saveOrUpdate(vo)).build();
	}

	/**
	 * 删除单条记录
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@DemoEnvironment
	@GetMapping(value = "/removeById")
	@PreAuthorize("hasAuthority('manage:dict:del')")
	@LogAnnotation(model = DEL_MODEL + MODEL, action = OperateLogConstant.DEL)
	public AjaxResult removeById(
			@NotBlank(message = ID_NOT_BLANK_MSG) @RequestParam(value = "id", required = false) String id) {
		return AjaxResult.builder().result(sysDictValueService.removeById(id)).build();
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
	public AjaxResult saveOrUpdate(
			@NotBlank(message = ID_NOT_BLANK_MSG) @RequestParam(value = "ids", required = false) List<String> ids) {
		return AjaxResult.builder().result(sysDictValueService.removeByIds(ids)).build();
	}

	/**
	 * 分页查询
	 * @return PageableResult
	 */
	@PostMapping(value = "/page")
	@LogAnnotation(model = MODEL + SELECT_PAGE_MODEL, action = OperateLogConstant.SELECT_PAGE)
	public PageableResult page(Page<SysDictValueEntity> page, SysDictValueVO vo) {
		return PageableResult.builder().page(pageVOFilling(
				sysDictValueService.page(new PageFactory<SysDictValueEntity>().defaultPage(page), vo),
				SysDictValueVO.class)).build();
	}

	/**
	 * 查询全部
	 * @return AjaxResult
	 */
	@PostMapping(value = "/list")
	@LogAnnotation(model = MODEL + SELECT_LIST_MODEL, action = OperateLogConstant.SELECT_LIST)
	public AjaxResult list(SysDictValueVO vo) {
		return AjaxResult.builder()
				.result(mappingList(sysDictValueService.list(new QueryWrapper<>(vo)), new ArrayList<SysDictTypeVO>(),
						SysDictValueVO.class)).build();
	}

	/**
	 * 唯一
	 * @param vo vo
	 * @return AjaxResult
	 */
	@PostMapping(value = UNIQUE)
	public AjaxResult unique(SysDictValueVO vo) {
		return AjaxResult.builder().result(sysDictValueService.unique(vo)).build();
	}


	/**
	 * 注入字典值业务逻辑接口
	 */
	private final SysDictValueService sysDictValueService;
}