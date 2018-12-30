package org.smallbun.fast.manage.org.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.framework.annotation.DictValue;

import java.io.Serializable;

/**
 * VO
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/23 23:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOrgVO extends SysOrgEntity {
	/**
	 * 这个字段为id，为了防止前端两个tree进行节点选择错误，而进行区分
	 */
	private Serializable orgId;
	/**
	 * 机构类型(字典值)
	 */
	@DictValue(valueField = "orgType", typeCode = "ORG_TYPE")
	private String orgTypeName;
	/**
	 * 机构等级（字典值）
	 */
	@DictValue(valueField = "grade", typeCode = "ORG_LEVEL")
	private String gradeName;

	public Serializable getOrgId() {
		return getId();
	}
}
