package cn.smallbun.scaffold.manage.pojo;

import cn.smallbun.scaffold.manage.entity.SysNotifyEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *系统通知通告VO
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019/5/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "通知通告", description = "系统通知通告VO")
public class NotifyVO extends SysNotifyEntity {
}
