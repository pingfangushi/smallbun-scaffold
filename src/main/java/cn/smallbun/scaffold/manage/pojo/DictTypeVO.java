package cn.smallbun.scaffold.manage.pojo;

import cn.smallbun.scaffold.manage.entity.SysDictTypeEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统字典类型VO
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019/5/27 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典类型", description = "系统字典类型VO")
public class DictTypeVO extends SysDictTypeEntity {
}
