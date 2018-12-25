package org.smallbun.fast.manage.config.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.smallbun.fast.manage.config.entity.SysConfigEntity;
import org.springframework.stereotype.Repository;

/**
 * 系统配置Mapper接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/3
 */
@Mapper
@Repository
public interface SysConfigMapper extends BaseMapper<SysConfigEntity> {

}
