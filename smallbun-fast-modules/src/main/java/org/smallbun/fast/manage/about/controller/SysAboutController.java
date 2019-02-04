package org.smallbun.fast.manage.about.controller;

import org.smallbun.framework.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 关于
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/4 18:01
 */
@RestController
@RequestMapping(value = "/about")
public class SysAboutController extends BaseController {
	/**
	 *
	 * @return
	 */
	@GetMapping(value = {"/list", ""})
	public ModelAndView list() {
		return new ModelAndView("/modules/manage/about/about_list.html");
	}

}
