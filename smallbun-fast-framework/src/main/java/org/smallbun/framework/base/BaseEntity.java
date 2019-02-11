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

package org.smallbun.framework.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 抽象的基础类
 *
 * @author SanLi BaseEntity
 * Created by 2689170096@qq.com on 2018/4/30
 */
@Data
public abstract class BaseEntity<T> implements Serializable {
	/**
	 * 主键ID
	 */
	private T id;
	/**
	 * GMT 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@TableField(value = "gmt_create", fill = FieldFill.INSERT)
	private LocalDateTime gmtCreate;
	/**
	 * GMT 修改时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime gmtModified;
	/**
	 * 是否删除
	 */
	@TableLogic
	@TableField(value = "is_deleted", fill = FieldFill.INSERT_UPDATE)
	@JSONField(serialize = false)
	private String isDeleted = new GlobalConfig.DbConfig().getLogicNotDeleteValue();
	/**
	 * 备注
	 */
	@TableField(value = "remarks", fill = FieldFill.INSERT_UPDATE)
	private String remarks;


}
