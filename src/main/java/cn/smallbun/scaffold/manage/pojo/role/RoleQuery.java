package cn.smallbun.scaffold.manage.pojo.role;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色页面查询参数
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/5/6 14:05
 */
@Data
public class RoleQuery implements Serializable {
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     */
    private String status;
}
