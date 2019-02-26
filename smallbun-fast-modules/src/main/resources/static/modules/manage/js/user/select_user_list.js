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

$(function () {
    //@formatter:off
    var options = {
        url: contextPath + 'user/page',
        sortOrder: "desc",
        search: false,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        columns: [{checkbox: true},
            {field: 'id', visible: false},
            {field: 'jobNumber', title: '工号',visible: false},
            {field: 'username', title: '用户名',formatter:$.table.emptyProcessing},
            {field: 'fullName', title: '姓名',formatter:$.table.emptyProcessing},
            {field: 'phone', title: '手机',formatter:$.table.emptyProcessing},
            {field: 'telephone', title: '电话',formatter:$.table.emptyProcessing},
            {field: 'email', title: '邮箱', visible: true,formatter:$.table.emptyProcessing},
            {field: 'org.orgName', title: '部门',formatter:$.table.emptyProcessing},
            {field: 'userStatusName', title: '状态', align: 'center'}]
    };
    $.table.init(options);
     //@formatter:off
    loadOrg();
     /**
     * 日期
     */
    laydate.render({
        elem: '#birthdayStart', theme: '#1890ff', max: new Date().toLocaleDateString()
    });
    laydate.render({
        elem: '#birthdayEnd', theme: '#1890ff', max: new Date().toLocaleDateString()
    });
});
/**
 * 加载部门
 */
function loadOrg(){
    var orgSetting = {
        view: {
            selectedMulti: false,
            showLine: true
        },
        check: {
            enable: false,
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
                var currentId = $('form').attr('id');
                var search = {};
                $.each($("#" + currentId).serializeArray(), function (i, field) {
                    search[field.name] = field.value;
                });
                search.orgId=treeNode.id;
                $('.table').bootstrapTable('refresh', {query: search});
            }
        }
    };
    $.post(contextPath + "org/list", function (data) {
        var orgTree = $.fn.zTree.init($("#orgTree"), orgSetting, data.result);
        //展开
        orgTree.expandAll(true);
    }, null, null, "正在加载，请稍后...");

}

/**
 * 选择用户并返回用户名
 * @author sw,SanLi
 * @date 2019年2月19日16:24:50
 * @returns {rows} 返回选中的工号
 */
function selectUsers(){
    var select_user = new Map();
    select_user.set('userName',$.table.selectColumns('username') );
    select_user.set('userId',$.table.selectFirstColumns());
    return select_user;
}