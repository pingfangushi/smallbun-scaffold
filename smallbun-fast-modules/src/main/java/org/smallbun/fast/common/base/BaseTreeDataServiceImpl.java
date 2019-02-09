package org.smallbun.fast.common.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.smallbun.fast.common.entity.TreeDataEntity;
import org.smallbun.framework.base.BaseMapper;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 树service
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/5 22:58
 */
public class BaseTreeDataServiceImpl<M extends BaseMapper<D>, D extends TreeDataEntity> extends BaseServiceImpl<M, D>
		implements BaseTreeService<D> {
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@Override
	public boolean removeById(Serializable id) {

		return super.removeById(id) && super.remove(new LambdaQueryWrapper<D>().eq(D::getParentId, id));
	}

	/**
	 * 根据ids移除
	 * @param idList
	 * @return
	 */
	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		//删除子节点
		idList.forEach(u -> super.remove(new LambdaQueryWrapper<D>().eq(D::getParentId, u)));
		return super.removeByIds(idList);
	}

	/**
	 * <p>
	 * TableId 注解存在更新记录，否插入一条记录
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return boolean
	 */
	@Override
	public boolean saveOrUpdate(D entity) {
		//获取父级ids
		D parent = baseMapper.selectById((Serializable) entity.getId());
		//设置ids
		entity.setParentIds(parent.getParentIds() + "," + parent.getId());
		return super.saveOrUpdate(entity);
	}


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
