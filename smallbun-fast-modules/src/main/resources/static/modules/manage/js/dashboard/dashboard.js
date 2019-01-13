$(function () {
    //在线用户数量
    onlineUserCount();
});

/**
 * 在线用户数量(每隔5s)
 */
function onlineUserCount() {
    setInterval(function () {
        $.ajax({
            url: contextPath + "monitor/online/user/count",
            type: "post",
            async: true,
            dataType: 'json',
            success: function (data) {
                $('#online-user-count').text(data.result)
            }
        });
    }, 5000);
}