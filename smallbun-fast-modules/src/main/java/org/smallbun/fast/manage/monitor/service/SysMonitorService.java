package org.smallbun.fast.manage.monitor.service;

import org.smallbun.fast.manage.monitor.vo.OnlineUserVO;

import java.util.List;

/**
 * 监控业务接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/13 13:51
 */
public interface SysMonitorService {

	/**
	 * 从会话注册表中获取用户
	 * @return {@link List <  OnlineUserVO  >}
	 */
	List<OnlineUserVO> getUsersFromSessionRegistry();

	/**
	 * 下线所有用户
	 */
	void expireUserSessions();


	/**
	 * 下线指定用户
	 * @param sessionId {@link String}
	 */
	void expireUserSession(String sessionId);

	/**
	 * 在线用户数量
	 * @return {@link Long}
	 */
	Long onlineUserCount();
}
