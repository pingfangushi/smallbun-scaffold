package org.smallbun.fast.manage.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;

import java.io.Serializable;

/**
 * 角色-部门
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/23 21:49
 */
@Data
@Accessors(chain = true)
@TableName(value = "sys_role_org")
@JsonIgnoreProperties(value = {"handler"})
public class SysRoleOrgEntity implements Serializable {
	/**
	 * 角色
	 */
	@TableId(type = IdType.NONE)
	@TableField(value = "role_id", el = "role.id")
	private SysRoleEntity role;
	/**
	 *用户
	 */
	@TableId(type = IdType.NONE)
	@TableField(value = "org_id", el = "org.id")
	private SysOrgEntity org;
}
