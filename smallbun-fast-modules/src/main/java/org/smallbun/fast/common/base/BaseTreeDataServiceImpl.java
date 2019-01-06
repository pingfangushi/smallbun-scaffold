package org.smallbun.fast.common.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.smallbun.fast.common.entity.TreeDataEntity;
import org.smallbun.framework.base.BaseMapper;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 树service
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/5 22:58
 */
public class BaseTreeDataServiceImpl<M extends BaseMapper<D>, D extends TreeDataEntity> extends BaseServiceImpl<M, D> implements BaseTreeService<D> {


    /**
     * 返回tree列表
     *
     * @param queryWrapper {@link QueryWrapper}
     * @return {@link List}
     */
    @Override
    public List<D> tree(QueryWrapper<D> queryWrapper) {
        return build(baseMapper.selectList(new QueryWrapper<>()));
    }

    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public List<D> build(List<D> treeNodes) {
        List<D> trees = new ArrayList<>();
        for (D treeNode : treeNodes) {
            if ("0".equals(String.valueOf(treeNode.getParentId()))) {
                trees.add(treeNode);
            }
            for (D it : treeNodes) {
                if (String.valueOf(it.getParentId()).equals(String.valueOf(treeNode.getId()))) {
                    if (StringUtils.isEmpty(treeNode.getChildren())) {
                        treeNode.setChildren(new ArrayList<D>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    private List<D> buildByRecursive(List<D> treeNodes) {
        List<D> trees = new ArrayList<>();
        for (D treeNode : treeNodes) {
            if ("0".equals(String.valueOf(treeNode.getParentId()))) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    private D findChildren(D treeNode, List<D> treeNodes) {
        for (D it : treeNodes) {
            if (String.valueOf(treeNode.getId()).equals(String.valueOf(it.getParentId()))) {
                if (StringUtils.isEmpty(treeNode.getChildren())) {
                    treeNode.setChildren(new ArrayList<D>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

}
