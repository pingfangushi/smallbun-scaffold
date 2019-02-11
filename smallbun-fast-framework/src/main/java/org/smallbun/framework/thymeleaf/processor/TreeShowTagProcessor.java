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
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Map;

/**
 * 树结构标签处理器
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/5
 */
@EqualsAndHashCode(callSuper = true)
public class TreeShowTagProcessor extends AbstractElementTagProcessor {
	/**
	 * 标签名
	 */
	private static final String TAG_NAME = "tree-show";
	/**
	 * 优先级
	 */
	private static final int PRECEDENCE = 10000;

	public TreeShowTagProcessor(String dialectPrefix) {
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

	/**
	 * context 页面上下文
	 * tag  标签
	 */
	@Override
	protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
			final IElementTagStructureHandler structureHandler) {

		Map<String, String> attributeMap = tag.getAttributeMap();
		TreeShow treeShow = JSON.parseObject(JSON.toJSONString(attributeMap), TreeShow.class);
		ReflectUtil.emptyProcessing(treeShow);
		/**
		 * 创建将替换自定义标签的DOM结构。
		 * 内容将显示在“<div>”标签内, 因此必须首先创建,
		 * 然后必须向其中添加一个节点。
		 */
		final IModelFactory modelFactory = context.getModelFactory();
		final IModel model = modelFactory.createModel();
		model.add(modelFactory.createText(
				"<input type=\"hidden\" id=" + treeShow.getHideId() + " name=" + treeShow.getHideName() + ">\n"
						+ "<input type=\"hidden\" id=" + treeShow.getHidePId() + " name=" + treeShow.getHidePName()
						+ ">\n"));
		model.add(modelFactory
				.createText("<div id=" + treeShow.getId() + " class=\"ztree\" style=\"padding:0px;\"></div>\n"));
		model.add(modelFactory.createText(
				"<script type=\"text/javascript\">\n" + "    /*<![CDATA[*/\n" + "    contextPath = \"\\/\";\n"
						+ "    /*]]>*/\n" + "var tree;\n    var setting = {\n" + "        view: {\n"
						+ "            selectedMulti: false\n" + "        },\n" + "        check: {\n"
						+ "            enable: " + treeShow.getMultiSelect() + ",\n"
						+ "            chkboxType: {\"Y\": \"" + treeShow.getChkboxType() + "\", \"N\": \"" + treeShow
						.getChkboxType() + "\"}\n" + "\n" + "        },\n" + "        data: {\n"
						+ "            simpleData: {\n" + "                enable: true,\n"
						+ "                idKey: \"" + treeShow.getIdKey() + "\",\n" + "                pIdKey: \""
						+ treeShow.getPIdKey() + "\",\n" + "                rootparentId: -1\n" + "            },\n"
						+ "            key: {\n" + "                url: \"xUrl\"\n" + ",name:\"" + treeShow.getName()
						+ "\"            }\n" + "        },\n" + "        edit: {\n" + "            enable: false\n"
						+ "        },\n" + "        callback: {\n"
						+ "            onClick: function (event, treeId, treeNode) {\n"
						+ "                tree.expandNode(treeNode);\n" + "                $(\"#" + treeShow
						.getHideId() + "\").val(treeNode." + treeShow.getIdKey() + ");\n" + "                $(\"#"
						+ treeShow.getHidePId() + "\").val(treeNode." + treeShow.getPIdKey() + ");\n"
						+ "                //刷新表\n $.table.refreshAll()" + "            }\n" + "        }\n" + "    };\n"
						+ "    /**\n" + "     * 加载树\n" + "     */\n" + "    loadTree = function () {\n"
						+ "        $.post(contextPath+\"" + treeShow.getUrl() + "\", function (data) {\n"
						+ "            tree = $.fn.zTree.init($(\"#" + treeShow.getId()
						+ "\"), setting, data.result);\n" + "            // 展开第一级节点\n"
						+ "            var nodes = tree.getNodesByParam(\"level\", 0);\n"
						+ "            for (var i = 0; i < nodes.length; i++) {\n"
						+ "                tree.expandNode(nodes[i], true, false, false);\n" + "            }\n"
						+ "            // 展开第二级节点\n" + "            nodes = tree.getNodesByParam(\"level\", 1);\n"
						+ "            for (var i = 0; i < nodes.length; i++) {\n"
						+ "                tree.expandNode(nodes[i], true, false, false);\n" + "            }\n"
						+ "        }, null, null, \"正在加载，请稍后...\");\n" + "    };\n" + "    /**\n" + "     * 初始化\n"
						+ "     */\n" + "    $(document).ready(function () {\n" + "        loadTree();\n" + "    });\n"
						+ "</script>"));
		//指示引擎用指定的模型替换整个元素
		structureHandler.replaceWith(model, false);

	}


}

@Data
class TreeShow {
	/**
	 * Tree主键
	 */
	private String id;
	/**
	 * 对应: data key 里面的name值 默认name
	 * var setting = {
	 * 	data: {
	 * 		key: {
	 * 			name: "ename"
	 *            }*
	 *        }
	 * };
	 */
	private String name = "name";
	/**
	 * 树结构 idKey
	 */
	private String idKey;
	/**
	 * 树结构 pIdKey
	 */
	private String pIdKey;

	/**
	 * 隐藏域 子ID
	 */
	private String hideId;
	/**
	 * 隐藏域 子Name属性
	 */
	private String hideName;
	/**
	 * 隐藏域 父ID属性
	 */
	private String hidePId;
	/**
	 * 隐藏域 父Name属性
	 */
	private String hidePName;
	/**
	 * 获取URL路径
	 */
	private String url;
	/**
	 * 是否有复选框
	 */
	private String multiSelect;
	/**
	 * chkboxType: { “Y”: “ps”, “N”: “ps” }
	 * Y 属性定义 checkbox 被勾选后的情况；
	 * N 属性定义 checkbox 取消勾选后的情况；
	 * “p” 表示操作会影响父级节点；
	 * “s” 表示操作会影响子级节点。
	 * 请注意大小写，不要改变
	 */
	private String chkboxType;
}
