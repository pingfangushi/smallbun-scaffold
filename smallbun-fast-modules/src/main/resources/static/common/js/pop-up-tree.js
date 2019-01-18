/**封装弹出树控件封装*/
/**
 * 页面一开始加载，首先构建树控件
 */
$(document).ready(function () {

});
(function ($) {
    $.extend({
        pop_up_tree: {
            init: function (options) {
                //为了防止页面多个，使用随机数生产ID
                var treeId = "zTree" + Math.random().toString(36).substring(2);
                var treeLayerId = "tree-layer" + Math.random().toString(36).substring(2);
                /**
                 * zTree配置
                 */
                var setting = {
                    data: {
                        simpleData: {
                            enable: true,
                            idKey: options.idKey,
                            pIdKey: options.pIdKey,
                            rootPId: options.rootPId
                        },
                        key: {
                            name: options.name,
                            url: "x"
                        }
                    }
                };
                var ztree;
                var obj = options.obj;
                var value = $('#' + options.value);
                //在当前对象后面追加html内容
                obj.after(
                    '<div id="' + treeLayerId + '" style="display: none;">' +
                    '   <div class="box-header" id="search" style="display: block;padding: 20px;">' +
                    '    <label for="keyword">关键字：</label><input autocomplete="off" type="text" id="keyword" style="line-height: 24px; width:60%;border: 1px solid #bbb;padding: 0 4px;" maxlength="50">' +
                    '    <a class="btn btn-xs ibtn-primary" style="line-height: 22px;" onclick="$.ztree.searchNode(\'' + String(treeId) + '\')">搜索</a>' +
                    '   </div>' +
                    '   <div style="padding:10px 20px">' +
                    '      <div class="pull-right">' +
                    '         <a class="btn btn-box-tool" id="expand_default"   onclick="$.ztree.expandTree(\'' + String(treeId) + '\')">展开</a>/' +
                    '         <a class="btn btn-box-tool" id="collapse_default" onclick="$.ztree.closeTree(\'' + String(treeId) + '\')">折叠</a>' +
                    '      </div>' +
                    '      <input  id="' + options.value + '" type="text"  name="' + options.value + '" class="hidden"/>' +
                    '      <ul id="' + treeId + '" class="ztree"></ul>' +
                    '   </div>' +
                    '</div>'
                );
                value.val(obj.val());
                //发送ajax请求
                $.ajax({
                    type: options.type,
                    url: options.url,
                    contentType: "application/json",
                    async: false,
                    dataType: "json",
                    success: function (result) {
                        //如果成功
                        if (result.status === web_status.SUCCESS) {
                            //渲染ztree
                            ztree = $.fn.zTree.init($("#" + treeId), setting, result.result);
                            //根据已经选择的节点ID进行渲染
                            var node = ztree.getNodeByParam(options.idKey, value.val());
                            //如果node不为空进行查询
                            if (node != null) {
                                ztree.selectNode(node);
                                //当前输入框添加内容
                                obj.val(node[options.name]);
                            }
                        } else {
                            alert(result.msg);
                        }
                    },
                    error: function () {
                        alert("系统错误，请稍后重试！");
                    }
                });
                var width = '300px';
                var height = '450px';
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                /**
                 * 弹出layer
                 */
                layer.open({
                    type: 1,
                    offset: '50px',
                    title: "请选择",
                    area: [width, height],
                    shade: 0,
                    shadeClose: false,
                    content: jQuery("#" + treeLayerId), //弹框内容
                    btn: ['确定', '取消'],
                    btn1: function (index) {
                        var node = ztree.getSelectedNodes();
                        if (node.length > 0) {
                            //将选中的值放入隐藏value框
                            value.val(node[0][options.idKey]);
                            //将名称会显示内容框
                            obj.val(node[0][options.name]);
                        }
                        //选择上级菜单
                        layer.close(index);
                    }
                });
            }
        }
    })
})(jQuery);
