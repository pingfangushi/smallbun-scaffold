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

package org.smallbun.fast.manage.log.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.common.PageFactory;
import org.smallbun.fast.manage.log.entity.SysOperateLogEntity;
import org.smallbun.fast.manage.log.service.SysOperateLogService;
import org.smallbun.fast.manage.log.vo.SysOperateLogVO;
import org.smallbun.framework.annotation.AutoQueryDictValue;
import org.smallbun.framework.annotation.DemoEnvironment;
import org.smallbun.framework.annotation.LogAnnotation;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.constant.OperateLogConstant;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.smallbun.framework.constant.ErrorMsgConstant.ID_NOT_BLANK_MSG;
import static org.smallbun.framework.constant.OperateLogConstant.DEL_MODEL;
import static org.smallbun.framework.constant.OperateLogConstant.SELECT_PAGE_MODEL;

/**
 * 操作日志记录 前端控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/15 21:47
 */
@Validated
@RestController
@RequestMapping("/log/operate")
public class SysOperateLogController extends BaseController {

	/**
	 * 模块名称
	 */
	private static final String MODEL = "操作日志";

	/**
	 * HTML页面路径前缀
	 */
	private static final String HTML_PREFIX = "modules/manage/log/";

	@Autowired
	public SysOperateLogController(SysOperateLogService sysOperateLogService) {
		this.sysOperateLogService = sysOperateLogService;
	}

	@ModelAttribute
	protected SysOperateLogVO model(HttpServletRequest request) {
		return sysOperateLogService.model(request);
	}


	/**
	 * 返回list页面
	 * @return {@link ModelAndView}
	 */
	@GetMapping(value = {"", "/"})
	public ModelAndView operate() {
		return new ModelAndView(HTML_PREFIX + "operate_log_list.html");
	}

	/**
	 * 分页查询
	 * @return PageableResult
	 */
	@AutoQueryDictValue
	@PostMapping(value = "/page")
	@LogAnnotation(model = MODEL + SELECT_PAGE_MODEL, action = OperateLogConstant.SELECT_PAGE)
	public PageableResult page(Page<SysOperateLogEntity> page, SysOperateLogVO vo) {
		return PageableResult.builder().page(pageVOFilling(
				sysOperateLogService.page(new PageFactory<SysOperateLogEntity>().defaultPage(page), vo),
				SysOperateLogVO.class)).build();
	}


	/**
	 * 删除多条记录
	 * @param ids 主键ID集合
	 * @return AjaxResult
	 */
	@DemoEnvironment
	@PostMapping(value = "/removeByIds")
	@PreAuthorize("hasAuthority('manage:log:operate:del')")
	@LogAnnotation(model = DEL_MODEL + MODEL, action = OperateLogConstant.DEL)
	public AjaxResult removeByIds(
			@NotNull(message = ID_NOT_BLANK_MSG) @RequestParam(value = "ids", required = false) List<String> ids) {
		return AjaxResult.builder().result(sysOperateLogService.removeByIds(ids)).build();
	}

	/**
	 * 注入 SysOperateLogService
	 */
	private SysOperateLogService sysOperateLogService;

}
