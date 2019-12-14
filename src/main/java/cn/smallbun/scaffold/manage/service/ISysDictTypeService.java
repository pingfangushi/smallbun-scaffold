package cn.smallbun.scaffold.manage.service;

import cn.smallbun.scaffold.manage.entity.SysDictTypeEntity;
import cn.smallbun.scaffold.manage.enums.DictStatus;
import com.alibaba.fastjson.annotation.JSONField;
import cn.smallbun.scaffold.framework.initialize.InitInterface;
import cn.smallbun.scaffold.framework.mybatis.service.BaseService;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 系统字典类型 服务类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
public interface ISysDictTypeService extends BaseService<SysDictTypeEntity>, InitInterface {
	/**
	 * 查询全部的字典，主要用于前端字典值展示,通过type获取到值，循环值渲染组件
	 * @return {@link List}
	 */
	List<Dict> dict();

	/**
	 * 根据ID更新状态
	 * @param id id
	 * @param status 状态
	 * @return 是否成功
	 */
	boolean updateStatusById(String id, DictStatus status);

	@Data
	class Dict {
		/**
		 * default : 0
		 * type : ROLE_STATUS
		 * items : [{"color":"green","label":"正常","value":"0"},{"color":"red","label":"禁用","value":"1"}]
		 */
		@JSONField(name = "default")
		@ApiModelProperty(value = "默认值")
		private String defaultValue;
		@ApiModelProperty(value = "类型编码")
		private String type;
		@ApiModelProperty(value = "字典项")
		private List<Item> items;
	}

	@Data
	@Accessors(chain = true)
	class Item {
		/**
		 * color : green
		 * label : 正常
		 * value : 0
		 */
		@ApiModelProperty(value = "颜色")
		private String color;
		@ApiModelProperty(value = "标签")
		private String label;
		@ApiModelProperty(value = "值")
		private String value;
	}
}
