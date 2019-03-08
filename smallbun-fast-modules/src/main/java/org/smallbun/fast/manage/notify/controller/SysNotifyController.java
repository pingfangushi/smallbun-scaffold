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


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.common.PageFactory;
import org.smallbun.fast.manage.notify.entity.SysNotifyEntity;
import org.smallbun.fast.manage.notify.entity.SysNotifyRecordEntity;
import org.smallbun.fast.manage.notify.service.SysNotifyService;
import org.smallbun.fast.manage.notify.vo.SysNotifyVO;
import org.smallbun.fast.manage.role.service.SysNotifyRecordService;
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
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.constant.ErrorMsgConstant.ID_NOT_BLANK_MSG;
import static org.smallbun.framework.constant.OperateLogConstant.SELECT_LIST;
import static org.smallbun.framework.constant.OperateLogConstant.SELECT_PAGE;
import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 通知通告 前端控制器
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/14 19:23
 */
@Validated
@RestController
@RequestMapping("/notify")
public class SysNotifyController extends BaseController {
    /**
     * 模块名称
     */
    private static final String MODEL = "通知公告";

    /**
     * HTML页面路径前缀
     */
    private static final String HTML_PREFIX = "modules/manage/notify/";


    @Autowired
    public SysNotifyController(SysNotifyService sysNotifyService, SysNotifyRecordService notifyRecordService) {
        this.sysNotifyService = sysNotifyService;
        this.notifyRecordService = notifyRecordService;
    }


    @ModelAttribute
    protected SysNotifyVO model(HttpServletRequest request) {
        return sysNotifyService.model(request);
    }

    /**
     * list页面
     *
     * @return {@link ModelAndView}
     */
    @GetMapping(value = {"", "/"})
    @PreAuthorize("hasAuthority('manage:notify:index')")
    public ModelAndView list() {
        return new ModelAndView(HTML_PREFIX + "notify_list.html");
    }

    /**
     * form表单
     *
     * @return {@link ModelAndView}
     */

    @GetMapping(value = "/form")
    @PreAuthorize("hasAuthority('manage:notify:add') or hasAuthority('manage:notify:edit') ")
    @LogAnnotation(model = MODEL, action = OperateLogConstant.OPEN_VIEW_FORM)
    public ModelAndView form(SysNotifyVO vo, Model model) {
        model.addAttribute("notify", vo);
        return new ModelAndView(HTML_PREFIX + "notify_form.html");
    }

    /**
     * 保存或更新
     *
     * @param vo Vo
     * @return {@link AjaxResult}
     */
    @DemoEnvironment
    @RequestMapping(value = "/saveOrUpdate")
    @PreAuthorize("hasAuthority('manage:notify:add') or hasAuthority('manage:notify:edit') ")
    @LogAnnotation(model = MODEL, action = OperateLogConstant.ADD_UPDATE)
    public AjaxResult saveOrUpdate(@Valid SysNotifyVO vo) {
        return AjaxResult.builder().result(sysNotifyService.saveOrUpdate(vo)).build();
    }

    /**
     * 删除单条记录
     *
     * @param id 主键ID
     * @return {@link AjaxResult}
     */
    @DemoEnvironment
    @PostMapping(value = "/removeById")
    @PreAuthorize("hasAuthority('manage:notify:del')")
    @LogAnnotation(model = MODEL, action = OperateLogConstant.DEL)
    public AjaxResult removeById(@NotNull(message = ID_NOT_BLANK_MSG) @RequestParam(value = "id") String id) {
        return AjaxResult.builder().result(sysNotifyService.removeById(id)
            && notifyRecordService.remove(new LambdaQueryWrapper<SysNotifyRecordEntity>().eq(SysNotifyRecordEntity::getNotifyId, id))).build();
    }

    /**
     * 删除多条记录
     *
     * @param ids 主键ID集合
     * @return {@link AjaxResult}
     */
    @DemoEnvironment
    @PostMapping(value = "/removeByIds")
    @PreAuthorize("hasAuthority('manage:notify:del')")
    @LogAnnotation(model = MODEL, action = OperateLogConstant.DEL)
    public AjaxResult removeByIds(
        @NotNull(message = ID_NOT_BLANK_MSG) @RequestParam(value = "ids", required = false) List<String> ids) {
        return AjaxResult.builder().result(sysNotifyService.removeByIds(ids)).build();
    }

    /**
     * 分页查询
     *
     * @return {@link PageableResult}
     */
    @AutoQueryDictValue
    @PostMapping(value = "/page")
    @LogAnnotation(model = MODEL + SELECT_PAGE, action = OperateLogConstant.SELECT_PAGE)
    public PageableResult page(Page<SysNotifyEntity> page, SysNotifyVO vo) {
        return PageableResult.builder().page(pageVOFilling(
            sysNotifyService.page(new PageFactory<SysNotifyEntity>().defaultPage(page), new QueryWrapper<>(vo)),
            SysNotifyVO.class)).build();
    }

    /**
     * 查询全部记录
     *
     * @return {@link AjaxResult}
     */
    @AutoQueryDictValue
    @PostMapping(value = "/list")
    @LogAnnotation(model = MODEL + SELECT_LIST, action = OperateLogConstant.SELECT_LIST)
    public AjaxResult list(SysNotifyVO vo) {
        return AjaxResult.builder()
            .result(mappingList(sysNotifyService.list(new QueryWrapper<>(vo)), new ArrayList<SysNotifyVO>(),
                SysNotifyVO.class)).build();
    }

    /**
     * 唯一
     *
     * @param notifyVO dictType
     * @return {@link AjaxResult}
     */
    @PostMapping(value = UNIQUE)
    public AjaxResult unique(SysNotifyVO notifyVO) {
        return AjaxResult.builder().result(sysNotifyService.unique(notifyVO)).build();
    }

    /**
     * 注入sysNotifyService
     */
    private final SysNotifyService sysNotifyService;
    /***
     * 关联关系
     */
    private final SysNotifyRecordService notifyRecordService;

}
