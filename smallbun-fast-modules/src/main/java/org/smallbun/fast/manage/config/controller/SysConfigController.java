package org.smallbun.fast.manage.config.controller;

import org.smallbun.fast.manage.config.service.SysConfigService;
import org.smallbun.framework.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统配置控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/3
 */
@RestController
@RequestMapping(value = "/config")
public class SysConfigController extends BaseController {


	@Autowired
	public SysConfigController(SysConfigService sysConfigService) {
		this.sysConfigService = sysConfigService;
	}


	private final SysConfigService sysConfigService;

}
