package cn.smallbun.scaffold.manage.pojo;

import cn.smallbun.scaffold.manage.entity.SysConfigEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置VO
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019/5/27 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统参数", description = "系统配置VO")
public class ConfigVO extends SysConfigEntity {
}
