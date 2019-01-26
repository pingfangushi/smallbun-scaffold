$(function () {
    //@formatter:off
    var options = {
        url: contextPath + 'user/page',
        createUrl: contextPath +"user/form",
        updateUrl: contextPath +"user/form{id}",
        removeUrl: contextPath + "user/removeById",
        batRemoveUrl: contextPath + "user/removeByIds",
        exportUrl: contextPath + "user/export",
        sortOrder: "desc",
        modalName: "用户",
        search: false,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        columns: [{checkbox: true},
            {field: 'id', visible: false},
            {field: 'username', title: '用户名'},
            {field: 'fullName', title: '姓名', sortable: true},
            {field: 'phone', title: '手机'},
            {field: 'telephone', title: '电话'},
            {field: 'email', title: '邮箱', visible: true},
            {field: 'org.orgName', title: '部门'},
            {field: 'userStatus', title: '状态', align: 'center', formatter: statusFormatter},
            {title: '操作', align: 'center', visible: true, formatter: function (value, row, index) {var actions = [];actions.push('<div class="btn-group"><button type="button" class="btn ibtn-primary btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog"></i>&nbsp;<span class="fa fa-chevron-down"></span></button>' +
               '<ul class="dropdown-menu" role="menu">' +
               '<li><a href="#" onclick="$.operate.viewTab(\'' + row.id + '\')"><i class="fa fa-search-plus"></i>查看</a></li>' +
               '<li><a href="#" onclick="$.operate.editTab(\'' + row.id + '\')"><i class="fa fa-edit"></i>修改</a></li>' +
               '<li><a href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-trash"></i>删除</a></li>' +
               '</ul>' +
               '</div>');
            return actions.join('');
            }}]
    };
    $.table.init(options);
     //@formatter:off
    loadOrg();
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
                $('.table').bootstrapTable('refresh', {query: {orgId:treeNode.id}});
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
 * 格式化用户状态
 * @param value
 * @param row
 * @param index
 */
statusFormatter = function (value, row, index) {
    if (value === '0') {
        return '<span class="label label-info">正常</span>';
    }
    if (value === '1') {
        return '<span class="label label-primary">禁用</span>';
    }
    if (value === '2') {
        return '<span class="label label-warning">锁定</span>';
    }
    return '';
};
