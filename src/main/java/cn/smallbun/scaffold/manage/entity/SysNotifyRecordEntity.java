/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.smallbun.scaffold.manage.entity;

import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统通知通告发送记录表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_notify_record")
public class SysNotifyRecordEntity extends BaseAuditEntity<String> {
	/**
	 * 通知通告ID
	 */
	@ApiModelProperty(value = "通知通告ID")
	@TableField("notify_")
	private Long notify;

	/**
	 * 接受用户ID
	 */
	@ApiModelProperty(value = "接受用户ID")
	@TableField("user_")
	private Long user;

	/**
	 * 是否阅读 0阅读 1未阅读
	 */
	@ApiModelProperty(value = "是否阅读 0阅读 1未阅读")
	@TableField("is_read")
	private String isRead;

	/**
	 * 阅读时间
	 */
	@ApiModelProperty(value = "阅读时间")
	@TableField("read_date")
	private LocalDateTime readDate;
}
