package org.smallbun.fast.common.interceptor;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.smallbun.fast.common.interceptor.DataScopeFilter.dataScopeFilter;


/**
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2019-03-01 14:58
 */
public class SelectVisitorImpl implements SelectVisitor {
	@Override
	public void visit(PlainSelect plainSelect) {
		//判断有没有关联关系，如果没有，说明为单表
		if (CollectionUtils.isEmpty(plainSelect.getJoins())) {
			//如果没有表别名
			if (StringUtils.isEmpty(plainSelect.getFromItem().getAlias())) {
				//拼接权限语句
				dataScopeFilter();
			}
			//如果具有表别名
			else {
				//别名
				String alias = plainSelect.getFromItem().getAlias().getName();
				//拼接权限语句
			}

		}
		//如果有关联关系
		else {

		}


		List<Join> joins = plainSelect.getJoins();
		//为空说明没有进行任何关联，拼接关联关系
		if (CollectionUtils.isEmpty(joins)) {
			joins = Lists.newArrayList();
			Join join = new Join();
			join.setLeft(true);
			join.setRightItem(new Table("sys_user a"));
			BinaryExpression onExpression = new EqualsTo();
			onExpression.setLeftExpression(new Column("a.id"));
			onExpression.setRightExpression(new Column(((Table) plainSelect.getFromItem()).getName() + ".creator"));
			join.setOnExpression(onExpression);
			joins.add(join);
			plainSelect.setJoins(joins);
		}
		System.err.println(plainSelect);
	}

	@Override
	public void visit(SetOperationList setOpList) {

	}

	@Override
	public void visit(WithItem withItem) {

	}

	@Override
	public void visit(ValuesStatement aThis) {

	}
}
