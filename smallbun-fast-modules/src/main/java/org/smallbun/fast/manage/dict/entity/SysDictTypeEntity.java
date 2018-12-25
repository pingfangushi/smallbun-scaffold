package org.smallbun.fast.manage.dict.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.smallbun.fast.common.entity.DataEntity;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 系统字典类型
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Getter
@Setter
@TableName("sys_dict_type")
public class SysDictTypeEntity extends DataEntity<Long> {

	/**
	 * 类型名称
	 */
	@NotEmpty(message = "类型名称不能为空")
	@TableField(condition = SqlCondition.LIKE)
	private String typeName;
	/**
	 * 类型编码
	 */
	@NotEmpty(message = "类型编码不能为空")
	private String typeCode;
	/**
	 * 字典值
	 */
	@TableField(exist = false)
	private List<SysDictValueEntity> dictValue;

}
