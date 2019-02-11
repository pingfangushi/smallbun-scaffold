package org.smallbun.fast.manage.log.update.controller;

import org.smallbun.fast.manage.log.update.entity.SysUpdateLogEntity;
import org.smallbun.fast.manage.log.update.service.SysUpdateLogService;
import org.smallbun.fast.manage.log.update.vo.SysUpdateLogVO;
import org.smallbun.framework.annotation.DemoEnvironment;
import org.smallbun.framework.annotation.SystemLog;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 系统更新日志
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/4 18:01
 */
@RestController
@RequestMapping(value = "/log/update")
public class SysUpdateLogController extends BaseController {
	@Autowired
	public SysUpdateLogController(SysUpdateLogService<SysUpdateLogEntity> sysUpdateLogService) {
		this.sysUpdateLogService = sysUpdateLogService;
	}

	@ModelAttribute
	public SysUpdateLogVO model(HttpServletRequest request) {
		return sysUpdateLogService.model(request);
	}

	/**
	 * 列表展示页面
	 * @return
	 */
	@GetMapping(value = {"/list", ""})
	public ModelAndView list(Model model) {
		model.addAttribute("updateLog",
				mappingList(sysUpdateLogService.list(), new ArrayList<SysUpdateLogVO>(), SysUpdateLogVO.class));
		return new ModelAndView("/modules/manage/log/update/update_log_list.html");
	}

	/**
	 * form页面
	 * @return
	 */
	@GetMapping(value = "/form")
	public ModelAndView form(SysUpdateLogVO vo, Model model) {
		model.addAttribute("updateLog", vo);
		return new ModelAndView("/modules/manage/log/update/update_log_form.html");
	}

	/**
	 * 保存或更新
	 * @return
	 */
	@SystemLog(value = "")
	@DemoEnvironment
	@PostMapping(value = "/saveOrUpdate")
	public AjaxResult saveOrUpdate(SysUpdateLogVO vo) {
		return AjaxResult.builder().result(sysUpdateLogService.saveOrUpdate(vo)).build();
	}

	/**
	 * 根据id 批量删除
	 * @param ids
	 * @return
	 */
	@SystemLog(value = "")
	@DemoEnvironment
	@PostMapping(value = "/removeByIds")
	public AjaxResult removeByIds(@RequestParam(value = "ids") List<String> ids) {
		return AjaxResult.builder().result(sysUpdateLogService.removeByIds(ids)).build();
	}

	private final SysUpdateLogService<SysUpdateLogEntity> sysUpdateLogService;
}
