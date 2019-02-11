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

package org.smallbun.framework.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.apache.commons.text.StringEscapeUtils;
import org.smallbun.framework.auto.SmallBunProperties;
import org.smallbun.framework.security.UserUtil;
import org.smallbun.framework.toolkit.AutoMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.smallbun.framework.toolkit.ReflectionUtil.getFieldAll;

/**
 * 公用Controller
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/6
 */
public abstract class BaseController<T> {
	private static final String NULL = "null";


	/**
	 * 填充model
	 * @
	 * @param model {{@link Model}}
	 */
	@ModelAttribute
	protected void model(Model model) {
		model.addAttribute("title", smallBunProperties.getProject().getName());
		model.addAttribute("version", smallBunProperties.getProject().getVersion());
		model.addAttribute("poweredBy", smallBunProperties.getProject().getPoweredBy());
	}

	/**
	 * 初始化
	 *
	 * @param binder {@link WebDataBinder}
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value == null ? "" : value.toString();
			}
		});
	}

	/**
	 * 把浏览器参数转化放到Map集合中
	 *
	 * @param request {@link HttpServletRequest}
	 * @return {@link Map}
	 */
	protected Map<String, Object> getParam(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>(16);
		String method = request.getMethod();
		Enumeration<?> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (key != null) {
				if (key instanceof String) {
					String value = request.getParameter(key.toString());
					//前台encodeURIComponent('我们');转码后到后台还是ISO-8859-1，所以还需要转码
					if ("GET".equals(method)) {
						value = new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
					}
					paramMap.put(key.toString(), value);
				}
			}
		}
		return paramMap;
	}

	/**
	 * 将数据刷新写回web端
	 *
	 * @param response        {@link HttpServletResponse}  response对象
	 * @param responseContent {@link String}  返回的数据
	 */
	protected void flushResponse(HttpServletResponse response, String responseContent) {
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("GBK");
			// 针对ajax中页面编码为GBK的情况，一定要加上以下两句
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/html;charset=UTF-8");
			writer = response.getWriter();
			if (responseContent == null || "".equals(responseContent) || NULL.equals(responseContent)) {
				writer.write("");
			} else {
				writer.write(responseContent);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	/**
	 * 页面VO转换
	 * @param src 来源
	 * @param targetClass 目标类
	 * @param <S>
	 * @param <T>
	 * @return
	 */
	protected static <S, T> IPage<T> pageVOFilling(IPage<S> src, Class<?> targetClass) {
		Page<T> page = new Page<>();
		page.setTotal(src.getTotal());
		page.setSize(src.getSize());
		page.setAsc(src.ascs());
		page.setCurrent(src.getCurrent());
		page.setDesc(src.descs());
		page.setOptimizeCountSql(src.optimizeCountSql());
		page.setPages(src.getPages());
		//设置结果集
		page.setRecords(AutoMapperUtil.mappingList(src.getRecords(), Lists.newArrayList(), targetClass));
		return page;
	}


	/**
	 * 获取session对象
	 *
	 * @return {@link HttpSession}
	 */
	protected HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	/**
	 * 获取用户信息
	 *
	 * @return {@link UserDetails}
	 */
	protected UserDetails getUserDetails() {
		return UserUtil.getLoginUser();
	}

	/**
	 * 由于ztree显示问题，如果json中有children字段，ztree图标显示为文件，此方法将List查询出来的Tree树集合对children字段fastJSON注解
	 * JSONField 中的serialize进行修改为false，通过此方式解决
	 * @param list
	 */
	protected List excludeZtreeChildrenField(List<Object> list) {
		for (Object o : list) {
			try {
				Field children = getFieldAll(o, "children");
				if (!StringUtils.isEmpty(children)) {
					children.setAccessible(true);
					JSONField annotation = children.getAnnotation(JSONField.class);
					if (!StringUtils.isEmpty(annotation)) {
						//获取 foo 这个代理实例所持有的 InvocationHandler
						InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
						// 获取 AnnotationInvocationHandler 的 memberValues 字段
						Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
						// 因为这个字段事 private final 修饰，所以要打开权限
						declaredField.setAccessible(true);
						// 获取 memberValues
						Map memberValues = (Map) declaredField.get(invocationHandler);
						// 修改 value 属性值
						memberValues.put("serialize", false);
						// 获取 foo 的 value 属性值
						annotation.serialize();
					}
				}
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * SmallBunProperties
	 */
	@Autowired
	private SmallBunProperties smallBunProperties;
}
