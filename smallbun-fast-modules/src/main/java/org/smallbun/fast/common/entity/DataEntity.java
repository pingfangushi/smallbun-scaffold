package org.smallbun.fast.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.framework.base.BaseEntity;

/**
 * 数据实体类
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/10 20:20
 */
@Getter
@Setter
public class DataEntity<T> extends BaseEntity<T> {
	/**
	 * 创建者
	 */
	@TableField(value = "creator", el = "creator.id", fill = FieldFill.INSERT)
	private SysUserEntity creator;
	/**
	 * 修改者
	 */
	@TableField(value = "editor", el = "editor.id", fill = FieldFill.INSERT_UPDATE)
	private SysUserEntity editor;
}
