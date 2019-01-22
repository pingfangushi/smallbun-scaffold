$(function () {
    //默认字体颜色
    $("input[name='fontColor']").val('#ddd');
    $('#color-picker').colorpicker();
    /**
     * 初始化时加载tree弹出数据
     */
    $.pop_up_tree.init({
        obj: 'pop-up-menu-tree',//显示input name 属性和id属性
        value: 'parentId', //隐藏value name 属性和id属性
        idKey: 'id',       //zTree idKey
        pIdKey: 'parentId',//zTree pIdKey
        rootPId: '0',      //zTree rootPId
        name: 'menuName',  //zTree name
        type: 'POST',      //ajax 请求
        url: contextPath + 'menu/list' //请求地址
    });
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
 * 提交事件
 */
function doSubmit() {
    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        //$.operate.save($('.form-horizontal').attr('action'), $('.form-horizontal').serializeArray());
        parent.closeCurrentTabPage(window)
    }
}
/**
 * 选择菜单类型事件
 */
$("input:radio[name='menuType']").on('ifChecked', function (event) {
    /**
     * 按钮
     */
    if ($(this).val() === '2') {
        $("input[name='fontColor']").parent().parent().parent().hide();
        $("input[name='icon']").parent().parent().parent().hide();
        $("input[name='target']").parent().parent().parent().hide();
        $("input[name='url']").parent().parent().parent().hide();

        //移除required
        $("input[name='icon']").removeAttr('required');
        $("input[name='target']").removeAttr('required');
        $("input[name='url']").removeAttr('required');

        //disabled 不提交
        $("input[name='fontColor']").attr('disabled');
        $("input[name='icon']").attr('disabled');
        $("input[name='target']").attr('disabled');
        $("input[name='url']").attr('disabled');
    }
    /**
     * 目录或菜单
     */
    if ($(this).val() === '0' || $(this).val() === '1') {
        $("input[name='fontColor']").parent().parent().parent().show();
        $("input[name='icon']").parent().parent().parent().show();
        $("input[name='target']").parent().parent().parent().show();
        $("input[name='url']").parent().parent().parent().show();

         //添加required
        $("input[name='icon']").attr('required');
        $("input[name='target']").attr('required');
        $("input[name='url']").attr('required');

        //disabled 移除
        $("input[name='fontColor']").removeAttr('disabled');
        $("input[name='icon']").removeAttr('disabled');
        $("input[name='target']").removeAttr('disabled');
        $("input[name='url']").removeAttr('disabled');
    }
});



