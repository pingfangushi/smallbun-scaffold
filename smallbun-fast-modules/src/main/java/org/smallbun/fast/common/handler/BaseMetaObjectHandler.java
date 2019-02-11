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

package org.smallbun.fast.common.handler;


import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.smallbun.fast.manage.user.util.UserUtil;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 填充创建时间，创建人，更新时间，更新人
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/12 22:05
 */
public class BaseMetaObjectHandler implements MetaObjectHandler {
	/**
	 * 父id
	 */
	private static final String PARENT_ID = "parentId";
	/**
	 * 父ids
	 */
	private static final String PARENT_ID_S = "parentIds";
	/**
	 * 删除标记
	 */
	private static final String IS_DELETED = "isDeleted";
	/**
	 * 创建时间
	 */
	private static final String CREATOR = "creator";
	/**
	 * 创建时间
	 */
	private static final String GMT_CREATE = "gmtCreate";
	/**
	 * 创建时间
	 */
	private static final String EDITOR = "editor";
	/**
	 * 更新时间
	 */
	private static final String GMT_MODIFIED = "gmtModified";

	/**
	 * 新增
	 * @param metaObject  {@link MetaObject}
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		// 创建用户
		Object creator = getFieldValByName(CREATOR, metaObject);
		if (StringUtils.isEmpty(creator)) {
			setFieldValByName(CREATOR, Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser(), metaObject);
		}
		// 修改用户
		Object editor = getFieldValByName(EDITOR, metaObject);
		if (StringUtils.isEmpty(editor)) {
			setFieldValByName(EDITOR, Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser(), metaObject);
		}
		// 创建时间
		Object gmtCreate = getFieldValByName(GMT_CREATE, metaObject);
		if (StringUtils.isEmpty(gmtCreate)) {
			setFieldValByName(GMT_CREATE, LocalDateTime.now(), metaObject);
		}
		// 修改时间
		Object gmtModified = getFieldValByName(GMT_MODIFIED, metaObject);
		if (StringUtils.isEmpty(gmtModified)) {
			setFieldValByName(GMT_MODIFIED, LocalDateTime.now(), metaObject);
		}
		// 删除标记
		Object isDeleted = getFieldValByName(IS_DELETED, metaObject);
		if (StringUtils.isEmpty(isDeleted)) {
			setFieldValByName(IS_DELETED, new GlobalConfig.DbConfig().getLogicNotDeleteValue(), metaObject);
		}
		// 父id
		Object parentId = getFieldValByName(PARENT_ID, metaObject);
		if (StringUtils.isEmpty(parentId)) {
			setFieldValByName(PARENT_ID, 0L, metaObject);
		}
		// 父id
		Object parentIds = getFieldValByName(PARENT_ID_S, metaObject);
		if (StringUtils.isEmpty(parentIds)) {
			setFieldValByName(PARENT_ID_S, "0", metaObject);
		}
	}

	/**
	 * 修改
	 * @param metaObject {@link MetaObject}
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		// 更新用户
		setFieldValByName(EDITOR, Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser(), metaObject);
		// 更新时间
		setFieldValByName(GMT_MODIFIED, LocalDateTime.now(), metaObject);
		// 父id
		Object parentId = getFieldValByName(PARENT_ID, metaObject);
		if (StringUtils.isEmpty(parentId)) {
			setFieldValByName(PARENT_ID, 0L, metaObject);
		}
		// 父id
		Object parentIds = getFieldValByName(PARENT_ID_S, metaObject);
		if (StringUtils.isEmpty(parentIds)) {
			setFieldValByName(PARENT_ID_S, "0", metaObject);
		}
	}
}