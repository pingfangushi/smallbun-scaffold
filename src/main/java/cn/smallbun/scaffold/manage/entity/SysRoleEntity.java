package cn.smallbun.scaffold.manage.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import cn.smallbun.scaffold.manage.enums.RoleStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value = "角色参数", description = "系统角色")
public class SysRoleEntity extends BaseAuditEntity<String> {
	/**
	 * 角色名称
	 */
	@ApiModelProperty(value = "角色名称")
	@NotBlank(message = "请输入角色名称", groups = {AddGroup.class, UpdateGroup.class})
	@TableField(value = "name_", condition = SqlCondition.LIKE)
	private String name;

	/**
	 * 英文名称
	 */
	@ApiModelProperty(value = "角色编码")
	@NotBlank(message = "请输入角色编码", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("code_")
	private String code;

	/**
	 * 数据范围
	 */
	@ApiModelProperty(value = "数据范围")
	@NotNull(message = "请选择数据范围")
	@TableField("data_scope")
	private Integer dataScope;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	@NotNull(message = "请选择角色状态", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("status_")
	private RoleStatus status;
}
