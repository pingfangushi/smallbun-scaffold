package org.smallbun.fast.common.config;

import org.smallbun.fast.manage.user.service.SysUserService;
import org.smallbun.fast.manage.user.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2019-01-29 17:07
 */
@Configuration
public class SecurityHandler extends org.smallbun.framework.security.SecurityHandler {
	@Autowired
	public SecurityHandler(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	/**
	 * 登陆成功Handler
	 *
	 * @return {@link AuthenticationSuccessHandler}
	 */
	@Override
	public AuthenticationSuccessHandler loginSuccessHandler() {
		//登录成功，保存登录信息到数据库
		sysUserService.updateLastLoginInfo(UserUtil.getLoginUser());
		//调用父类处理方法
		return super.loginSuccessHandler();
	}

	/**
	 * 注入用户逻辑service接口
	 */
	private final SysUserService sysUserService;
}
