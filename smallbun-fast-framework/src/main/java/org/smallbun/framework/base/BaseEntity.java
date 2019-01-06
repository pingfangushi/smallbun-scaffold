/*
 *
 * Copyright(c)[2018] [smallbun] www.smallbun.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
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
