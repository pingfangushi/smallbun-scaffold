/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
