package org.smallbun.fast.manage.role.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.smallbun.fast.common.entity.DataEntity;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.framework.annotation.DictValue;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 角色实体类
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/15 20:04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
@JsonIgnoreProperties(value = {"handler"})
public class SysRoleEntity extends DataEntity<Long> {

	/**
	 * 角色名称
	 */
	@NotEmpty(message = "角色名称不能为空")
	@TableField(condition = SqlCondition.LIKE)
	private String roleName;
	/**
	 * 英文名称
	 */
	@NotEmpty(message = "英文名称不能为空")
	private String enName;

	/**
	 * 可查看的数据范围(1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置)
	 */
	@NotEmpty(message = "请选择数据范围")
	private String dataScope;
	/**
	 * 角色类型
	 */
	@NotEmpty(message = "请选择角色类型")
	private String roleType;

	/**
	 * 是否可用
	 */
	@NotEmpty(message = "请选择是否可用")
	private String useable;

	/**
	 * 是否系统数据（默认否）
	 */
	private String sysData;
	/**
	 * 拥有用户列表(缓存)
	 */
	@TableField(exist = false)
	private List<SysUserEntity> userList = Lists.newArrayList();
	/**
	 * 拥有菜单列表(缓存)
	 */
	@TableField(exist = false)
	private List<SysMenuEntity> menuList = Lists.newArrayList();
	/**
	 * 按明细设置数据范围(缓存)
	 */
	@TableField(exist = false)
	private List<SysOrgEntity> orgList = Lists.newArrayList();

	/**
	 * 可查看的数据范围-字典值
	 * (1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置)
	 */
	@DictValue(typeCode = "DATA_SCOPE", valueField = "dataScope")
	@TableField(exist = false)
	private String dataScopeName;
	/**
	 * 角色类型-字典值
	 */
	@DictValue(typeCode = "ROLE_TYPE", valueField = "roleType")
	@TableField(exist = false)
	private String roleTypeName;

	/**
	 * 是否可用-字典值
	 */
	//@DictValue(typeCode = "WHETHER_USEABLE", valueField = "useable")
	@TableField(exist = false)
	private String useableName;

	/**
	 * 是否系统数据-字典值
	 */
	@DictValue(typeCode = "SYS_DATA", valueField = "sysData")
	@TableField(exist = false)
	private String sysDataName;
}
