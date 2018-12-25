if (window.attachEvent) {
    window.attachEvent("onload", function () {
        document.open();
        document.clear();
        document.close();
        var el = document.createElement("div"), elStyle = el.style, docBody = document.getElementsByTagName("body")[0];
        el.innerHTML = "<div>尊敬的用户,为了您的安全管理控制台不支持IE8浏览器下的可用性,建议您尽快" + '<a href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie" target=_blank>升级浏览器</a>' + '，或者使用<a href="http://www.google.com/intl/zh-CN/chrome/" target=_blank>Chrome</a>' + '<a href="http://www.firefox.com.cn/download/" target=_blank>Firefox</a>' + "</div>";
        elStyle.width = "100%";
        elStyle.color = "#2e9900";
        elStyle.fontSize = "12px";
        elStyle.backgroundColor = "#F2FFEA";
        elStyle.padding = "12px 11px";
        docBody.insertBefore(el, docBody.firstChild)
    })
}