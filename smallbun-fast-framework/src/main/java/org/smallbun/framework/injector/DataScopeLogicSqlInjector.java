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

package org.smallbun.framework.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.smallbun.framework.injector.methods.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 数据权限过滤
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 20:55
 */
public class DataScopeLogicSqlInjector extends LogicSqlInjector {

	@Override
	public List<AbstractMethod> getMethodList() {
		return Stream.of(
				new Insert(),
				new DataScopeLogicDelete(),
				new DataScopeLogicDeleteByMap(),
				new DataScopeLogicDeleteById(),
				new DataScopeLogicDeleteBatchByIds(),
				new DataScopeLogicUpdate(),
				new DataScopeLogicUpdateById(),
				new DataScopeLogicSelectById(),
				new DataScopeLogicSelectBatchByIds(),
				new DataScopeLogicSelectByMap(),
				new DataScopeLogicSelectOne(),
				new DataScopeLogicSelectCount(),
				new DataScopeLogicSelectMaps(),
				new DataScopeLogicSelectMapsPage(),
				new DataScopeLogicSelectObjs(),
				new DataScopeLogicSelectList(),
				new DataScopeLogicSelectPage()
		).collect(toList());
	}

}
