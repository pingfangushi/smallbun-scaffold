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
import lombok.experimental.Accessors;
import cn.smallbun.scaffold.manage.enums.DictStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统字典类型
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict_type")
@ApiModel(value = "字典类型", description = "系统字典类型")
public class SysDictTypeEntity extends BaseAuditEntity<String> {
	/**
	 * 类型名称
	 */
	@ApiModelProperty(value = "类型名称")
	@TableField(value = "name_", condition = SqlCondition.LIKE)
	@NotBlank(message = "请输入类型名称", groups = {AddGroup.class, UpdateGroup.class})
	private String name;

	/**
	 * 类型编码
	 */
	@ApiModelProperty(value = "类型编码")
	@TableField("code_")
	@NotBlank(message = "请输入类型编码", groups = {AddGroup.class, UpdateGroup.class})
	private String code;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "类型状态")
	@TableField("status_")
	@NotNull(message = "请选择是否为默认值", groups = {AddGroup.class, UpdateGroup.class})
	private DictStatus status;
}
