package cn.smallbun.scaffold.manage.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * Default
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/21 21:09
 */
public enum DictDefault implements IEnum<String> {
	/**
	 * 是
	 */
	YES("0", "是"),
	/**
	 * 不是
	 */
	NO("1", "不是");

	private String code;
	private String desc;

	DictDefault(String code, String desc) {
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

	public static DictDefault getStatus(String code) {
		DictDefault[] values = values();
		for (DictDefault status : values) {
			if (String.valueOf(status.getCode()).equals(code)) {
				return status;
			}
		}
		return null;
	}
}
