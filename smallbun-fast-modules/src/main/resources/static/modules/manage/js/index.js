/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
/**
 * 获取菜单
 */
$(function () {
    //默认打开首页
    addTabs(({id: '10008', title: '首页', close: false, url: 'dashboard'}));
    App.fixIframeContent();
});

/**
 * 打开个人主页
 */
function profile() {
    addTabs(({id: 'profile', title: '个人中心', close: true, url: 'modules/manage/profile.html'}));
}

/**
 * 天气插件
 */
(function (T, h, i, n, k, P, a, g, e) {
    g = function () {
        P = h.createElement(i);
        a = h.getElementsByTagName(i)[0];
        P.src = k;
        P.charset = "utf-8";
        P.async = 1;
        a.parentNode.insertBefore(P, a)
    };
    T["ThinkPageWeatherWidgetObject"] = n;
    T[n] || (T[n] = function () {
        (T[n].q = T[n].q || []).push(arguments)
    });
    T[n].l = +new Date();
    if (T.attachEvent) {
        T.attachEvent("onload", g)
    } else {
        T.addEventListener("load", g, false)
    }
}(window, document, "script", "tpwidget", "//widget.seniverse.com/widget/chameleon.js"))
tpwidget("init", {
    "flavor": "slim",
    "location": "WWE0TGW4PX6N",
    "geolocation": "enabled",
    "language": "zh-chs",
    "unit": "c",
    "theme": "chameleon",
    "container": "tp-weather-widget",
    "bubble": "enabled",
    "alarmType": "badge",
    "color": "#FFFFFF",
    "uid": "U817819DBC",
    "hash": "28b2c7920e9afe7fe5807dfb74ccfe57"
});
tpwidget("show");