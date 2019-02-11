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

package org.smallbun.framework.thymeleaf.processor;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.framework.toolkit.ReflectUtil;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Map;

/**
 * 树选择标签封装
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/8
 */
@EqualsAndHashCode(callSuper = true)
public class TreeSelectTabProcessor extends AbstractElementTagProcessor {
	/**
	 * 标签名
	 */
	private static final String TAG_NAME = "tree-select";
	/**
	 * 优先级
	 */
	private static final int PRECEDENCE = 10000;

	public TreeSelectTabProcessor(String dialectPrefix) {
		// 此处理器将仅应用于HTML模式
		super(TemplateMode.HTML,
				// 要应用于名称的匹配前缀
				dialectPrefix,
				// 标签名称：匹配此名称的特定标签
				TAG_NAME,
				// 将标签前缀应用于标签名称
				true,
				// 无属性名称：将通过标签名称匹配
				null,
				// 没有要应用于属性名称的前缀
				false,
				// 优先(内部方言自己的优先)
				PRECEDENCE);

	}


	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {

		final IModelFactory modelFactory = context.getModelFactory();
		final IModel model = modelFactory.createModel();
		//将请求参数转换为对象
		Map<String, String> attributeMap = tag.getAttributeMap();
		if (CollectionUtils.isEmpty(attributeMap)) {
			throw new RuntimeException("请添加标签参数");
		}
		TreeSelect treeSelect = JSON.parseObject(JSON.toJSONString(attributeMap), TreeSelect.class);
		//对Null进行处理
		ReflectUtil.emptyProcessing(treeSelect);
		//封装为HTML
		model.add(modelFactory.createText("<script th:src=\"/webjars/jquery/3.3.1/jquery.min.js\"></script>"));
		model.add(modelFactory.createText(
				"<div class=\"input-group\">\n" + "<input id=" + treeSelect.getId() + " name=" + treeSelect.getName()
						+ " class=\"form-control\" type=\"hidden\" value=\"\">\n" + "<input type=\"text\" id="
						+ treeSelect.getLabelId() + " name=" + treeSelect.getLabelName()
						+ " readonly=\"readonly\" value=" + treeSelect.getLabelValue() + " class=" + treeSelect
						.getCssClass() + " style=" + treeSelect.getCssStyle() + ">\n"
						+ "<span class=\"input-group-addon \" id=" + treeSelect.getLabelId() + "Search"
						+ "><i class=\"fa fa-search\"></i></span>\n" + " </div>"));
		model.add(modelFactory.createText(
				"<script type=\"text/javascript\">\n" + "    $(\"#" + treeSelect.getLabelId() + "Search, " + "#"
						+ treeSelect.getLabelId() + "\").click(function () {\n" + "        // 是否限制选择，如果限制，设置为disabled\n"
						+ "        if (" + treeSelect.getDisabled() + ") {\n" + "            return " + treeSelect
						.getDisabled() + ";\n" + "        }\n" + "        // 正常打开\n" + "        top.layer.open({\n"
						+ "            type: 2,\n" + "            area: ['280px', '420px'],\n" + "            title: \""
						+ treeSelect.getTitle() + "\",\n" + "            content: contextPath + \"" + treeSelect.getUrl()
						+ "\",\n" + "            btn: ['确定', '关闭'],\n" + "            yes: function (index, layer) {\n"
						+ "                var tree = layer.find(\"iframe\")[0].contentWindow.tree;//h.find(\"iframe\").contents();\n"
						+ "                var ids = [], names = [], nodes = [];\n"
						+ "                if (\"\" == \"true\") {\n"
						+ "                    nodes = tree.getCheckedNodes(true);\n" + "                } else {\n"
						+ "                    nodes = tree.getSelectedNodes();\n" + "                }\n"
						+ "                for (var i = 0; i < nodes.length; i++) {//\n"
						+ "                    ids.push(nodes[i].id);\n"
						+ "                    names.push(nodes[i].orgName);//\n"
						+ "                    break; // 如果为非复选框选择，则返回第一个选择\n" + "                }\n"
						+ "                $(\"#" + treeSelect.getId() + "\").val(ids.join(\",\"));\n"
						+ "                $(\"#" + treeSelect.getLabelId() + "\").val(names.join(\",\"));\n"
						+ "                $(\"#" + treeSelect.getLabelId() + "\").focus();\n"
						+ "                top.layer.close(index);\n" + "            },\n"
						+ "            cancel: function (index) { //或者使用btn2\n" + "                //按钮【按钮二】的回调\n"
						+ "            }\n" + "        });\n" + "\n" + "    });\n" + "</script>"));
		structureHandler.replaceWith(model, false);
	}
}

/**
 * TreeSelect 对标签参数进行封装
 */
@Data
class TreeSelect {
	/**
	 * 隐藏域 id
	 */
	private String id;
	/**
	 * 隐藏域 name
	 */
	private String name;

	/**
	 * 隐藏域 value
	 */
	private String value;

	/**
	 * 输入框 id
	 */
	private String labelId;

	/**
	 * 输入框 name
	 */
	private String labelName;

	/**
	 * 输入框 value
	 */
	private String labelValue;

	/**
	 * 弹出框标题
	 */
	private String title;

	/**
	 * 请求树机构树结构地址
	 */
	private String url;

	/**
	 * 是否显示复选框，如果不需要返回父节点，请设置notAllowSelectParent为true
	 */
	private String checked;
	/**
	 * 排除掉的编号（不能选择的编号）
	 */
	private String extId;
	/**
	 * 是否列出全部数据，设置true则不进行数据权限过滤（目前仅对Office有效）
	 */
	private String isAll;
	/**
	 * 不允许选择根节点
	 */
	private String notAllowSelectRoot;
	/**
	 * 不允许选择父节点
	 */
	private String notAllowSelectParent;
	/**
	 * 过滤栏目模型（只显示指定模型，仅针对CMS的Category树）
	 */
	private String module;
	/**
	 * 选择范围内的模型（控制不能选择公共模型，不能选择本栏目外的模型）（仅针对CMS的Category树）
	 */
	private String selectScopeModule;
	/**
	 * 是否允许清除
	 */
	private String allowClear;
	/**
	 * 文本框可填写
	 */
	private String allowInput;
	/**
	 * css样式
	 */
	private String cssClass = "\"form-control white-bg\"";

	/**
	 * css样式
	 */
	private String cssStyle;

	/**
	 * 缩小按钮显示
	 */
	private String smallBtn;
	/**
	 * 是否显示按钮
	 */
	private String hideBtn;
	/**
	 * 是否限制选择，如果限制，设置为disabled
	 */
	private String disabled;
}
