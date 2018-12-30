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

package org.smallbun.framework.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.apache.commons.text.StringEscapeUtils;
import org.smallbun.framework.security.UserUtil;
import org.smallbun.framework.toolkit.AutoMapperUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 公用Controller
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/6
 */
public abstract class BaseController {

	private static final String NULL = "null";
	/**
	 * 项目名称
	 */
	@Value("${project.name}")
	private String name;
	/**
	 * 版本
	 */
	@Value("${project.version}")
	private String version;
	/**
	 * 提供者
	 */
	@Value("${project.powered-by}")
	private String poweredBy;

	/**
	 * 填充model
	 * @
	 * @param model {{@link Model}}
	 */
	@ModelAttribute
	protected void model(Model model) {
		model.addAttribute("title", name);
		model.addAttribute("version", version);
		model.addAttribute("poweredBy", poweredBy);
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
	public static <S, T> IPage<T> pageVOFilling(IPage<S> src, Class<?> targetClass) {
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
}
