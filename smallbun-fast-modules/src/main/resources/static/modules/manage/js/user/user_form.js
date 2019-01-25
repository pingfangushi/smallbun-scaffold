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
 * 提交事件
 */
function doSubmit() {
    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        $.operate.saveCurrentTabPage($('.form-horizontal').attr('action'), $('.form-horizontal').serializeArray());
    }
}
