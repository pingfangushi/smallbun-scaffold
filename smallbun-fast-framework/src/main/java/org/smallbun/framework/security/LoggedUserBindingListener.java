package org.smallbun.framework.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;

/**
 * 记录用户
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/9 22:45
 */
@Getter
@Setter
@Component
public class LoggedUserBindingListener implements HttpSessionBindingListener {

	/**
	 * 用户活动存储
	 */
	private ActiveUserStore activeUserStore;

	private LoggedUser loggedUser;

	public LoggedUserBindingListener(LoggedUser loggedUser, ActiveUserStore activeUserStore) {
		this.activeUserStore = activeUserStore;
		this.loggedUser = loggedUser;
	}

	public LoggedUserBindingListener() {

	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {

		List<LoggedUser> users = activeUserStore.getUsers();
		LoggedUserBindingListener value = (LoggedUserBindingListener) event.getValue();
		if (!users.contains(value.getLoggedUser())) {
			users.add(value.getLoggedUser());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<LoggedUser> users = activeUserStore.getUsers();
		LoggedUserBindingListener user = (LoggedUserBindingListener) event.getValue();
		users.remove(user.getLoggedUser());
	}
}
