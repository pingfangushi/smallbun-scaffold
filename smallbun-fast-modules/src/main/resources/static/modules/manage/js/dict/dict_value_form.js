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
    /**
     * 字典类型
     */
    $.ajax({
        url: contextPath + "dict/type/list",
        async: true,
        type: "post",
        dataType: 'JSON',
        success: function (data) {
            $('#dictType').selectPage({
                showField: 'typeName',
                keyField: 'id',
                data: data.result,
                pageSize: 10,
                eOpen: function () {
                    $('.sp_result_area').outerWidth($('#dictType').parent().width());
                },
                //自定义行内容呈现
                formatItem: function (data) {
                    return data.typeName + '(' + data.typeCode + ')';
                },
                //选中处理
                eSelect: function (data) {
                    $('#dictType').valid();
                },
                //单选模式清除事件
                eClear: function () {
                }
            });
            //移除class
            $('#dictType').addClass('required');
        }
    });
});

/**
 * 验证
 */
$(".form-horizontal").validate({
    onkeyup: false,
    rules: {
        dictValue: {
            required: true,
            dictValue: true
        }
    },
    messages: {
        dictValue: {
            required: "请输入字典值",
            remote: "字典值已存在"
        }
    }
});
/**
 * 自定义验证方法,验证字典值
 */
jQuery.validator.addMethod("dictValue", function (value, element) {
    var flag = true;
    var dictType = $("#dictType").val();
    var id = $("#id").val();
    //如果选择了字典类型，进行验证字典类型和字典值是否重复
    if ($.common.isNotEmpty(dictType)) {
        $.ajax({
            url: contextPath + "dict/value/unique",
            async: false, //同步方法，如果用异步的话，flag永远为false
            type: "POST",
            dataType: 'JSON',
            data: {
                id: id,
                dictValue: value,
                dictType: dictType
            },
            success: function (data) {
                flag = data.result;
            }
        });
    }
    return flag;
}, "该类型下字典值已存在");

/**
 * 提交事件
 */
function doSubmit() {
    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        $.operate.save($('.form-horizontal').attr('action'), $('.form-horizontal').serializeArray());
    }
}








