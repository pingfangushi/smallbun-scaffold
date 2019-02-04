/*$(function () {
    //默认打开首页
    addTabs(({id: '10008', title: '首页', close: false, url: 'dashboard'}));
    App.fixIframeContent();
});*/
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