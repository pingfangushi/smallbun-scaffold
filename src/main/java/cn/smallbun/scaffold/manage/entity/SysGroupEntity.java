package cn.smallbun.scaffold.manage.entity;

import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditNodeEntity;
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import cn.smallbun.scaffold.manage.enums.GroupStatus;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统机构表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_group")
@ApiModel(value = "组织机构参数", description = "系统组织机构")
public class SysGroupEntity<E extends SysGroupEntity> extends BaseAuditNodeEntity<String, E> {
	/**
	 * 机构名称
	 */
	@ApiModelProperty(value = "机构名称")
	@NotBlank(message = "请输入机构名称", groups = {AddGroup.class, UpdateGroup.class})
	@TableField(value = "name_", condition = SqlCondition.LIKE)
	private String name;

	/**
	 * 机构编码
	 */
	@ApiModelProperty(value = "机构编码")
	@NotBlank(message = "请输入机构编码", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("code_")
	private String code;


	/**
	 * 机构类型
	 */
	@ApiModelProperty(value = "机构类型")
	@NotBlank(message = "请选择机构类型", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("type_")
	private String type;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	@NotNull(message = "请选择机构状态", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("status_")
	private GroupStatus status;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	@TableField(value = "sort_", exist = false)
	private Integer sort;
}
