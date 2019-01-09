package org.smallbun.framework.security;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActiveUserStore {

	public List<String> users;

	public ActiveUserStore() {
		users = new ArrayList<String>();
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
}
