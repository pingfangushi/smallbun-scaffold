/**
 * 更改密码
 **/
function changPassword() {
    // 手动触发校验代码
    if ($('#changPasswordForm').valid()) {
        $.ajax({
            url: $('#changPasswordForm').attr('action'),
            type: "post",
            data: $('#changPasswordForm').serializeArray(),
            dataType: "json",
            success: function (result) {
                if (result.status === '900001') {
                    $.modal.alertError(result.msg)
                }
                if (result.status === web_status.SUCCESS) {
                    layer.confirm("修改成功, 请重新登录！", {
                            icon: $.modal.icon(modal_status.SUCCESS),
                            title: "系统提示",
                            btn: ['确认'],
                            btnclass: ['btn btn-primary']
                        }, function (index) {
                            layer.close(index);
                            document.location.href = contextPath + 'logout';
                        }
                    );

                }
            }
        })
    }
}

/**
 * 修改用户密码
 **/
function updateUserInfo() {
    // 手动触发校验代码
    if ($('#updateUserInfoForm').valid()) {
        $.ajax({
            url: $('#updateUserInfoForm').attr('action'),
            type: "post",
            data: $('#updateUserInfoForm').serializeArray(),
            dataType: "json",
            success: function (result) {
                if (result.status === web_status.SUCCESS) {

                }
            }
        })
    }
}

/**
 * 验证密码
 */
$("#changPasswordForm").validate({
    onkeyup: false,
    rules: {
        oldPassword: {
            required: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "user/verifyOldPassword",
                type: "post",
                dataType: 'JSON',
                data: {
                    oldPassword: function () {
                        return $("#oldPassword").val();
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
        oldPassword: {
            remote: "原密码不正确"
        }
    }
});
/**
 * 验证基本信息
 */
$("#updateUserInfoForm").validate({
    onkeyup: false,
    rules: {
        phone: {
            isPhone: true
        },
        oldPassword: {
            required: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "user/unique",
                type: "post",
                dataType: 'JSON',
                data: {
                    id: function () {
                        return $("#id").val();
                    },
                    phone: function () {
                        return $("#phone").val();
                    },
                    email: function () {
                        return $("#email").val();
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
        phone: {
            remote: "手机号已存在"
        },
        email: {
            remote: "邮箱已存在"
        }
    }
});