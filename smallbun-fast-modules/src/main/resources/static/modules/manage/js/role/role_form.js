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

/**
 * 提交事件
 */
function doSubmit() {
    //获取from表单的值
    var serializeArray = $('.form-horizontal').serializeArray();
    //菜单节点
    var menuNodes = $.ztree.getCheckedNodes('menuTree');
    var menuIds = [];
    for (var i = 0; i < menuNodes.length; i = i + 1) {
        menuIds.push(menuNodes[i].id);
    }
    for (var i = 0; i < menuIds.length; i++) {
        serializeArray.push({"name": "menuList[" + i + "].id", "value": menuIds[i]});
    }

    //如果是按照明细设置,获取数据节点，并添加参数
    if ($("#dataScope").select2("val") === '9') {
        //菜单节点
        var orgNodes = $.ztree.getCheckedNodes('orgTree');
        var orgIds = [];
        for (var i = 0; i < orgNodes.length; i = i + 1) {
            orgIds.push(orgNodes[i].id);
        }
        for (var i = 0; i < orgIds.length; i++) {
            serializeArray.push({"name": "orgList[" + i + "].id", "value": orgIds[i]});
        }
    }


    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        $.operate.save($('.form-horizontal').attr('action'), serializeArray);
    }
}

/**
 * 验证
 */
$(".form-horizontal").validate({
    onkeyup: false,
    rules: {
        roleName: {
            required: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "role/unique",
                type: "post",
                dataType: 'JSON',
                data: {
                    id: function () {
                        return $("#id").val();
                    },
                    roleName: function () {
                        return $("#roleName").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        },
        enName: {
            required: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "role/unique",
                type: "post",
                dataType: 'JSON',
                data: {
                    id: function () {
                        return $("#id").val();
                    },
                    enName: function () {
                        return $("#enName").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        }
    },
    messages: {
        roleName: {
            required: "请输入角色名称",
            remote: "角色名称已存在"
        },
        enName: {
            remote: "角色编码已存在"
        }
    }
});





/**
 * 点击导航菜单事件
 * 勾选全部节点和撤销全部节点
 */
//菜单权限
$('#menuCheckAllNodes').on('ifUnchecked', function () {
    $.ztree.checkAllFalse('menuTree');
});
$('#menuCheckAllNodes').on('ifChecked', function () {
    $.ztree.checkAllTrue('menuTree');
});
//明细设置
$('#orgCheckAllNodes').on('ifUnchecked', function () {
    $.ztree.checkAllFalse('orgTree');
});
$('#orgCheckAllNodes').on('ifChecked', function () {
    $.ztree.checkAllTrue('orgTree');
});
/**
 * 数据权限下拉框选择事件
 */
$("#dataScope").on('select2:select', function (e) {
    // e 的话就是一个对象 然后需要什么就 “e.参数” 形式 进行获取
    if (e.params.data.id === '9') {
        $('#org-tree-div').slideDown("slow");
        loadOrgTree();
    } else {
        $('#org-tree-div').slideUp("slow");
    }
});

/**
 * 初始化
 */
$(function () {
    /**
     * 加载菜单树
     */
    loadMenuTree();
    /**
     * 如果初始化完成
     */
    if ($('#dataScope').hasClass("select2-hidden-accessible")) {
        /*已初始化完成*/
        if ($("#dataScope").select2("val") === '9') {
            $('#org-tree-div').slideDown("slow");
            //加载orgTree
            loadOrgTree();
        } else {
            $('#org-tree-div').slideUp("slow");
        }
    }
});

function loadMenuTree() {
    /**
     * 系统菜单
     * @type {{view: {selectedMulti: boolean, showLine: boolean}, data: {simpleData: {idKey: string, enable: boolean, pIdKey: string, rootPId: number}, key: {name: string}}, callback: {onClick: onClick}, check: {enable: boolean, chkboxType: {Y: string, N: string}}}}
     */
    var menuSetting = {
        view: {
            selectedMulti: false,
            showLine: true
        },
        check: {
            enable: true,
            chkboxType: {"Y": "ps", "N": "ps"}
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: 0
            },
            key: {
                name: "nodeName"
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode) {

            }
        }
    };
    $.post(contextPath + "menu/list", function (data) {
        var menuTree = $.fn.zTree.init($("#menuTree"), menuSetting, data.result);
        //不展开
        menuTree.expandAll(false);
        //如果id不为空，类型为修改，勾选菜单
        if ($('#id').val() !== '' && $('#id').val() !== null && $('#id').val() !== undefined) {
            //获取用户对应的菜单
            var menus = JSON.parse($('#menusJson').val());
            for (var i = 0; i < menus.length; i++) {
                //根据角色菜单节点数据的属性搜索，获取与完整菜单树完全匹配的节点JSON对象集合
                var nodes = menuTree.getNodesByParam("id", menus[i].id, null);
                //勾选当前选中的节点
                menuTree.checkNode(nodes[0], true, false);
                //展开这个节点
                menuTree.expandNode(nodes[0], true, false);
            }
        }
    }, null, null, "正在加载，请稍后...");
}

function loadOrgTree() {
    /**
     * 按照明细设置部门
     * @type {{view: {selectedMulti: boolean, showLine: boolean}, data: {simpleData: {idKey: string, enable: boolean, pIdKey: string, rootPId: number}, key: {name: string}}, callback: {onClick: onClick}, check: {enable: boolean, chkboxType: {Y: string, N: string}}}}
     */
    var orgSetting = {
        view: {
            selectedMulti: false,
            showLine: true
        },
        check: {
            enable: true,
            chkboxType: {"Y": "ps", "N": "ps"}
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "orgId",
                pIdKey: "parentId",
                rootPId: 0
            },
            key: {
                name: "orgName"
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode) {

            }
        }
    };
    $.post(contextPath + "org/list", function (data) {
        var orgTree = $.fn.zTree.init($("#orgTree"), orgSetting, data.result);
        //不展开
        orgTree.expandAll(false);
        //如果id不为空，类型为修改，勾选菜单
        if ($('#id').val() !== '' && $('#id').val() !== null && $('#id').val() !== undefined) {
            //如果选择的修改之前一样
            if ($("#dataScope").select2("val") === $('#data-scope').val()) {
                //获取用户对应的详细部门
                var orgs = JSON.parse($('#orgsJson').val());
                //遍历勾选角色关联的菜单数据
                for (var i = 0; i < orgs.length; i++) {
                    //根据角色部门节点数据的属性搜索，获取与完整菜单树完全匹配的节点JSON对象集合
                    var nodes = orgTree.getNodesByParam("orgId", orgs[i].id, null);
                    //勾选当前选中的节点
                    orgTree.checkNode(nodes[0], true, false);
                    //展开这个节点
                    orgTree.expandNode(nodes[0], true, false);
                }
            }

        }
    }, null, null, "正在加载，请稍后...");
}