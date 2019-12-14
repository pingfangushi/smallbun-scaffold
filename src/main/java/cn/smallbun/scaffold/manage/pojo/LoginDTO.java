package cn.smallbun.scaffold.manage.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录DTO
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/10/27 15:29
 */
@Data
public class LoginDTO implements Serializable {
	/**
	 * 标志
	 */
	private String key;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 验证码
	 */
	private String captcha;
	/**
	 * 记住我
	 */
	private Boolean rememberMe;
}
