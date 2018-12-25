package org.smallbun.fast.manage.dict.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.common.entity.DataEntity;

/**
 * 系统字典数据
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict_value")
@JsonIgnoreProperties(value = {"handler"})
public class SysDictValueEntity extends DataEntity<Long> {


	/**
	 * 所属字典类型(关联sys_dict_type表)
	 */
	@TableField(exist = false)
	private SysDictTypeEntity sysDictType;
	/**
	 * 所属字典类型
	 */
	private String dictType;
	/**
	 * 标签
	 */
	private String dictLabel;

	/**
	 * 值
	 */
	private String dictValue;

	/**
	 * 排序
	 */
	private Integer sort;
}
