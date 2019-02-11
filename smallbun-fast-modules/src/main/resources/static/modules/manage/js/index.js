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
    //默认打开首页
    addTabs(({id: "_tab" + Math.random().toString(36).substring(2), title: '首页', close: false, url: 'dashboard'}));
});

/**
 * 打开个人主页
 */
function profile() {
    addTabs(({id: 'profile', title: '个人中心', close: true, url: 'modules/manage/profile.html'}));
}

/**
 * 首页控制台输出
 */
try {
    if (window.console && window.console.log) {
        console.log("终于等到探寻秘密的你！\n想体验这里面的挑战吗？\n想要成为这里的主人吗？\n加入SmallBun，拥抱开源世界，你，可以影响世界！\n");
        console.log("有意者请联系我个人QQ：%c2689170096（ 备注以“姓名-来自console”命名）", "color:red");
        console.log("简书：https://www.jianshu.com/u/d5950a6af4cd");
        console.log("个人博客： https://www.pingfangushi.com");
        console.log("%c欢迎关注，持续更新", "color:red")
    }
} catch (e) {
}