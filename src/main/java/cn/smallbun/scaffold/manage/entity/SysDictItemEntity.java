package cn.smallbun.scaffold.manage.entity;

import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import cn.smallbun.scaffold.manage.enums.DictDefault;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统字典数据
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict_item")
@ApiModel(value = "字典数据", description = "系统字典数据")
public class SysDictItemEntity extends BaseAuditEntity<String> {

	/**
	 * 所属字典类型(关联sys_dict_type表)
	 */
	@ApiModelProperty(value = "字典类型")
	@TableField("type_")
	@NotNull(message = "请选择字典类型", groups = {AddGroup.class, UpdateGroup.class})
	private String type;

	/**
	 * 字典标签
	 */
	@ApiModelProperty(value = "字典标签")
	@TableField(value = "label_", condition = SqlCondition.LIKE)
	@NotBlank(message = "请输入字典标签", groups = {AddGroup.class, UpdateGroup.class})
	private String label;

	/**
	 * 字典值
	 */
	@ApiModelProperty(value = "字典值")
	@NotBlank(message = "请输入字典值", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("value_")
	private String value;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	@TableField(value = "sort_")
	private Integer sort;
	/**
	 * 颜色值
	 */
	@ApiModelProperty(value = "颜色值")
	@NotNull(message = "请选择颜色", groups = {AddGroup.class, UpdateGroup.class})
	@TableField(value = "color_")
	private String color;

	/**
	 * 是否为默认值 0 默认 1 非默认
	 */
	@ApiModelProperty(value = "是否为默认值")
	@TableField("is_default")
	@NotNull(message = "请选择是否为默认值", groups = {AddGroup.class, UpdateGroup.class})
	private DictDefault isDefault;
}
