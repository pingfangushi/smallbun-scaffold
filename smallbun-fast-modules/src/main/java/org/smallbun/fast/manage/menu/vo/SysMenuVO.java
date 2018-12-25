package org.smallbun.fast.manage.menu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;

/**
 * 系统菜单vo
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/22 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuVO extends SysMenuEntity {
	/**
	 * 节点名称
	 */
	private String nodeName;

	/**
	 * 获取拼接的节点名称，名称加上权限
	 * @return {@link String} nodeName
	 */
	public String getNodeName() {
		return getMenuName() + " " + getPermission();
	}
}
