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

package org.smallbun.framework.thymeleaf.dialect;


import org.smallbun.framework.thymeleaf.processor.TreeSelectTabProcessor;
import org.smallbun.framework.thymeleaf.processor.TreeShowTagProcessor;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

/**
 * SmallBun自定义方言封装
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/5
 */
public class SmallBunDialect extends AbstractProcessorDialect {
	/**
	 * 定义方言名称
	 */
	private static final String DIALECT_NAME = "SmallBun Dialect";

	public SmallBunDialect() {
		// 我们将设置此方言与“方言处理器”优先级相同
		// 标准方言, 以便处理器执行交错。
		super(DIALECT_NAME, "smallbun", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	/**
	 * 元素处理器：“matter”标签。
	 */
	@Override
	public Set<IProcessor> getProcessors(final String dialectPrefix) {
		Set<IProcessor> processors = new HashSet<>();
		//添加TreeShow树处理标签
		processors.add(new TreeShowTagProcessor(dialectPrefix));
		//添加TreeSelect树处理标签
		processors.add(new TreeSelectTabProcessor(dialectPrefix));
		processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
		return processors;
	}

}
