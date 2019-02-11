/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
