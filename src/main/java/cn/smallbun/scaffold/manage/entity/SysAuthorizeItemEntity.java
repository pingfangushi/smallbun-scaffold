package cn.smallbun.scaffold.manage.entity;

import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import cn.smallbun.scaffold.manage.enums.AuthorizeStatus;
import cn.smallbun.scaffold.manage.enums.AuthorizeType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统权限项
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_authorize_item")
@ApiModel(value = "权限项", description = "系统权限项")
public class SysAuthorizeItemEntity extends BaseAuditEntity<String> {

	/**
	 * 归属权限
	 */
	@TableField("authorize_")
	@NotBlank(message = "请选择权限类型")
	private String authorize;

	/**
	 * 权限项名称
	 */
	@TableField("name_")
	@NotBlank(message = "请输入权限项名称")
	private String name;

	/**
	 * 授权标识
	 */
	@TableField("permission_")
	@NotBlank(message = "请输入授权标识")
	private String permission;

	/**
	 * 状态  0：正常 1：禁用
	 */
	@TableField("status_")
	@NotNull(message = "请选择权限项状态")
	private AuthorizeStatus status;
	/**
	 * 类型 1 路由 2操作 3 接口
	 */
	@TableField("type_")
	@NotNull(message = "请选择权限类型")
	private AuthorizeType type;

}
