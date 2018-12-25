package org.smallbun.fast.manage.role.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;

import java.io.Serializable;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/23 14:29
 */
@Data
@Accessors(chain = true)
@TableName(value = "sys_role_menu")
@JsonIgnoreProperties(value = {"handler"})
public class SysRoleMenuEntity implements Serializable {

	/**
	 * 角色
	 */
	@TableField(value = "role_id", el = "role.id")
	private SysRoleEntity role;

	/**
	 * 菜单
	 */
	@TableField(value = "menu_id", el = "menu.id")
	private SysMenuEntity menu;

}
