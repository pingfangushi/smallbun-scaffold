/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
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
