/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
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
    $("#captcha").attr("src", basePath + "captcha?" + Math.random());
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
            url: basePath + "key",
            type: "post",
            async: false,
            dataType: 'json',
            success: function (data) {
                encrypt.setPublicKey(data.result);
            }
        });
        //登录
        $.ajax({
            url: basePath + "login",
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