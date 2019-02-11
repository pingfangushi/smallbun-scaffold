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
 * 更新用户头像
 */
function avatarSubmit() {
    $('#head-portrait-url').attr('src', $('.ng-scope.selected').find('img').attr('src'));
    //发送请求
    $.ajax({
        url: contextPath + 'user/updateHeadPortrait',
        type: "post",
        data: {
            id: $('#id').val(),
            url: $('.ng-scope.selected').find('img').attr('src')
        },
        dataType: "json",
        success: function (result) {
        }
    })
}

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
                    $.modal.alertSuccess("更新成功！")
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
        email: {
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
                    email: function () {
                        return $("#email").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        },
        phone: {
            isPhone: true,
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

/**
 * 监听ng-scope
 */
$(".ng-scope").click(function (event) {
    $('.ng-scope').removeClass('selected');
    $(this).addClass('selected');
    $('#browse-img').attr('src', $(this).find('img').attr('src'));
});