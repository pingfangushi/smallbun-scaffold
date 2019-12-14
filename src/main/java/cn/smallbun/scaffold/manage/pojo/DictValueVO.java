package cn.smallbun.scaffold.manage.pojo;

import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统字典数据VO
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019/5/27 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典数据", description = "系统字典数据VO")
public class DictValueVO extends SysDictItemEntity {
}
