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

var ERROR = 'error';
var CAPTCHA = 'captcha';
var OK = 'ok';


/**
 * 刷新验证码
 */
function reloadCode() {
    //清楚样式
    $(".captcha").removeClass('has-success');
    //清空输入框
    $("#KAPTCHA_SESSION_KEY").val(null);
    //刷新校验码
    $("#captcha").attr("src", contextPath + "captcha?" + Math.random());
}

/*自定义表单验证，验证成功后在进行密码加密提交*/
$(document).ready(function () {
    $("#form").bootstrapValidator({
        fields: {
            username: {
                feedbackIcons: false,
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    }
                }
            },
            password: {
                feedbackIcons: false,
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            },
            KAPTCHA_SESSION_KEY: {
                feedbackIcons: false,
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        //禁止提交表单
        e.preventDefault();
        //获取公钥
        var encrypt = new JSEncrypt();
        $.ajax({
            url: contextPath + "key",
            type: "post",
            async: false,
            dataType: 'json',
            success: function (data) {
                encrypt.setPublicKey(data.result);
            }
        });
        //登录
        $.ajax({
            url: contextPath + "login",
            type: "post",
            async: false,
            dataType: 'json',
            data: {
                "password": encrypt.encrypt($('#password').val()),
                "username": $('#username').val(),
                "KAPTCHA_SESSION_KEY": $('#KAPTCHA_SESSION_KEY').val(),
            },
            success: function (data) {
                if (OK === data.status) {
                    location.href = '/index';
                }
                if (CAPTCHA === data.status) {
                    $("#msg").empty().append("验证码错误");
                    //更新验证码
                    reloadCode();
                }
                if (ERROR === data.status) {
                    $("#msg").empty().append(data.msg);
                    reloadCode();
                    $(".username").removeClass('has-success');
                    $(".password").removeClass('has-success');
                    $("#password").val(null);
                    $("#username").val(null);
                }
            }
        });
    })
    ;
});