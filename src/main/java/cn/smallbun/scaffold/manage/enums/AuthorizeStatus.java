package cn.smallbun.scaffold.manage.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * AuthorizeStatusEnum
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/11 15:40
 */
public enum AuthorizeStatus implements IEnum<String> {
	/**
	 * 启用
	 */
	ENABLE("0", "启用"),
	/**
	 * 禁用
	 */
	DISABLE("1", "禁用");
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

	AuthorizeStatus(String code, String desc) {
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
