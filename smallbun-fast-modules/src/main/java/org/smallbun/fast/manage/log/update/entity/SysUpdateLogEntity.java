package org.smallbun.fast.manage.log.update.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.common.entity.DataEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 更新记录
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/4 18:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_update_log")
public class SysUpdateLogEntity extends DataEntity<Long> {
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 版本名称
	 */
	private String version;
	/**
	 * 发布时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;
}
