package org.smallbun.fast.manage.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.service.SysRoleService;
import org.smallbun.fast.manage.role.vo.SysRoleVO;
import org.smallbun.framework.annotation.AutoQueryDictValue;
import org.smallbun.framework.annotation.SystemLog;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 角色管理Controller
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/9 22:01
 */
@RestController
@RequestMapping(value = "/role")
public class SysRoleController extends BaseController {
	public SysRoleController(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	@ModelAttribute
	protected SysRoleVO model(String id) {
		return sysRoleService.getById(id);
	}

	/**
	 * form表单
	 * @return 地址
	 */
	@SystemLog(value = "")
	@GetMapping(value = {"", "/"})
	public ModelAndView role() {
		return new ModelAndView("modules/manage/role/role_list.html");
	}

	/**
	 * form表单
	 * @return 地址
	 */
	@SystemLog(value = "")
	@GetMapping(value = "/form")
	public ModelAndView form(SysRoleVO vo, Model model) {
		model.addAttribute("role", vo);
		return new ModelAndView("modules/manage/role/role_form.html");
	}

	/**
	 * 保存或更新
	 * @param vo 实体对象
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@RequestMapping(value = "/saveOrUpdate")
	public AjaxResult saveOrUpdate(@Validated SysRoleVO vo) {
		return AjaxResult.builder().result(sysRoleService.saveOrUpdate(vo)).build();
	}

	/**
	 * 删除单条记录
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/removeById")
	public AjaxResult removeById(@NotNull @RequestParam(value = "id") String id) {
		return AjaxResult.builder().result(sysRoleService.removeById(id)).build();
	}

	/**
	 * 删除多条记录
	 * @param ids 主键ID集合
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/removeByIds")
	public AjaxResult saveOrUpdate(@NotNull @RequestParam(value = "ids") List<String> ids) {
		return AjaxResult.builder().result(sysRoleService.removeByIds(ids)).build();
	}

	/**
	 * 分页查询
	 * @return PageableResult
	 */
	@SystemLog(value = "")
	@AutoQueryDictValue
	@PostMapping(value = "/page")
	public PageableResult page(Page<SysRoleEntity> page, SysRoleVO vo) {
		return PageableResult.builder()
				.page(pageVOFilling(sysRoleService.page(page, new QueryWrapper<>(vo)),  SysRoleVO.class))
				.build();
	}

	/**
	 * 查询全部记录
	 * @return SysDictTypeEntity
	 */
	@SystemLog(value = "")
	@AutoQueryDictValue
	@PostMapping(value = "/list")
	public AjaxResult list() {
		return AjaxResult.builder()
				.result(mappingList(sysRoleService.list(new QueryWrapper<>()), new ArrayList<>(), SysRoleVO.class))
				.build();
	}

	/**
	 * 唯一
	 * @param vo vo
	 * @return AjaxResult
	 */
	@PostMapping(value = "/unique")
	public AjaxResult unique(SysRoleVO vo) {
		return sysRoleService.unique(vo);
	}

	/**
	 * 注入用户角色service接口
	 */
	private final SysRoleService sysRoleService;
}
