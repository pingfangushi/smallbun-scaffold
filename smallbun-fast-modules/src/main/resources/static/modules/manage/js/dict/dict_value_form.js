$(function () {
    var tag_data = [
        {id: 1, name: 'Chicago Bulls', desc: '芝加哥公牛'},
        {id: 2, name: 'Cleveland Cavaliers', desc: '克里夫兰骑士'},
        {id: 3, name: 'Detroit Pistons', desc: '底特律活塞'},
        {id: 4, name: 'Indiana Pacers', desc: '印第安纳步行者'}
    ];
    $('#dictType').selectPage({
        showField: 'desc',
        keyField: 'id',
        data: tag_data,
        pageSize: 5,
        //箭头按钮
        dropButton: true,
        //自定义行内容呈现
        formatItem: function (data) {
            return data.desc + '(' + data.name + ')';
        }
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








