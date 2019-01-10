package org.smallbun.framework.security;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/9 22:59
 */
@Data
@Builder
public class LoggedUser implements Serializable {
	/**
	 * sessionId
	 */
	private String sessionId;
	/**
	 * 登录IP
	 */
	private String logInIp;
	/**
	 * 登录地址
	 */
	private String logInAddress;
	/**
	 * 登录时间
	 */
	private Date logInTime;
	/**
	 * 浏览器
	 */
	private String browser;
	/**
	 * 操作系统
	 */
	private String os;
}
