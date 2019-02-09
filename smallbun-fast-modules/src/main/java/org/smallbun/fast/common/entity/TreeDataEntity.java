package org.smallbun.fast.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 树形结构实体类
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/26 20:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TreeDataEntity<D, T> extends DataEntity<T> {
	/**
	 * 父级编号
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private T parentId;
	/**
	 * 所有父级编号
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String parentIds;
	/**
	 * 子集合
	 */
	@TableField(exist = false)
	@JSONField
	private List<?> children;
}
