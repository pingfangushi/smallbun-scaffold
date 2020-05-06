package cn.smallbun.scaffold.manage.pojo.role;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色新增或修改VO对象
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/5/6 13:47
 */
@Data
public class RoleAddOrUpdateVO implements Serializable {
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 备注
     */
    private String remarks;

}
