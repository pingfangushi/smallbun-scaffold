/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.fast.manage.role.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.framework.annotation.DictValue;

/**
 * 用户角色VO对象
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/9 22:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleVO extends SysRoleEntity {
	/**
	 * 菜单json字符串，方便ztree获取，而不用请求后台
	 */
	private String menusJson;
	/**
	 * 明细设置部门json字符串，方便ztree获取，而不用请求后台
	 */
	private String orgsJson;

	/**
	 * 可查看的数据范围-字典值
	 * (1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置)
	 */
	@DictValue(typeCode = "DATA_SCOPE", valueField = "dataScope")
	@TableField(exist = false)
	private String dataScopeName;
	/**
	 * 角色类型-字典值
	 */
	@DictValue(typeCode = "ROLE_TYPE", valueField = "roleType")
	@TableField(exist = false)
	private String roleTypeName;

	/**
	 * 是否可用-字典值
	 */
	@DictValue(typeCode = "WHETHER_USEABLE", valueField = "useable")
	@TableField(exist = false)
	private String useableName;

	/**
	 * 是否系统数据-字典值
	 */
	@DictValue(typeCode = "SYS_DATA", valueField = "sysData")
	@TableField(exist = false)
	private String sysDataName;
	/**
	 * 选中，如果是选中为true
	 */
	private Boolean flag;
}
