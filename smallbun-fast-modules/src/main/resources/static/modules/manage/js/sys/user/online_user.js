$(function () {

    var options = {
        url: contextPath + 'user/online',
        sidePagination: 'client',
        search: false,
        showExport: false,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        columns: [{checkbox: true},
            {field: 'sessionId', visible: false},
            {field: 'sessionId', visible: false},
            {field: 'username', title: '用户名'},
            {field: 'orgName', title: '归属部门'},
            {field: 'logInIp', title: '主机'},
            {field: 'logInAddress', title: '登录地点'},
            {field: 'browser', title: '浏览器'},
            {field: 'os', title: '操作系统'},
            {field: 'logInTime', title: '登录时间'},
            {field: 'lastAccessTime', title: '最后访问时间'},
            {
                title: '操作', width: 50,
                formatter: function (value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn bg-maroon btn-xs " href="#" onclick="expireUserSession(\'' + row.sessionId + '\')"><i class="fa fa-remove"></i> 强退</a>');
                    return actions.join('');
                }
            }]
    };
    $.table.init(options);
});

/**
 * 下线用户会话
 * @param sessionId
 */
function expireUserSession(sessionId) {
    $.modal.confirm("确定强退该用户吗？", function () {
        var data = {"sessionId": sessionId};
        $.operate.submit(contextPath + 'user/expireUserSession', "post", "json", data);
    });
}