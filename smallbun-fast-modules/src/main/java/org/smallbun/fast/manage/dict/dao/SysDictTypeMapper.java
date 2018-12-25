package org.smallbun.fast.manage.dict.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.smallbun.fast.manage.dict.entity.SysDictTypeEntity;
import org.smallbun.framework.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  系统字典类型 Mapper 接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Mapper
@Repository
public interface SysDictTypeMapper extends BaseMapper<SysDictTypeEntity> {

}
