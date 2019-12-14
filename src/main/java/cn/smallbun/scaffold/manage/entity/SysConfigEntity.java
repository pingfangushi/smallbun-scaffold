package cn.smallbun.scaffold.manage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 系统参数配置表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_config")
@ApiModel(value = "系统参数", description = "系统配置参数")
public class SysConfigEntity extends BaseAuditEntity<String> {
	/**
	 * 参数名称
	 */
	@ApiModelProperty(value = "参数名称")
	@TableField("name_")
	@NotBlank(message = "请输入参数名称", groups = {AddGroup.class, UpdateGroup.class})
	private String name;

	/**
	 * 参数键
	 */
	@ApiModelProperty(value = "参数键")
	@NotBlank(message = "请输入参数键", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("key_")
	private String key;

	/**
	 * 参数值
	 */
	@ApiModelProperty(value = "参数值")
	@NotBlank(message = "请输入参数值", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("value_")
	private String value;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	@TableField("status_")
	@NotBlank(message = "请输入状态", groups = {AddGroup.class, UpdateGroup.class})
	private String status;
}
