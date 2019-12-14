package cn.smallbun.scaffold.manage.entity;

import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统通知通告表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_notify")
@ApiModel(value = "通知参数", description = "系统通知公告")
public class SysNotifyEntity extends BaseAuditEntity<String> {
	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题")
	@TableField("title_")
	private String title;

	/**
	 * 内容
	 */
	@ApiModelProperty(value = "内容")
	@TableField("content_")
	private String content;

	/**
	 * 类型
	 */
	@ApiModelProperty(value = "类型")
	@TableField("type_")
	private String type;
}
