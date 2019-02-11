/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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