package cn.smallbun.scaffold.manage.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/10/27 15:26
 */
@Data
public class LoginResultDTO implements Serializable {
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 权限
	 */
	private List<String> currentAuthority;
	/**
	 * token
	 */
	private String token;
}
