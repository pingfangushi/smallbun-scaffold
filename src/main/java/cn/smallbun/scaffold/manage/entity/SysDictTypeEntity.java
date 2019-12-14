/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.smallbun.scaffold.manage.entity;

import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import cn.smallbun.scaffold.manage.enums.DictStatus;
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
