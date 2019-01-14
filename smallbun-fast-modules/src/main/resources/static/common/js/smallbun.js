/**
 * 禁用右键
 * @type {Function}
 */
document.oncontextmenu = new Function("return false");
/**
 *
 * @type {document.onkeypress}
 */
document.onkeydown = document.onkeyup = document.onkeypress = function (event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    //如果是F12
    if (e && e.keyCode === 123) {
        e.returnValue = false;
        return (false);
    }
};

/**
 * AJAX 异常统一处理
 */
$(function () {
    $(document).ajaxError(
        /**
         * 所有Ajax请求异常的统一处理函数，处理
         * @param event
         * @param xhr
         * @param options
         * @param exc
         */
        function (event, xhr, options, exc) {
            if (xhr.status === 'undefined') {
                return;
            }
            switch (xhr.status) {
                case 403:
                    // 未授权异常
                    $.modal.alert("您没有访问权限！", modal_status.FAIL);
                    break;
                case 404:
                    $.modal.alert("您访问的资源不存在！", modal_status.FAIL);
                    break;
                case 500:
                    $.modal.alert("服务器开小差了~~,请您稍后重试！", modal_status.FAIL);
                    break;
            }
        }
    );
    /**
     * 全局的ajax访问，处理ajax清求时session超时
     */
    $.ajaxSetup({
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        complete: function (XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.responseText != null && XMLHttpRequest.responseText.indexOf("LOGIN-PAGE") > 0) {
                layer.confirm("您的登录已超时, 请点确定后重新登录！", {
                        icon: $.modal.icon(modal_status.FAIL),
                        title: "系统提示",
                        btn: ['确认'],
                        btnclass: ['btn btn-primary']
                    }, function (index) {
                        layer.close(index);
                        document.location.href = contextPath + 'login';
                    }
                );
            }
        }
    });
});