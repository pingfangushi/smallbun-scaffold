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

package org.smallbun.framework.aspect;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.smallbun.framework.annotation.AutoQueryDictValue;
import org.smallbun.framework.base.IAutoQueryDictValue;
import org.smallbun.framework.result.AjaxResult;
import org.smallbun.framework.result.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动注入字典值
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/16 0:11
 */
@Slf4j
@Order(value = 1)
@Aspect
@Component
public class AutoQueryDictValueAspect {
	@Autowired
	public AutoQueryDictValueAspect(IAutoQueryDictValue iAutoQueryDictValue) {
		this.iAutoQueryDictValue = iAutoQueryDictValue;
	}

	/**
	 * 切面方法返回结果实体 | 集合内对象是否存在@ColumnDesc注解配置
	 * 如果存在，根据对应的配置查询出描述值
	 *
	 * @param proceedingJoinPoint 切面方法实例
	 * @param autoQueryDictValue 切面注解实例
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(autoQueryDictValue)")
	public Object autoQueryColumnDesc(ProceedingJoinPoint proceedingJoinPoint, AutoQueryDictValue autoQueryDictValue)
			throws Throwable {
		Long start = System.currentTimeMillis();
		StringBuilder stringBuilderStart = new StringBuilder().append("开始处理字典值自动设置切面逻辑").append(StringPool.NEWLINE);
		log.info("{}", stringBuilderStart);
		/**
		 * 执行方法，获取返回值
		 */
		Object result = proceedingJoinPoint.proceed();
		if (ObjectUtils.isEmpty(result)) {
			return result;
		}
		/**
		 * 返回值为List集合时
		 */
		else if (result instanceof List) {
			List<Object> list = (List<Object>) result;
			iAutoQueryDictValue.autoQuery(list);
		}
		/**
		 * 如果返回值类型为Page
		 */
		else if (result instanceof Page) {
			log.info("{}", ((Page) result).getRecords());
			iAutoQueryDictValue.autoQuery(((Page) result).getRecords());

		}
		/**
		 * 如果返回值类型为AjaxResult
		 */
		else if (result instanceof AjaxResult) {
			log.info("{}", ((AjaxResult) result).getResult());
			ArrayList<Object> objects = (ArrayList<Object>) ((AjaxResult) result).getResult();
			iAutoQueryDictValue.autoQuery(objects);

		}
		/**
		 * 如果返回值类型为PageableResult
		 */
		else if (result instanceof PageableResult) {
			Object page = ((PageableResult) result).getPage();
			//如果page方法中范围值为mybatis plus 分页page对象
			if (page instanceof Page) {
				iAutoQueryDictValue.autoQuery(((Page) page).getRecords());
			}
		}
		/**
		 * 返回值为单值时
		 */
		else {
			iAutoQueryDictValue.autoQuery(result);
		}
		long end = System.currentTimeMillis();
		StringBuilder stringBuilderEnd = new StringBuilder().append(" Time：").append(end - start).append("ms")
				.append("字典值自动设置逻辑处理完成").append(StringPool.NEWLINE);
		log.info("{}", stringBuilderEnd);
		return result;
	}

	private final IAutoQueryDictValue iAutoQueryDictValue;

}
