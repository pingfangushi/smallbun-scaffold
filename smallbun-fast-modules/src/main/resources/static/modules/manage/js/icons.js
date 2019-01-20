$(function () {
    $('#fa-icons').find("i").parent().attr('onselectstart', 'return false');
    $('#glyphicons').find("li").attr('onselectstart', 'return false');
    /**
     * fa-icons 选中
     */
    $('#fa-icons').find("i").parent().on("click", function () {
        remIconSel();
        //设置值
        $('#icon').val($(this).find('i').attr('class').trim());
        //给当前选中元素添加style样式
        $(this).css({"background-color": "#4F8EF7", "color": "#ffffff", "cursor": "pointer"});
    });
    /**
     * glyphicons 选中
     */
    $('#glyphicons').find("li").on("click", function () {
        remIconSel();
        //设置值
        $('#icon').val($(this).find('span').eq(0).attr('class').trim());
        //给当前选中元素添加style样式
        $(this).css({"background-color": "#4F8EF7", "color": "#ffffff", "cursor": "pointer"});
    });
    /**
     * icons 选中
     */
    $('.icons').find("li").on("click", function () {
        remIconSel();
        //设置值
        $('#icon').val($(this).attr('class').trim());
        //给当前选中元素添加style样式
        console.log($(this));
        $(this).css({"background-color": "#4F8EF7", "color": "#ffffff", "cursor": "pointer"});
    });

    /**
     * 移除图标选中样式
     */
    function remIconSel() {
        //移除style
        $('#fa-icons').find("i").parent().removeAttr("style");
        $('#glyphicons').find("li").removeAttr("style");
        $('.icons').find("li").removeAttr("style");
    }
});