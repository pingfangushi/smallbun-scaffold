package org.smallbun.fast.manage.role.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;

/**
 * 用户角色VO对象
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/9 22:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleVO extends SysRoleEntity {
	/**
	 * 菜单json字符串，方便ztree获取，而不用请求后台
	 */
	private String menusJson;
	/**
	 * 明细设置部门json字符串，方便ztree获取，而不用请求后台
	 */
	private String orgsJson;
}
