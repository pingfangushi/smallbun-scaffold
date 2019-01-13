$(function () {
    //在线用户数量
    onlineUserCount();
    //定时
    timingLoading();
});

/**
 * 在线用户数量(每隔5s)
 */
function onlineUserCount() {
    $.ajax({
        url: contextPath + "monitor/online/user/count",
        type: "post",
        async: true,
        dataType: 'json',
        success: function (data) {
            $('#online-user-count').text(data.result)
        }
    });
}

/**
 * 定时查询，每个请求都是五秒，将需要查询的放入setInterval()里面
 */
function timingLoading() {
    setInterval(function () {
        onlineUserCount();
    }, 5000);
}