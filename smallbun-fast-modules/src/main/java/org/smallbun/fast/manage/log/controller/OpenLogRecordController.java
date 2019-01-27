package org.smallbun.fast.manage.log.controller;

import java.security.Timestamp;

/**
 * 操作日志控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/27 20:59
 */
public class OpenLogRecordController {

	/**
	 * 编号
	 */
	private Long id;
	/**
	 * 客户端请求ip
	 */
	private String clientIp;
	/**
	 * 客户端请求路径
	 */
	private String uri;
	/**
	 * 终端请求方式,普通请求,ajax请求
	 */
	private String type;
	/**
	 * 请求方式method,post,get等
	 */
	private String method;
	/**
	 * 请求的类及方法
	 */
	private String classMethod;
	/**
	 * 请求参数内容,json
	 */
	private String paramData;
	/**
	 * 请求接口唯一session标识
	 */
	private String sessionId;
	/**
	 * 请求时间
	 */
	private Timestamp time;
	/**
	 * 接口返回时间
	 */
	private String returnTime;
	/**
	 * 接口返回数据json
	 */
	private String returnData;
	/**
	 * 请求时httpStatusCode代码，如：200,400,404等
	 */
	private String httpStatusCode;
	/**
	 * 请求耗时秒单位
	 */
	private int timeConsuming;
	/**
	 * 异常描述
	 */
	private String exceptionMessage;
	/**
	 * 请求开始时间
	 */
	private long startTime;
	/**
	 * 请求结束时间
	 */
	private long endTime;
}
