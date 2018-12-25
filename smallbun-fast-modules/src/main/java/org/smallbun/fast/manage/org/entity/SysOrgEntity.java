package org.smallbun.fast.manage.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.smallbun.fast.common.entity.DataEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 部门机构实体类
 * @author SanLi
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_org")
public class SysOrgEntity extends DataEntity<Long> {

	/**
	 * 父级编号
	 */
	private Long parentId;
	/**
	 * 所有父级编号
	 */
	private String parentIds;
	/**
	 * 子部门
	 */
	@TableField(exist = false)
	private List<SysOrgEntity> childDeptList;
	/**
	 * 名称
	 */
	private String orgName;
	/**
	 * 机构编码
	 */
	private String orgCode;
	/**
	 * 归属区域
	 */
	private Long areaId;
	/**
	 * 机构类型
	 */
	private String type;
	/**
	 * 机构等级
	 */
	private String grade;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private BigDecimal zipCode;
	/**
	 * 负责人
	 */
	private String principal;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 状态 :0 启用 1 未启用
	 */
	private String orgStatus;
	/**
	 * 排序
	 */
	private Integer sort;
}
