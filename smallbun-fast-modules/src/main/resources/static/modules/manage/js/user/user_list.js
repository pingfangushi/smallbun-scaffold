$(function () {

    var options = {
        url: contextPath + 'user/page',
        createUrl: "user/form",
        updateUrl: "user/form/{id}",
        removeUrl: contextPath + "/remove",
        exportUrl: contextPath + "/export",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "用户",
        search: false,
        showExport: false,
        showRefresh: false,
        showColumns: false,
        showToggle: false,
        columns: [{checkbox: true},
            {field: 'username', title: '用户名'},
            {field: 'fullName', title: '姓名', sortable: true},
            {field: 'phone', title: '手机'},
            {field: 'telephone', title: '电话'},
            {field: 'email', title: '邮箱', visible: false},
            {field: 'mobile', title: '归属公司'},
            {field: 'mobile', title: '归属部门'},
            {
                field: 'status', title: '状态', align: 'center',
                formatter: function (value, row, index) {
                    //return $.table.selectDictLabel(dist, value);
                }
            },
            {
                title: '操作', align: 'center', visible: false,
                formatter: function (value, row, index) {

                }
            }]
    };
    $.table.init(options);
});

