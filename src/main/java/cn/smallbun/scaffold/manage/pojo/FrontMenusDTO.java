package cn.smallbun.scaffold.manage.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 展示菜单
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/10/30 9:37
 */
@Data
public class FrontMenusDTO implements Serializable {

	private String[] authority;
	private String icon;
	private String locale;
	private String name;
	private String path;
	private List<FrontMenusDTO> children;
}
