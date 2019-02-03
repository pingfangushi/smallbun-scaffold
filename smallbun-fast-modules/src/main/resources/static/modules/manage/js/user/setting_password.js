/**
 * 提交事件
 */
function doSubmit() {
    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        $.modal.loading("正在处理中，请稍后...");
        var config = {
            url: $('.form-horizontal').attr('action'),
            type: "post",
            dataType: "json",
            data: $('.form-horizontal').serializeArray(),
            success: function (result) {
                if (result.status === web_status.SUCCESS) {
                    $.modal.closeLoading();
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }
            }
        };
        $.ajax(config)
    }
}