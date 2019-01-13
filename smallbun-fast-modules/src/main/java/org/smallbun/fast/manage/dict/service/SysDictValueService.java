package org.smallbun.fast.manage.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.smallbun.fast.manage.dict.entity.SysDictValueEntity;
import org.smallbun.fast.manage.dict.vo.SysDictValueVO;
import org.smallbun.framework.result.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  字典值 业务层接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
public interface SysDictValueService extends IService<SysDictValueEntity> {
	/**
	 * 分页查询
	 * @param page page
	 * @param vo
	 * @return
	 */
	IPage<SysDictValueEntity> page(Page<SysDictValueEntity> page, SysDictValueVO vo);

	/**
	 * 查询唯一
	 * @param vo
	 * @return
	 */
	Boolean unique(SysDictValueEntity vo);

	/**
	 * 根据type code 查询字典值
	 * @param typeCode
	 * @return
	 */
	List<SysDictValueEntity> findByTypeCode(String typeCode);

	/**
	 * 根据type code 和值查询label
	 * @param typeCode typeCode
	 * @param dictValue dictValue
	 * @return {@link SysDictValueEntity}
	 */
	String findLabelByTypeCodeAndValue(String typeCode, String dictValue);

	/**
	 * model
	 * @param request
	 * @return
	 */
	SysDictValueVO model(HttpServletRequest request);
}
