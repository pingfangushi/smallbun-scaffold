package cn.smallbun.scaffold.manage.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * Status
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/21 21:09
 */
public enum DictStatus implements IEnum<String> {
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

	DictStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

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

	/**
	 * 枚举数据库存储值
	 */
	@Override
	public String getValue() {
		return this.code;
	}

	public static DictStatus getStatus(String code) {
		DictStatus[] values = values();
		for (DictStatus status : values) {
			if (String.valueOf(status.getCode()).equals(code)) {
				return status;
			}
		}
		return null;
	}
}
