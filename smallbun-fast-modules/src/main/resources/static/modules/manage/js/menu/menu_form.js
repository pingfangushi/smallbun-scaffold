/**
 * 上级菜单弹出树
 */
function pop_upr_menu_tree(obj) {
    $.pop_up_tree.init({
        obj: obj,//显示input name 属性和id属性
        value: 'parentId', //隐藏value name 属性和id属性
        idKey: 'id',       //zTree idKey
        pIdKey: 'parentId',//zTree pIdKey
        rootPId: '0',      //zTree rootPId
        name: 'menuName',  //zTree name
        type: 'POST',      //ajax 请求
        url: contextPath + 'menu/list' //请求地址
    });
}

/**
 * 验证
 */
$(".form-horizontal").validate({
    rules: {
        menuName: {
            required: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "menu/unique",
                type: "post",
                dataType: 'JSON',
                data: {
                    id: function () {
                        return $("#id").val();
                    },
                    menuName: function () {
                        return $("#menuName").val();
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
        menuName: {
            required: "请输入菜单名称",
            remote: "菜单名称已存在"
        }
    }
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

