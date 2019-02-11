package org.smallbun.fast.manage.log.update.service;

import org.smallbun.fast.manage.log.update.vo.SysUpdateLogVO;
import org.smallbun.framework.base.BaseService;

import javax.servlet.http.HttpServletRequest;

/**
 * 更新日志
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/4 18:03
 */
public interface SysUpdateLogService<T> extends BaseService<T> {
	/**
	 * model
	 * @param request
	 * @return
	 */
	SysUpdateLogVO model(HttpServletRequest request);
}
