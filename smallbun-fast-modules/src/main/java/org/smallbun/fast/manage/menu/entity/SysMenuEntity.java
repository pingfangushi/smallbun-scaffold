/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.smallbun.fast.manage.menu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.smallbun.fast.common.entity.TreeDataEntity;

import java.util.List;

/**
 * 系统菜单实体类
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "sys_menu")
public class SysMenuEntity extends TreeDataEntity<Long> {
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 地址
	 */
	private String url;
	/**
	 * 类型 1. 菜单,2 按钮
	 */
	private Integer type;
	/**
	 * 方法允许配置标识
	 */
	private String permission;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 菜单状态 0正常 ，1锁定
	 */
	private String menuStatus;
	/**
	 * 子菜单
	 */
	@JSONField(serialize = false)
	@TableField(exist = false)
	private List<SysMenuEntity> children;

}
