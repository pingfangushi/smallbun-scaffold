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

package org.smallbun.fast.manage.notify.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.common.PageFactory;
import org.smallbun.fast.manage.notify.entity.SysNotifyEntity;
import org.smallbun.fast.manage.notify.service.SysNotifyService;
import org.smallbun.fast.manage.notify.vo.SysNotifyVO;
import org.smallbun.framework.annotation.DemoEnvironment;
import org.smallbun.framework.annotation.LogAnnotation;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.constant.OperateLogActionConstant;
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
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 通知通告 前端控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/14 19:23
 */
@Validated
@RestController
@RequestMapping("/notify")
public class SysNotifyController extends BaseController {


	@Autowired
	public SysNotifyController(SysNotifyService sysNotifyService) {
		this.sysNotifyService = sysNotifyService;
	}

	@ModelAttribute
	protected SysNotifyVO model(HttpServletRequest request) {
		return sysNotifyService.model(request);

	}

	/**
	 *
	 * @return
	 */
	@GetMapping(value = {"", "/"})
	public ModelAndView list() {
		return new ModelAndView("modules/manage/notify/notify_list.html");
	}

	/**
	 * form表单
	 * @return 地址
	 */
	@LogAnnotation(model = "", action = OperateLogActionConstant.ADD_UPDATE_FORM)
	@PreAuthorize("hasAuthority('manage:notify:add') or hasAuthority('manage:notify:edit') ")
	@GetMapping(value = "/form")
	public ModelAndView form(SysNotifyVO vo, Model model) {
		model.addAttribute("notify", vo);
		return new ModelAndView("modules/manage/notify/notify_form.html");
	}

	/**
	 * 保存或更新
	 * @param vo Vo
	 * @return AjaxResult
	 */
	@LogAnnotation(model = "", action = OperateLogActionConstant.ADD_UPDATE)
	@DemoEnvironment
	@PreAuthorize("hasAuthority('manage:notify:add') or hasAuthority('manage:notify:edit') ")
	@RequestMapping(value = "/saveOrUpdate")
	public AjaxResult saveOrUpdate(@Valid SysNotifyVO vo) {
		return AjaxResult.builder().result(sysNotifyService.saveOrUpdate(vo)).build();
	}

	/**
	 * 删除单条记录
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@LogAnnotation(model = "", action = OperateLogActionConstant.DEL)
	@DemoEnvironment
	@PreAuthorize("hasAuthority('manage:notify:del')")
	@PostMapping(value = "/removeById")
	public AjaxResult removeById(@NotNull(message = "id不能为空") @RequestParam(value = "id") String id) {
		return AjaxResult.builder().result(sysNotifyService.removeById(id)).build();
	}

	/**
	 * 删除多条记录
	 * @param ids 主键ID集合
	 * @return AjaxResult
	 */
	@LogAnnotation(model = "", action = OperateLogActionConstant.DEL)
	@DemoEnvironment
	@PreAuthorize("hasAuthority('manage:notify:del')")
	@PostMapping(value = "/removeByIds")
	public AjaxResult removeByIds(
			@NotNull(message = "id不能为空") @RequestParam(value = "ids", required = false) List<String> ids) {
		return AjaxResult.builder().result(sysNotifyService.removeByIds(ids)).build();
	}

	/**
	 * 分页查询
	 * @return PageableResult
	 */
	@LogAnnotation(model = "", action = OperateLogActionConstant.SELECT_PAGE)
	@PostMapping(value = "/page")
	public PageableResult page(Page<SysNotifyEntity> page, SysNotifyVO vo) {
		return PageableResult.builder().page(pageVOFilling(
				sysNotifyService.page(new PageFactory<SysNotifyEntity>().defaultPage(page), new QueryWrapper<>(vo)),
				SysNotifyVO.class)).build();
	}

	/**
	 * 查询全部记录
	 * @return SysDictTypeEntity
	 */
	@LogAnnotation(model = "", action = OperateLogActionConstant.SELECT_LIST)
	@PostMapping(value = "/list")
	public AjaxResult list(SysNotifyVO vo) {
		return AjaxResult.builder()
				.result(mappingList(sysNotifyService.list(new QueryWrapper<>(vo)), new ArrayList<SysNotifyVO>(),
						SysNotifyVO.class)).build();
	}

	/**
	 * 唯一
	 * @param notifyVO dictType
	 * @return AjaxResult
	 */
	@PostMapping(value = UNIQUE)
	public AjaxResult unique(SysNotifyVO notifyVO) {
		return AjaxResult.builder().result(sysNotifyService.unique(notifyVO)).build();
	}

	/**
	 * 注入sysNotifyService
	 */
	private final SysNotifyService sysNotifyService;

}
