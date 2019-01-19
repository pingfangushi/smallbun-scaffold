$(function () {
    $('#fa-icons').find("i").parent().attr('onselectstart', 'return false');
    $('#glyphicons').find("li").attr('onselectstart', 'return false');
    /**
     * fa-icons 选中
     */
    $('#fa-icons').find("i").parent().on("click", function () {
        //移除style
        $('#glyphicons').find("li").removeAttr("style");
        $('#fa-icons').find("i").parent().removeAttr("style");
        //设置值
        $('#icon').val($(this).find('i').attr('class').trim());
        //给当前选中元素添加style样式
        $(this).css({"background-color": "#479bff", "color": "#ffffff", "cursor": "pointer"});
    });
    /**
     * glyphicons 选中
     */
    $('#glyphicons').find("li").on("click", function () {
        //移除style
        $('#fa-icons').find("i").parent().removeAttr("style");
        $('#glyphicons').find("li").removeAttr("style");
        //设置值
        $('#icon').val($(this).find('span').eq(0).attr('class').trim());
        //给当前选中元素添加style样式
        $(this).css({"background-color": "#479bff", "color": "#ffffff", "cursor": "pointer"});
    });
});