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
    //@formatter:off
    var options = {
        url: contextPath + 'log/operate/page',
        batRemoveUrl: contextPath + "log/operate/removeByIds",
        exportUrl: contextPath + "log/operate/export",
        sortName: "operateTime",
        sortOrder: "desc",
        modalName: "操作日志",
        search: false,
        showExport: false,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        columns: [{checkbox: true},
            {field: 'id', visible: false},
            {field: 'title', title: '标题', sortable: true},
            {field: 'actionName', title: '功能'},
            {field: 'user.username', title: '用户'},
            {field: 'org.orgName', title: '部门'},
            {field: 'operateIp', title: 'IP地址'},
            {field: 'operateLocation', title: '地点'},
            {field: 'operateUrl', title: '请求URL'},
            {field: 'method', title: '方法名称'},
            {field: 'operateParam', title: '请求参数',visible: false},
            {field: 'channelName', title: '来源渠道'},
            {field: 'operateStatus', title: '状态', sortable: true},
            {field: 'operateTime', title: '操作时间', sortable: true},
            {field: 'error_msg', title: '错误',visible: false}]
    };
    $.table.init(options);
    //@formatter:off
});
