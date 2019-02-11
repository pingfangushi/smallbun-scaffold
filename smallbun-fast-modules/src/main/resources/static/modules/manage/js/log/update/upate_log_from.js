/**
 * 日期
 */
laydate.render({
    elem: '#releaseDate',
    theme: '#6284f3',
    max: new Date().toLocaleDateString(),
    value: new Date()
});

/**
 * 提交事件
 */
function doSubmit() {
    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        var data = $('.form-horizontal').serializeArray();
        $.operate.save($('.form-horizontal').attr('action'), data);
    }
}