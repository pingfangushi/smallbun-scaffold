package cn.smallbun.scaffold.manage.pojo;

import cn.smallbun.scaffold.manage.enums.AuthorizeType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * UpdateRoleAuthBO
 * @author SanLis
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/11 15:22
 */
@Data
public class UpdateAuthorizeBO implements Serializable {
	/**
	 * 角色ID
	 */
	@NotBlank(message = "请选择角色ID")
	private String id;
	/**
	 * 权限ID
	 */
	@NotBlank(message = "请选择权限ID")
	private String auth;
	/**
	 * 权限类型
	 */
	@NotNull(message = "请选择权限类型")
	private AuthorizeType type;
	/**
	 * 是否设置
	 */
	@NotNull(message = "请选择是否设置")
	private Boolean checked;
}
