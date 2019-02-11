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

package org.smallbun.fast.manage.menu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.smallbun.fast.common.entity.TreeDataEntity;
import org.smallbun.framework.annotation.DictValue;

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
public class SysMenuEntity extends TreeDataEntity<SysMenuEntity, Long> {
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 *字体颜色
	 */
	private String fontColor;
	/**
	 * 地址
	 */
	private String url;
	/**
	 * 类型 0.目录 1. 菜单,2 按钮
	 */
	private Integer menuType;
	/**
	 * 菜单目标
	 */
	private String target;
	/**
	 * 方法允许配置标识
	 */
	private String permission;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 菜单状态 0正常 ，2锁定
	 */
	private String menuStatus;

}
