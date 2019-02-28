/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.fast.common;

import com.baomidou.mybatisplus.core.parser.AbstractJsqlParser;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.values.ValuesStatement;

/**
 * 数据权限解析
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/28 20:40
 */
public class DataPermissionSqlParser extends AbstractJsqlParser {

	@Override
	public void processInsert(Insert insert) {
		// to do nothing
	}

	@Override
	public void processDelete(Delete delete) {
		// to do nothing
	}

	@Override
	public void processUpdate(Update update) {
		// to do nothing
	}

	@Override
	public void processSelectBody(SelectBody selectBody) {
		selectBody.accept(new SelectVisitor() {
			/**
			 * 正常的select
			 * @param plainSelect
			 */
			@Override
			public void visit(PlainSelect plainSelect) {
				// 访问 select
				if (plainSelect.getSelectItems() != null) {
					plainSelect.getSelectItems().forEach(u -> {
						System.err.println(u);
					});
				}
				// 访问from
				FromItem fromItem = plainSelect.getFromItem();
				System.err.println(fromItem);

				// 访问where
				if (plainSelect.getWhere() != null) {
					System.err.println(plainSelect.getWhere());
				}
				// 访问join
				if (plainSelect.getJoins() != null) {
					for (Join join : plainSelect.getJoins()) {
						System.err.println(join);
					}
				}

				// 访问 order by
				if (plainSelect.getOrderByElements() != null) {
					for (OrderByElement orderByElement : plainSelect.getOrderByElements()) {
						System.err.println(orderByElement);
					}
				}

				// 访问group by having
				if (plainSelect.getHaving() != null) {
					System.err.println(plainSelect.getHaving());
				}

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
		});
	}
}

