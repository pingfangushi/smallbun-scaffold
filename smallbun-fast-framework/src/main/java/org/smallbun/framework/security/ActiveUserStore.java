package org.smallbun.framework.security;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动用户存储
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/9 23:00
 */
@Component
public class ActiveUserStore {

	public List<LoggedUser> users;

	public ActiveUserStore() {
		users = new ArrayList<>();
	}

	public List<LoggedUser> getUsers() {
		return users;
	}

	public void setUsers(List<LoggedUser> users) {
		this.users = users;
	}
}
