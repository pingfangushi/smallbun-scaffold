$(document).ready(function () {
    /**
     * 展开隐藏搜索框
     */
    $(".query-a").click(function () {
        var query = $('.query-a');
        var table = $('.table');
        var form = $('.search-form');
        if (form.is(":hidden")) {
            form.slideToggle("slow", function () {
                $('.bootstrap-table').css('border-top', '1px solid #ddd');
                //-1 为bootstrap-table 上边框高度
                table.bootstrapTable('resetView', {height: $('.ibox-content-table').height() - $('.search-form').outerHeight(true) - 1});
                query.text('');
                query.append('<li class=\'fa fa-filter\'/></li> 隐藏');
                query.css('background-color', '#3276b1');
                query.css('border-color', '#285e8e');
            });
        } else {
            form.slideToggle("slow", function () {
                $('.bootstrap-table').css('border-top', '0 solid #ddd');
                //-1 为bootstrap-table 上边框高度
                table.bootstrapTable('resetView', {height: $('.ibox-content-table').height() - 1});
                query.text('');
                query.removeAttr("style");
                query.append('<li class=\'fa fa-filter\'></li> 查询');
            });
        }
    });
});

/**
 * 表格移动事件
 */
$(".ibox-content-table").resize(function (e) {
    if (typeof ($('.ibox-content-tree').css("marginTop")) !== 'undefined') {
        $('.ibox-content-tree').height($('.ibox').height() - $('.ibox-title').outerHeight(true) - $('.ibox-content-tree').css("marginTop").replace('px', ''));
    }
    //ibox-content-table 高度等于ibox高度减去title高度获得
    $('.ibox-content-table').outerHeight($('.ibox').height() - $('.ibox-title').outerHeight(true));
    //-1 为bootstrap-table 上边框高度
    $('.table').bootstrapTable('resetView', {height: $('.ibox-content-table').height() - 1});
    $('.table').bootstrapTable('resetView');
});
/**
 * 左树
 */
$(".ibox-content-tree").resize(function (e) {
    if (typeof ($('.ibox-content-tree').css("marginTop")) !== 'undefined') {
        $('.ibox-content-tree').height($('.ibox').height() - $('.ibox-title').outerHeight(true) - $('.ibox-content-tree').css("marginTop").replace('px', ''));
    }
});
/**
 * 树形表格移动事件
 */
$(".ibox-content-tree-table").resize(function (e) {
    //树表格高度等于ibox高度减去title高度获得
    $('.ibox-content-tree-table').outerHeight($('.ibox').height() - $('.ibox-title').outerHeight(true));
    //-1 为bootstrap-table 上边框高度
    $('.table').bootstrapTable('resetView', {height: $('.ibox-content-tree-table').height() - 1});
});