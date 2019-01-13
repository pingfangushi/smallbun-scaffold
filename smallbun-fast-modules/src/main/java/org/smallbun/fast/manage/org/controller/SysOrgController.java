package org.smallbun.fast.manage.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.common.utils.AutoMapperUtil;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.org.service.SysOrgService;
import org.smallbun.fast.manage.org.vo.SysOrgVO;
import org.smallbun.framework.annotation.AutoQueryDictValue;
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
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.toolkit.AutoMapperUtil.mapping;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 部门控制器
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/3
 */
@RestController
@RequestMapping(value = "/org")
public class SysOrgController extends BaseController {
	@Autowired
	public SysOrgController(SysOrgService sysOrgService) {
		this.sysOrgService = sysOrgService;
	}

	@ModelAttribute
	public SysOrgVO model(String id) {
		return StringUtils.isEmpty(id) ? new SysOrgVO() : mapping(sysOrgService.getById(id), new SysOrgVO());
	}

	/**
	 * 列表页面
	 *
	 * @return ModelAndView
	 */
	@GetMapping(value = {"/", ""})
	public ModelAndView org() {
		return new ModelAndView("/modules/manage/org/org_list.html");
	}

	/**
	 * form 表单
	 *
	 * @param org   SysOrgEntity
	 * @param model Model
	 * @return ModelAndView
	 */
	@GetMapping(value = "form")
	public ModelAndView form(@Validated SysOrgVO org, Model model) {
		model.addAttribute("org", org);
		return new ModelAndView("/modules/manage/org/org_form.html");
	}

	/**
	 * 添加一条记录
	 *
	 * @param org 实体类对象
	 * @return AjaxResult
	 */
	@PostMapping("/saveOrUpdate")
	public AjaxResult saveOrUpdate(@Validated SysOrgVO org) {
		return AjaxResult.builder().result(sysOrgService.saveOrUpdate(org)).build();
	}

	/**
	 * 根据ID删除一条记录
	 *
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@PostMapping("/removeById")
	public AjaxResult removeById(@NotNull Long id) {
		return AjaxResult.builder().result(sysOrgService.removeById(id)).build();
	}

	/**
	 * 批量删除一条记录
	 *
	 * @param ids ID列表
	 * @return AjaxResult
	 */
	@PostMapping("/removeByIds")
	public AjaxResult removeByIds(@NotNull List<String> ids) {
		return AjaxResult.builder().result(sysOrgService.removeByIds(ids)).build();
	}

	/**
	 * 分页查询
	 *
	 * @param page page
	 * @return PageableResult
	 */
	@PostMapping(value = "/page")
	@AutoQueryDictValue
	public PageableResult page(Page<SysOrgEntity> page, SysOrgVO vo) {
		return PageableResult.builder()
				.page(pageVOFilling(sysOrgService.page(page, new QueryWrapper<>(vo)), SysOrgVO.class)).build();
	}

	/**
	 * 查询全部数据
	 *
	 * @return AjaxResult
	 */
	@PostMapping(value = "/list")
	@AutoQueryDictValue
	public AjaxResult list() {
		return AjaxResult.builder()
				.result(mappingList(sysOrgService.list(new QueryWrapper<>()), new ArrayList<SysOrgVO>(),
						SysOrgVO.class)).build();
	}

	/**
	 * 返回树
	 *
	 * @return AjaxResult
	 */
	@PostMapping(value = "/tree")
	@AutoQueryDictValue
	public AjaxResult tree() {
		return AjaxResult.builder().result(AutoMapperUtil
				.mappingTreeList(sysOrgService.tree(new QueryWrapper<>()), new ArrayList<>(), SysOrgVO.class)).build();
	}

	/**
	 * 唯一
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/unique")
	public AjaxResult unique(SysOrgVO vo) {
		return sysOrgService.unique(vo);
	}

	/**
	 * 注入部门业务接口
	 */
	private final SysOrgService sysOrgService;
}
