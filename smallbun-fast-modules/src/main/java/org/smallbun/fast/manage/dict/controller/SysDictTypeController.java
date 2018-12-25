package org.smallbun.fast.manage.dict.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.manage.dict.entity.SysDictTypeEntity;
import org.smallbun.fast.manage.dict.service.SysDictTypeService;
import org.smallbun.fast.manage.dict.vo.SysDictTypeVO;
import org.smallbun.framework.annotation.SystemLog;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统字典类型 前端控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@RestController
@RequestMapping("/dict/type")
public class SysDictTypeController extends BaseController {
	@Autowired
	public SysDictTypeController(SysDictTypeService sysDictTypeService) {
		this.sysDictTypeService = sysDictTypeService;
	}

	@ModelAttribute
	protected SysDictTypeEntity model(String id) {
		return StringUtils.isEmpty(id) ? new SysDictTypeEntity() : sysDictTypeService.getById(id);

	}

	/**
	 * form表单
	 * @return 地址
	 */
	@SystemLog(value = "")
	@GetMapping(value = {"", "/"})
	public ModelAndView dictType() {
		return new ModelAndView("modules/manage/dict/dict_type_list.html");
	}

	/**
	 * form表单
	 * @return 地址
	 */
	@SystemLog(value = "")
	@GetMapping(value = "/form")
	public ModelAndView form(SysDictTypeEntity dictType, Model model) {
		model.addAttribute("dictType", dictType);
		return new ModelAndView("modules/manage/dict/dict_type_form.html");
	}

	/**
	 * 保存或更新
	 * @param dictTypeEntity 类型实体对象
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@RequestMapping(value = "/saveOrUpdate")
	public AjaxResult saveOrUpdate(@Validated SysDictTypeEntity dictTypeEntity) {
		return AjaxResult.builder().result(sysDictTypeService.saveOrUpdate(dictTypeEntity)).build();
	}

	/**
	 * 删除单条记录
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/removeById")
	public AjaxResult removeById(@NotNull @RequestParam(value = "id") String id) {
		return AjaxResult.builder().result(sysDictTypeService.removeById(id)).build();
	}

	/**
	 * 删除多条记录
	 * @param ids 主键ID集合
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/removeByIds")
	public AjaxResult saveOrUpdate(@NotNull @RequestParam(value = "ids") List<String> ids) {
		return AjaxResult.builder().result(sysDictTypeService.removeByIds(ids)).build();
	}

	/**
	 * 分页查询
	 * @return PageableResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/page")
	public PageableResult page(Page<SysDictTypeEntity> page, SysDictTypeEntity vo) {
		return PageableResult.builder().page(sysDictTypeService.page(page, new QueryWrapper<>(vo))).build();
	}

	/**
	 * 查询全部记录
	 * @return SysDictTypeEntity
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/list")
	public AjaxResult list() {
		return AjaxResult.builder().result(sysDictTypeService.list(new QueryWrapper<>())).build();
	}

	/**
	 * 唯一
	 * @param dictType dictType
	 * @return AjaxResult
	 */
	@PostMapping(value = "/unique")
	public AjaxResult unique(SysDictTypeVO dictType) {
		return sysDictTypeService.unique(dictType);
	}

	private final SysDictTypeService sysDictTypeService;
}
