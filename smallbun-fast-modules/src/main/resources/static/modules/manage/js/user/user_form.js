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
 * 初始化
 */
$(function () {
    //如果orgId为空，那么设置为0，为主菜单
    if ($.common.isEmpty($('#orgId').val())) {
        $('#orgId').val(0);
    }
    /**
     * 加载部门树
     */
    $.pop_up_tree.init({
        obj: 'pop-up-org-tree',//显示input name 属性和id属性
        value: 'orgId', //隐藏value name 属性和id属性
        idKey: 'id',       //zTree idKey
        pIdKey: 'parentId',//zTree pIdKey
        rootPId: '0',      //zTree rootPId
        name: 'orgName',  //zTree name
        type: 'POST',      //ajax 请求
        expand: true,
        required: true,
        url: contextPath + 'org/list' //请求地址birthday
    });

    /**
     * 日期
     */
    laydate.render({
        elem: '#birthday', theme: '#1890ff', max: new Date().toLocaleDateString()
    });
});
/**
 * 验证
 */
$(".form-horizontal").validate({
    onkeyup: false,
    rules: {
        telephone: {
            isTel: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "user/unique",
                type: "post",
                dataType: 'JSON',
                data: {
                    id: function () {
                        return $("#id").val();
                    },
                    telephone: function () {
                        return $("#telephone").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        },
        idCard: {
            isIdCardNo: true
        },
        username: {
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
                    username: function () {
                        return $("#username").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        },
        jobNumber: {
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
                    username: function () {
                        return $("#jobNumber").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        },
        email: {
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
        username: {remote: "用户名已存在"},
        jobNumber: {remote: "工号已存在"},
        phone: {remote: "手机号已存在"},
        email: {remote: "邮箱已存在"},
        telephone: {remote: "电话已存在"}
    }
});

/**
 * 提交事件
 */
function doSubmit() {
    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        var data = $('.form-horizontal').serializeArray();
        $('input:checkbox[id="roleList"]:checked').each(function (i) {
            data.push({"name": "roleVOS[" + i + "].id", "value": $(this).val()});
        });
        $.operate.saveCurrentTabPage($('.form-horizontal').attr('action'), data);
    }
}
