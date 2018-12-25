package org.smallbun.fast.manage.config.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.smallbun.fast.common.entity.DataEntity;

/**
 * 部门机构实体类
 * @author SanLi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "sys_config")
public class SysConfigEntity extends DataEntity<Long> {
	/**
	 * 管理平台请求地址前缀
	 */
	private String managementPrefix;
}
