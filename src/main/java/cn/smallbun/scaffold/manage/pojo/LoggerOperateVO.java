package cn.smallbun.scaffold.manage.pojo;

import cn.smallbun.scaffold.manage.entity.SysLoggerOperateEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/2 14:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "操作日志", description = "系统操作日志VO")
public class LoggerOperateVO extends SysLoggerOperateEntity {
}
