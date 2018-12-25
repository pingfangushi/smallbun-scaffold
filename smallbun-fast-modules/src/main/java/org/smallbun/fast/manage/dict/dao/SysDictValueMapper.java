package org.smallbun.fast.manage.dict.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.smallbun.fast.manage.dict.entity.SysDictValueEntity;
import org.smallbun.fast.manage.dict.vo.SysDictValueVO;
import org.smallbun.framework.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统字典数据 Mapper 接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Mapper
@Repository
public interface SysDictValueMapper extends BaseMapper<SysDictValueEntity> {
	/**
	 * 分页查询
	 * @param page {@link Page<SysDictValueEntity>}
	 * @param vo {@link SysDictValueVO}
	 * @return {@link Page<SysDictValueEntity>}
	 */
	IPage<SysDictValueEntity> page(Page<SysDictValueEntity> page, @Param("p") SysDictValueVO vo);

	/**
	 * 根据类别获取字典值
	 * @param typeId 字典类型
	 * @return {@link SysDictValueEntity}
	 */
	@Select("select * from sys_dict_value where dict_type=#{typeId}")
	List<SysDictValueEntity> findByTypeId(String typeId);

	/**
	 * 根据类型编码查询字典值
	 * @param typeCode
	 * @return
	 */
	List<SysDictValueEntity> findByTypeCode(String typeCode);

	/**
	 * 根据字典类型和字典值查询字典名称
	 * @param typeCode
	 * @param dictValue
	 * @return
	 */
	String findLabelByTypeCodeAndValue(@Param("typeCode") String typeCode,
			@Param("dictValue") String dictValue);
}
