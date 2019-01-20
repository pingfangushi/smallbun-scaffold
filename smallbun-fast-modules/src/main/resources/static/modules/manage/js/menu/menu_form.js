$(function () {
    //默认字体颜色
    $("input[name='fontColor']").val('#ddd');
    $('#colorpicker').colorpicker()
});

/**
 * 图标选中回调
 * @param index
 * @param layero
 */
//@formatter:off
function iconConfirm(index, layero) {
    //得到iframe页的窗口对象，获取icon的值
    var icon = layero.find('iframe')[0].contentWindow.document.getElementById("icon").value;
    $("input[name='icon']").val(icon);
    try {$("input[name='icon']").valid();}catch (e) {}
    //给图标显示位置添加class,首先移除当前class样式
    $('#icon-show').removeClass();
    $('#icon-show').removeAttr('style');
    $('#icon-show').addClass(icon);
    //关闭弹框
    layer.close(index);

}
//@formatter:off
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

//默认为菜单
$("input[type=radio][name=menuType][value='1']").attr("checked", true);
//可见为可见
$("input[type=radio][name=visible][value='0']").attr("checked", true);
/**
 * 选择菜单类型事件
 */
$("input:radio[name='menuType']").on('ifChecked', function (event) {
    //按钮
    if ($(this).val() === '2') {
        $("input[name='fontColor']").parent().parent().parent().hide();
        $("input[name='icon']").parent().parent().parent().hide();
        $("input[name='target']").parent().parent().parent().hide();
        $("input[name='url']").parent().parent().parent().hide();
    }
    //目录或菜单
    if ($(this).val() === '0' || $(this).val() === '1') {
        $("input[name='fontColor']").parent().parent().parent().show();
        $("input[name='icon']").parent().parent().parent().show();
        $("input[name='target']").parent().parent().parent().show();
        $("input[name='url']").parent().parent().parent().show();
    }
});



