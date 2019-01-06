package org.smallbun.fast.manage.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.smallbun.fast.common.entity.TreeDataEntity;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.IGNORED;

/**
 * 部门机构实体类
 *
 * @author SanLi
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_org")
public class SysOrgEntity extends TreeDataEntity<SysOrgEntity, Long> {
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
    private String orgType;
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
    @TableField(strategy = IGNORED)
    private String address;
    /**
     * 邮政编码
     */
    @TableField(strategy = IGNORED)
    private String zipCode;
    /**
     * 负责人
     */
    @TableField(strategy = IGNORED)
    private String principal;
    /**
     * 电话
     */
    @TableField(strategy = IGNORED)
    private String telephone;
    /**
     * 传真
     */
    @TableField(strategy = IGNORED)
    private String fax;
    /**
     * 邮箱
     */
    @TableField(strategy = IGNORED)
    private String email;
    /**
     * 是否可用
     */
    private String useable;
}
