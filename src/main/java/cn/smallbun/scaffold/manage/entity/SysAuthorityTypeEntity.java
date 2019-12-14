package cn.smallbun.scaffold.manage.entity;

import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 系统权限类型表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_authority_type")
@ApiModel(value = "权限参数", description = "系统权限类型信息")
public class SysAuthorityTypeEntity extends BaseAuditEntity<String> {

	/**
	 * 业务名称
	 */
	@TableField("name_")
	private String name;

	/**
	 * 业务编码
	 */
	@TableField("code_")
	private String code;
	/**
	 *  权限项
	 */
	@TableField(exist = false)
	private List<SysAuthorizeItemEntity> items;
}
