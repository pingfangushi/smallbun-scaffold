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
        url: contextPath + 'org/list' //请求地址
    });

    /**
     * 日期
     */
    laydate.render({
        elem: '#birthday', theme: '#6284f3', max: new Date().toLocaleDateString()
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
