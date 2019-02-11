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

package org.smallbun.fast.common.utils;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.smallbun.fast.manage.dict.entity.SysDictValueEntity;
import org.smallbun.fast.manage.dict.service.SysDictValueService;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *字典工具
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/13 20:58
 */
@Component(value = "smallbun")
public class SmallBunUtil {

	@Autowired
	public SmallBunUtil(SysDictValueService valueService, SysRoleService sysRoleService) {
		this.valueService = valueService;
		this.sysRoleService = sysRoleService;
	}

	/**
	 * 根据类型编码获取字典
	 * @param typeCode typeCode
	 * @return
	 */
	public List<Dict> dict(String typeCode) {
		List<Dict> dictList = Lists.newArrayList();
		//根据类型获取编码获取字典值
		List<SysDictValueEntity> dictValue = valueService.findByTypeCode(typeCode);
		//类型不为空
		if (!CollectionUtils.isEmpty(dictValue)) {
			//循环set值
			for (SysDictValueEntity value : dictValue) {
				dictList.add(Dict.builder().dictLabel(value.getDictLabel()).dictValue(value.getDictValue())
						.typeCode(value.getSysDictType().getTypeCode()).typeName(value.getSysDictType().getTypeName())
						.build());
			}
		}
		return dictList;
	}

	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Role> roles() {
		List<Role> roles = Lists.newArrayList();
		sysRoleService.list().forEach(
				u -> roles.add(Role.builder().id(String.valueOf(u.getId())).roleName(u.getRoleName()).build()));
		return roles;
	}

	/**
	 * 获取所有角色，传入已经选择的集合，设置为true
	 * @return
	 */
	public List<Role> roles(List<SysRoleEntity> list) {
		List<Role> roles = roles();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(u -> roles.forEach(s -> {
				if (String.valueOf(u.getId()).equals(String.valueOf(s.getId()))) {
					s.setFlag(true);
				}
			}));
		}
		return roles;
	}

	/**
	 * 注入字典类型service接口
	 */
	private final SysDictValueService valueService;
	/**
	 * 注入角色service接口
	 */
	private final SysRoleService sysRoleService;

}

/**
 * dict
 */
@Builder
@Data
class Dict {
	/**
	 * 类型编码
	 */
	private String typeCode;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 字典标签
	 */
	private String dictLabel;
	/**
	 * 字典值
	 */
	private String dictValue;
}

/**
 * Role
 */
@Builder
@Data
class Role {
	/**
	 * 角色Id
	 */
	private String id;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 选中
	 */
	private Boolean flag;
}