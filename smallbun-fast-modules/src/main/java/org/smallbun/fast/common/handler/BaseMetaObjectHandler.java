package org.smallbun.fast.common.handler;


import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.smallbun.fast.manage.user.util.UserUtil;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * 填充创建时间，创建人，更新时间，更新人
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/12 22:05
 */
public class BaseMetaObjectHandler implements MetaObjectHandler {
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
		if (creator == null) {
			setFieldValByName(CREATOR, Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser(), metaObject);
		}
		// 修改用户
		Object editor = getFieldValByName(EDITOR, metaObject);
		if (editor == null) {
			setFieldValByName(EDITOR, Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser(), metaObject);
		}
		// 创建时间
		Object gmtCreate = getFieldValByName(GMT_CREATE, metaObject);
		if (gmtCreate == null) {
			setFieldValByName(GMT_CREATE, LocalDateTime.now(), metaObject);
		}
		// 修改时间
		Object gmtModified = getFieldValByName(GMT_MODIFIED, metaObject);
		if (gmtModified == null) {
			setFieldValByName(GMT_MODIFIED, LocalDateTime.now(), metaObject);
		}
		// 删除标记
		Object isDeleted = getFieldValByName(IS_DELETED, metaObject);
		if (isDeleted == null) {
			setFieldValByName(IS_DELETED, new GlobalConfig.DbConfig().getLogicNotDeleteValue(), metaObject);
		}
	}

	/**
	 * 修改
	 * @param metaObject {@link MetaObject}
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		// 更新用户
		Object updateBy = getFieldValByName(EDITOR, metaObject);
		if (updateBy == null) {
			setFieldValByName(EDITOR, Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser(), metaObject);
		}

		// 更新时间
		Object updateDate = getFieldValByName(GMT_MODIFIED, metaObject);
		if (updateDate == null) {
			setFieldValByName(GMT_MODIFIED, new Date(), metaObject);
		}
	}
}