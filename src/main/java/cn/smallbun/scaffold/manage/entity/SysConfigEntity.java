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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
