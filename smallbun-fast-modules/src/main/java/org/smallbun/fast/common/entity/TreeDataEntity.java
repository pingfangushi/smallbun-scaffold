package org.smallbun.fast.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 树形结构实体类
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/26 20:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TreeDataEntity<T> extends DataEntity<T> {

	/**
	 * 父级编号
	 */
	private T parentId;
	/**
	 * 所有父级编号
	 */
	private String parentIds;
}
