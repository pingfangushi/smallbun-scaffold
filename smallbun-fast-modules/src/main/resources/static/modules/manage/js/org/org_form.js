/**
 * 初始化
 */
$(function () {
    //加载部门树
    $.pop_up_tree.init({
        obj: 'pop-up-org-tree',//显示input name 属性和id属性
        value: 'parentId', //隐藏value name 属性和id属性
        idKey: 'id',       //zTree idKey
        pIdKey: 'parentId',//zTree pIdKey
        rootPId: '0',      //zTree rootPId
        name: 'orgName',  //zTree name
        type: 'POST',      //ajax 请求
        expand: true,
        url: contextPath + 'org/list' //请求地址
    });
});

/**
 * 提交事件
 */
function doSubmit() {
    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        $.operate.save($('.form-horizontal').attr('action'), $('.form-horizontal').serializeArray());
    }
}

/**
 * 验证
 */
$(".form-horizontal").validate({
    onkeyup: false,
    rules: {
        telephone: {
            isTel: true
        },
        orgCode: {
            required: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "org/unique",
                type: "post",
                dataType: 'JSON',
                data: {
                    id: function () {
                        return $("#id").val();
                    },
                    orgCode: function () {
                        return $("#orgCode").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        },
        orgName: {
            required: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "org/unique",
                type: "post",
                dataType: 'JSON',
                data: {
                    id: function () {
                        return $("#id").val();
                    },
                    orgName: function () {
                        return $("#orgName").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        },
        zipCode: {
            isZipCode: true
        },
        fax: {
            fax: true
        },
        principal: {
            realName: true
        }
    },
    messages: {
        orgCode: {
            remote: "机构编码已存在"
        },
        orgName: {
            remote: "机构名称已存在"
        }
    }
});
