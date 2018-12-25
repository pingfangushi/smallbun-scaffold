/*
 *
 * Copyright(c)[2018] [smallbun] www.smallbun.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

$(function () {
    /**
     * combogrid 加载字典类型
     */
    var comboGridId = '#combogrid';          //ComboGrid ID值
    var valueId = '#dictType';               //下拉框id值
    var idField = 'id';                      //ID字段
    var textField = 'typeName';              //显示的字段
    var url = basePath + 'dict/type/page';   //URL
    var pageSize = 10;                       //每页显示
    var pageList = [10, 20];                 //可设置显示列表
    var delay = 500;                         //延时查询
    var editable = true;                     //是否可编辑
    var method = 'POST';                     //请求方式
    var columns = [[{field: 'id', title: 'id', hidden: true},
        {field: 'typeName', title: '类型名称', width: $.combogrid.fixWidth(0.2)},
        {field: 'typeCode', title: '类型编码', width: $.combogrid.fixWidth(0.2)},
        {field: 'gmtModified', title: '修改时间', width: $.combogrid.fixWidth(0.2)}
    ]];
    $.combogrid.init({
        comboGridId: comboGridId,
        valueId: valueId,
        idField: idField,
        textField: textField,
        url: url,
        pageSize: pageSize,
        pageList: pageList,
        delay: delay,
        editable: editable,
        method: method,
        columns: columns
    });
});

/**
 * 验证
 */
$(".form-horizontal").validate({
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
            url: basePath + "dict/value/unique",
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








