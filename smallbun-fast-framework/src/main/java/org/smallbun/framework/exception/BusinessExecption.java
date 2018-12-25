package org.smallbun.framework.exception;


import org.smallbun.framework.base.BaseException;

/**
 * 业务异常
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/17 20:02
 */
public class BusinessExecption extends BaseException {
	public BusinessExecption(String msg) {
		super("", msg);
	}

	public BusinessExecption(String status, String msg) {
		super(status, msg);
	}

	public BusinessExecption(String message, String status, String msg) {
		super(message, status, msg);
	}

	public BusinessExecption(String message, Throwable cause, String status, String msg) {
		super(message, cause, status, msg);
	}

	public BusinessExecption(Throwable cause, String status, String msg) {
		super(cause, status, msg);
	}

	public BusinessExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
			String status, String msg) {
		super(message, cause, enableSuppression, writableStackTrace, status, msg);
	}
}
