package cn.smallbun.scaffold.manage.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 组织机构状态枚举
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/25 19:12
 */
public enum GroupStatus implements IEnum<String> {
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

	GroupStatus(String code, String desc) {
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

	public static GroupStatus getStatus(String code) {
		GroupStatus[] values = values();
		for (GroupStatus status : values) {
			if (String.valueOf(status.getCode()).equals(code)) {
				return status;
			}
		}
		return null;
	}

}
