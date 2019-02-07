package org.smallbun.fast.manage.dict.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.common.PageFactory;
import org.smallbun.fast.manage.dict.entity.SysDictValueEntity;
import org.smallbun.fast.manage.dict.service.SysDictValueService;
import org.smallbun.fast.manage.dict.vo.SysDictTypeVO;
import org.smallbun.fast.manage.dict.vo.SysDictValueVO;
import org.smallbun.framework.annotation.DemoEnvironment;
import org.smallbun.framework.annotation.SystemLog;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 系统字典数据 前端控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@RestController
@RequestMapping("/dict/value")
public class SysDictValueController extends BaseController {


	@Autowired
	public SysDictValueController(SysDictValueService sysDictValueService) {
		this.sysDictValueService = sysDictValueService;
	}

	@ModelAttribute
	protected SysDictValueVO model(HttpServletRequest request) {
		return sysDictValueService.model(request);
	}

	/**
	 * form表单
	 * @return 地址
	 */
	@SystemLog(value = "")
	@GetMapping(value = {"", "/"})
	public ModelAndView dictType(SysDictValueVO vo, Model model) {
		model.addAttribute("vo", vo);
		return new ModelAndView("/modules/manage/dict/dict_value_list.html");
	}

	/**
	 * form表单
	 * @return 地址
	 */
	@SystemLog(value = "")
	@GetMapping(value = "/form")
	public ModelAndView form(SysDictValueVO vo, Model model) {
		model.addAttribute("dictValue", vo);
		return new ModelAndView("/modules/manage/dict/dict_value_form.html");
	}

	/**
	 * 保存或更新
	 * @param vo 类型实体对象
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@DemoEnvironment
	@PostMapping(value = "/saveOrUpdate")
	public AjaxResult saveOrUpdate(SysDictValueVO vo) {
		return AjaxResult.builder().result(sysDictValueService.saveOrUpdate(vo)).build();
	}

	/**
	 * 删除单条记录
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@DemoEnvironment

	@PostMapping(value = "/removeById")
	public AjaxResult removeById(@NotNull String id) {
		return AjaxResult.builder().result(sysDictValueService.removeById(id)).build();
	}

	/**
	 * 删除多条记录
	 * @param ids 主键ID集合
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/removeByIds")
	public AjaxResult saveOrUpdate(@NotNull @RequestParam(value = "ids") List<String> ids) {
		return AjaxResult.builder().result(sysDictValueService.removeByIds(ids)).build();
	}

	/**
	 * 分页查询
	 * @return PageableResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/page")
	public PageableResult page(Page<SysDictValueEntity> page, SysDictValueVO vo) {
		return PageableResult.builder().page(pageVOFilling(
				sysDictValueService.page(new PageFactory<SysDictValueEntity>().defaultPage(page), vo),
				SysDictValueVO.class)).build();
	}

	/**
	 * 查询全部
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/list")
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
	@PostMapping(value = "/unique")
	public AjaxResult unique(SysDictValueVO vo) {
		return AjaxResult.builder().result(sysDictValueService.unique(vo)).build();
	}


	/**
	 * 注入字典值业务逻辑接口
	 */
	private final SysDictValueService sysDictValueService;
}