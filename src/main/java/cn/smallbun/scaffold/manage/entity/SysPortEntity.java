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
 * 系统员工岗位表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_port")
@ApiModel(value = "岗位参数", description = "系统员工岗位")
public class SysPortEntity extends BaseAuditEntity<String> {
	/**
	 * 岗位编码
	 */
	@ApiModelProperty(value = "岗位编码")
	@NotBlank(message = "请输入岗位编码", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("code_")
	private String code;

	/**
	 * 岗位名称
	 */
	@ApiModelProperty(value = "岗位名称")
	@NotBlank(message = "请输入岗位名称", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("name_")
	private String name;

	/**
	 * 岗位分类（高管、中层、基层）
	 */
	@ApiModelProperty(value = "岗位分类（高管、中层、基层）")
	@NotBlank(message = "请选择岗位分类", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("type_")
	private String type;

	/**
	 * 岗位排序（升序）
	 */
	@ApiModelProperty(value = "岗位排序（升序）")
	@NotBlank(message = "请输入岗位排序", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("sort_")
	private Integer sort;
}
