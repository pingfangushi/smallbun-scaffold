$(function () {

    var options = {
        url: contextPath + 'monitor/online/user/list',
        sidePagination: 'client',
        search: false,
        showExport: false,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        columns: [{checkbox: true},
            {field: 'sessionId', title: '会话', visible: false},
            {field: 'username', title: '用户'},
            {field: 'orgName', title: '部门'},
            {field: 'logInIp', title: '主机'},
            {field: 'logInAddress', title: '登录地'},
            {field: 'browser', title: '浏览器'},
            {field: 'os', title: '操作系统'},
            {field: 'logInTime', title: '登录时间'},
            {field: 'lastAccessTime', title: '最后访问时间'},
            {
                title: '操作', width: 50,
                formatter: function (value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn bg-maroon btn-xs " href="#" onclick="expireUserSession(\'' + row.sessionId + '\')"><i class="fa fa-sign-out"></i> 强退</a>');
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
        $.operate.submit(contextPath + 'monitor/online/user/expireUserSession', "post", "json", data);
    });
}

/**
 * 强退所有用户
 */
function expireUserSessions() {
    $.modal.confirm("确定强退所有用户吗？", function () {
        $.operate.submit(contextPath + 'monitor/online/user/expireUserSessions', "post", "json");
    });
}