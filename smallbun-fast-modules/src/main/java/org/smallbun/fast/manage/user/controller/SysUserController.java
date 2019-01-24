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

package org.smallbun.fast.manage.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.fast.manage.user.service.SysUserService;
import org.smallbun.fast.manage.user.util.UserUtil;
import org.smallbun.fast.manage.user.vo.SysUserVO;
import org.smallbun.framework.annotation.SystemLog;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;

/**
 * 用户控制器
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/5/8
 */
@RestController
@RequestMapping(value = "/user")
public class SysUserController extends BaseController {


	@Autowired
	public SysUserController(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@ModelAttribute
	protected SysUserVO model(HttpServletRequest request) {
		return sysUserService.model(request);
	}

	/**
	 * 列表页面
	 *
	 * @return 地址
	 */
	@SystemLog(value = "")
	@RequestMapping(value = {"", "/"})
	public ModelAndView user() {
		return new ModelAndView("modules/manage/user/user_list.html");
	}

	/**
	 * form表单
	 *
	 * @return 地址
	 */
	@SystemLog(value = "")
	@GetMapping(value = "/form")
	public ModelAndView form(SysUserVO user, Model model) {
		model.addAttribute("user", user);
		return new ModelAndView("modules/manage/user/user_form.html");
	}

	/**
	 * 个人资料页面
	 *
	 * @return ModelAndView
	 */
	@GetMapping(value = "/profile")
	public ModelAndView profile() {
		return new ModelAndView("modules/manage/user/profile.html");
	}

	/**
	 * 获取当前用户个人信息
	 *
	 * @return AjaxResult
	 */
	@GetMapping(value = "/info")
	public AjaxResult info() {
		return AjaxResult.builder().result(Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser()).build();
	}

	/**
	 * 保存或更新
	 *
	 * @param user 类型实体对象
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/saveOrUpdate")
	public AjaxResult saveOrUpdate(@Validated SysUserVO user) {
		return AjaxResult.builder().result(sysUserService.saveOrUpdate(user)).build();
	}

	/**
	 * 删除单条记录
	 *
	 * @param id 主键ID
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/removeById")
	public AjaxResult removeById(@NotNull @RequestParam(value = "id") String id) {
		return AjaxResult.builder().result(sysUserService.removeById(id)).build();
	}

	/**
	 * 删除多条记录
	 *
	 * @param id 主键ID集合
	 * @return AjaxResult
	 */
	@SystemLog(value = "")
	@PostMapping(value = "/removeByIds")
	public AjaxResult saveOrUpdate(@NotNull @RequestParam(value = "ids") List<String> id) {
		return AjaxResult.builder().result(sysUserService.removeByIds(id)).build();
	}


	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	@PostMapping(value = "/page")
	public PageableResult page(Page<SysUserEntity> page, SysUserVO vo) {
		return PageableResult.builder().page(sysUserService.page(page, vo)).build();
	}

	/**
	 * 查询全部
	 *
	 * @return AjaxResult
	 */
	@RequestMapping(value = "/list")
	public AjaxResult list(QueryWrapper<SysUserEntity> wrapper) {
		return AjaxResult.builder().result(sysUserService.list(wrapper)).build();
	}

	/**
	 * 唯一
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = UNIQUE)
	public AjaxResult unique(SysUserVO vo) {
		return AjaxResult.builder().result(sysUserService.unique(vo)).build();
	}

	/**
	 * 注入SysUserService
	 */
	private final SysUserService sysUserService;
}
