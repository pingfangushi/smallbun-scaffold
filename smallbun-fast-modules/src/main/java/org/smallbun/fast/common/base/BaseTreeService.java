package org.smallbun.fast.common.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.smallbun.fast.common.entity.TreeDataEntity;
import org.smallbun.framework.base.BaseService;

import java.util.List;

/**
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/5 23:17
 */
public interface BaseTreeService<T extends TreeDataEntity> extends BaseService<T> {
    /**
     * 返回tree列表
     *
     * @param queryWrapper
     * @return
     */
    List<T> tree(QueryWrapper<T> queryWrapper);
}
