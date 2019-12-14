package cn.smallbun.scaffold.manage.pojo;

import cn.smallbun.scaffold.manage.entity.SysUserEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户AO
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/5 19:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用户参数", description = "系统用户AO")
public class UserAO extends SysUserEntity {
	/**
	 * 角色ID集合
	 */
	private List<String> roleIds;
}
