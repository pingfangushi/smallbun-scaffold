package cn.smallbun.scaffold.manage.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cn.smallbun.scaffold.manage.entity.SysLoggerLoginEntity;

/**
 * 登录日志
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/2 14:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "登录日志", description = "系统登录日志VO")
public class LoggerLoginVO extends SysLoggerLoginEntity {
}
