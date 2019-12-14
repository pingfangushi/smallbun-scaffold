package cn.smallbun.scaffold.manage.entity;

import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统登录日志表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_logger_login")
public class SysLoggerLoginEntity extends BaseAuditEntity<String> {

	/**
	 * 用户(关联用户表ID)
	 */
	@TableField("user_")
	private String user;

	/**
	 * 登陆时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@TableField("login_time")
	private LocalDateTime loginTime;

	/**
	 * ip地址
	 */
	@TableField("ip_")
	private String ip;

	/**
	 * 地点
	 */
	@TableField("location_")
	private String location;

	/**
	 * 结果
	 */
	@TableField("result_")
	private String result;

	/**
	 * 浏览器
	 */
	@TableField("browser_")
	private String browser;

	/**
	 * 操作系统

	 */
	@TableField("os_")
	private String os;
}
