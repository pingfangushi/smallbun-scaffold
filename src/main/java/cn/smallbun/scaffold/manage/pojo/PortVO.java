package cn.smallbun.scaffold.manage.pojo;

import cn.smallbun.scaffold.manage.entity.SysPortEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位VO
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019/5/27 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工岗位", description = "员工岗位VO")
public class PortVO extends SysPortEntity {
}
