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

package cn.smallbun.scaffold.manage.pojo;

import cn.smallbun.scaffold.manage.entity.SysGroupEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 组织机构VO
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/5/16 15:54
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "组织机构参数", description = "系统机构VO")
public class GroupVO extends SysGroupEntity<GroupVO> {
	/**
	 * key 针对antd
	 */
	private String key;

	/**
	 * 标题(针对ant) tree 树展示的名称
	 */
	private String title;
	/**
	 *值(针对ant) 默认根据此属性值进行筛选（其值在整个树范围内唯一）	前端我们使用了treeNodeFilterProp={title} 属性配置
	 * 在搜索的时候根据title可以搜索，但是修改的时候需要根据pid 过滤要展示的节点，所以这里value我们赋值为节点ID
	 */
	private String value;
}
