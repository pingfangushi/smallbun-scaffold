$(function () {
    //默认字体颜色
    if ($.common.isEmpty($("input[name='fontColor']").val())) {
        $("input[name='fontColor']").val('#ddd');
    }
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
        $.operate.saveCurrentTabPage($('.form-horizontal').attr('action'), $('.form-horizontal').serializeArray());
    }
}


/**
 * 选择菜单类型事件
 */
$("input:radio[name='menuType']").on('ifChecked', function (event) {
    /**
     * 按钮
     */
    if ($(this).val() === '2') {btnExtracted ();}
    /**
     * 目录或菜单
     */
    if ($(this).val() === '0' || $(this).val() === '1') {menuExtracted()}
});


$(function() {
   var menuType= $('input:radio[name="menuType"]:checked');
   if (menuType.val()==='2') {btnExtracted()}
   if (menuType.val()==='1' || menuType.val()==='0'  ) {menuExtracted()}
});
/**
 * 如果选中的是按钮
 */
function btnExtracted (){
     $("input[name='fontColor']").parent().parent().parent().hide();
     $("input[name='icon']").parent().parent().parent().hide();
     $("input[name='url']").parent().parent().parent().hide();
     $("input[name='target']").parent().parent().parent().hide();
     $("input[name='permission']").parent().parent().parent().show();

     //添加required
     $("input[name='icon']").attr('readonly', "true");
     $("input[name='url']").attr('readonly', "true");
     $("input[name='fontColor']").attr('readonly', "true");
     $("input[name='target']").attr('readonly', "true");


     //设置为必选
     $("#pop-up-menu-tree").attr('required', "true");
}

/**
 * 如果选中的是菜单
 */
function menuExtracted (){
     $("input[name='fontColor']").parent().parent().parent().show();
     $("input[name='icon']").parent().parent().parent().show();
     $("input[name='url']").parent().parent().parent().show();
     $("input[name='target']").parent().parent().parent().show();
     $("input[name='permission']").parent().parent().parent().hide();

     //移除required
     $("input[name='icon']").removeAttr('readonly');
     $("input[name='url']").removeAttr('readonly');
     $("input[name='fontColor']").removeAttr('readonly');
     $("input[name='target']").removeAttr('readonly');


     //不需要为必选
     $("#pop-up-menu-tree").removeAttr('required');
     $("#pop-up-menu-tree").valid();
}