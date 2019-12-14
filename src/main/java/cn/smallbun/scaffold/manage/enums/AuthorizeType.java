package cn.smallbun.scaffold.manage.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * AuthorizeEnum
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/11 15:40
 */
public enum AuthorizeType implements IEnum<String> {
	/**
	 * 接口权限
	 */
	INTERFACE("2", "接口权限"),
	/**
	 * 操作权限
	 */
	OPERATE("1", "操作权限"),
	/**
	 * 路由权限
	 */
	ROUTE("0", "路由权限");
	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	AuthorizeType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * 枚举数据库存储值
	 */
	@Override
	public String getValue() {
		return this.code;
	}
}
