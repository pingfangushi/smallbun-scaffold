package cn.smallbun.scaffold.manage.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * RoleAuthBO
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/11 16:20
 */
@Data
public class RoleAuthBO implements Serializable {
	/**
	 * 角色ID
	 */
	private String role;
	/**
	 * 权限ID
	 */
	private String auth;
	/**
	 * 权限类型
	 */
	private String type;
}
