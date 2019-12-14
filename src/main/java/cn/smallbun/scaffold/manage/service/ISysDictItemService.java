package cn.smallbun.scaffold.manage.service;

import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import cn.smallbun.scaffold.manage.enums.DictDefault;
import cn.smallbun.scaffold.framework.initialize.InitInterface;
import cn.smallbun.scaffold.framework.mybatis.service.BaseService;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 系统字典数据 服务类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
public interface ISysDictItemService extends BaseService<SysDictItemEntity>, InitInterface {
	/**
	 * 是否存在默认
	 * @param type 类型
	 * @return boolean
	 */
	boolean isExistDefault(Serializable type);

	/**
	 * 根据字典ID更新是否默认值
	 * @param id ID
	 * @param isDefault 默认值
	 * @return 是否成功
	 */
	boolean updateIsDefaultById(String id, DictDefault isDefault);

	/**
	 * 根据type类型删除数据
	 * @param idList idList
	 * @return boolean
	 */
	boolean removeByTypes(Collection<? extends Serializable> idList);
}
