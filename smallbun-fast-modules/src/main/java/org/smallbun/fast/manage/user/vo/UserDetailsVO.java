package org.smallbun.fast.manage.user.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * 用户详细信息描述VO类
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/8 22:45
 */
@Data
@Builder
public class UserDetailsVO implements Serializable {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 归属部门
	 */
	private String orgName;
	/**
	 * 登录时间
	 */
	private Date logInTime;
	/***
	 * 最后访问时间
	 */
	private Date lastAccessTime;
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
	 * 浏览器
	 */
	private String browser;
	/**
	 * 操作系统
	 */
	private String os;
}
