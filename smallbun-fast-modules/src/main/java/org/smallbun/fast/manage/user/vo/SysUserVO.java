package org.smallbun.fast.manage.user.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.manage.role.vo.SysRoleVO;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.framework.annotation.DictValue;

import java.util.List;

/**
 * 用户VO
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/24 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserVO extends SysUserEntity {
	/**
	 * 用户状态
	 */
	@DictValue(typeCode = "USER_STATUS", valueField = "userStatus")
	private String userStatusName;

	/**
	 * 角色vo集合
	 */
	private List<SysRoleVO> roleVOS;
	/**
	 * 出生日期起
	 */
	private String birthdayStart;
	/**
	 * 出生日期止
	 */
	private String birthdayEnd;

}
