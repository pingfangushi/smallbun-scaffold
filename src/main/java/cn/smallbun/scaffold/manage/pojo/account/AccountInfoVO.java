package cn.smallbun.scaffold.manage.pojo.account;

import cn.smallbun.scaffold.framework.security.authority.AuthorityInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * 账户信息
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/5/6 15:45
 */
@Data
public class AccountInfoVO implements Serializable {
    /**
     * ID
     */
    private String        id;

    /**
     * 用户名
     */
    private String        username;
    /**
     * 姓名
     */
    private String        name;
    /**
     * 手机号
     */
    private String        telephone;
    /**
     * 头像
     */
    private String        avatar;
    /**
     * 授权
     */
    private AuthorityInfo authorize;
    /**
     * 登录IP
     */
    private String        lastLoginIp;
    /**
     * 状态
     */
    private Integer       status;
    /**
     * 最后登录时间
     */
    private String        lastLoginTime;
}
