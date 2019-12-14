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
