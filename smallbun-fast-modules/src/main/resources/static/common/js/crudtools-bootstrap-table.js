/*
 *
 *  Copyright(c)[2018] [smallbun] www.smallbun.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

/**
 * 通用方法封装处理
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/9/7
 */
(function ($) {
    var $table = $('.table');
    $.extend({
        // 表格封装处理
        table: {
            _option: {},
            _params: {},
            // 初始化表格
            init: function (options) {
                $.table._option = options;
                $.table._params = $.common.isEmpty(options.queryParams) ? $.table.queryParams : options.queryParams;
                var _sortOrder = $.common.isEmpty(options.sortOrder) ? "asc" : options.sortOrder;
                var _sortName = $.common.isEmpty(options.sortName) ? "" : options.sortName;
                $table.bootstrapTable({
                    width: 'auto',
                    height: 'auto',
                    url: options.url,                                   // 请求后台的URL（*）
                    contentType: "application/x-www-form-urlencoded",   // 编码类型
                    method: 'post',                                     // 请求方式（*）
                    cache: false,                                       // 是否使用缓存
                    sortable: true,                                     // 是否启用排序
                    sortStable: true,                                   // 设置为 true 将获得稳定的排序
                    sortName: _sortName,                                // 排序列名称
                    sortOrder: _sortOrder,                              // 排序方式  asc 或者 desc
                    pagination: $.common.visible(options.pagination),   // 是否显示分页（*）
                    clickToSelect: true,                                //是否启用点击选中行
                    pageNumber: 1,                                      // 初始化加载第一页，默认第一页
                    pageSize: 20,                                       // 每页的记录行数（*）
                    pageList: [20, 30, 40],                             // 可供选择的每页的行数（*）
                    iconSize: 'sm',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
                    sidePagination: "server",                           // 启用服务端分页
                    paginationLoop: false,                              //不启用分页条无限循环的功能。
                    cardView: true,                                     //手机端适应
                    toolbar: "#toolbar",
                    search: $.common.visible(options.search),           // 是否显示搜索框功能
                    showRefresh: $.common.visible(options.showRefresh), // 是否显示刷新按钮
                    showColumns: $.common.visible(options.showColumns), // 是否显示隐藏某列下拉框
                    showToggle: $.common.visible(options.showToggle),   // 是否显示详细视图和列表视图的切换按钮
                    showExport: $.common.visible(options.showExport),   // 是否支持导出文件
                    queryParams: $.table._params,                       // 传递参数（*）
                    columns: options.columns,                           // 显示列信息（*）
                    responseHandler: $.table.responseHandler            // 回调函数
                });
            },
            // 查询条件
            queryParams: function (params) {
                return {
                    // 传递参数查询参数
                    size: params.limit, //每页显示条数
                    current: params.offset / params.limit + 1, //当前页数
                    searchValue: params.search,
                    orderByColumn: params.sort,
                    isAsc: params.order
                };
            },
            // 请求获取数据后处理回调函数
            responseHandler: function (res) {
                //如果请求成功
                if (res.status === '200') {
                    return {rows: res.page.records, total: res.page.total};
                }
                //否则
                else {
                    $.modal.alert(res.status + ":" + res.msg, modal_status.FAIL);
                    return {rows: [], total: 0};
                }
            },
            // 搜索
            search: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                var search = {};
                $.each($("#" + currentId).serializeArray(), function (i, field) {
                    search[field.name] = field.value;
                });
                $table.bootstrapTable('refresh', {query: search});
            },
            // 导出
            exportExcel: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                var index = $.modal.loading("正在导出数据，请稍后...");
                $.post($.table._option.exportUrl, $("#" + currentId).serializeArray(), function (result) {
                    if (result.status === web_status.SUCCESS) {
                        window.location.href = basePath + "fast/download?fileName=" + result.msg + "&delete=" + true;
                    } else {
                        $.modal.alertError(result.msg);
                    }
                    $.modal.closeLoading(index);
                });
            },
            // 导入
            importExcel: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                var index = $.modal.loading("正在导出数据，请稍后...");
                $.post($.table._option.exportUrl, $("#" + currentId).serializeArray(), function (result) {
                    if (result.status === web_status.SUCCESS) {
                        window.location.href = basePath + "fast/download?fileName=" + result.msg + "&delete=" + true;
                    } else {
                        $.modal.alertError(result.msg);
                    }
                    $.modal.closeLoading(index);
                });
            },
            // 刷新
            refresh: function () {
                $table.bootstrapTable('refresh', {
                    url: $.table._option.url,
                    silent: true
                });
            },
            // 查询选中列值
            selectColumns: function (column) {
                return $.map($table.bootstrapTable('getSelections'), function (row) {
                    return row[column];
                });
            },
            // 查询选中首列值
            selectFirstColumns: function () {
                return $.map($table.bootstrapTable('getSelections'), function (row) {
                    return row[$.table._option.columns[1].field];
                });
            },
            // 回显数据字典
            selectDictLabel: function (_datas, _value) {
                var actions = [];
                $.each(_datas, function (index, dict) {
                    if (dict.dictValue === _value) {
                        actions.push("<span class='badge badge-" + dict.listClass + "'>" + dict.dictLabel + "</span>");
                        return false;
                    }
                });
                return actions.join('');
            },
            //查看
            view: function (value, row, index) {
                var actions = [];
                actions.push('<a  href="#" onclick="$.operate.view(\'' + row.id + '\',\'\')">' + value + '</a> ');
                return actions.join('');
            }
        },
        // 表格树封装处理
        treeTable: {
            _option: {},
            _treeTable: {},
            // 初始化表格
            init: function (options) {
                $.table._option = options;
                $.treeTable._treeTable = $table.bootstrapTable({
                    url: options.url,
                    method: 'post',                                     // 请求方式（*）
                    singleSelect: true,
                    striped: true,
                    clickToSelect: true,//是否启用点击选中行
                    toolbarAlign: 'left',//工具栏对齐方式
                    buttonsAlign: 'left',//按钮对齐方式
                    width: 'auto',
                    height: '760',
                    sidePagination: 'server',
                    idField: options.idField,
                    rootParentId: options.rootParentId,
                    parentIdField: options.parentIdField,
                    columns: options.columns,
                    treeShowField: options.treeShowField,
                    responseHandler: $.treeTable.responseHandler,           // 回调函数
                    onLoadSuccess: function (res) {
                        $table.treegrid({
                            initialState: 'collapsed', /*不展开*/
                            treeColumn: 1,
                            expanderExpandedClass: 'treegrid-expander glyphicon glyphicon-chevron-down',
                            expanderCollapsedClass: 'treegrid-expander glyphicon glyphicon-chevron-right',
                            onChange: function () {
                                $table.bootstrapTable('resetWidth');
                            }
                        });
                    }
                });
            },
            // 条件查询
            search: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                var params = {};
                $.each($("#" + currentId).serializeArray(), function (i, field) {
                    params[field.name] = field.value;
                });
                $.treeTable._treeTable.bootstrapTable('refresh', params);
            },
            // 请求获取数据后处理回调函数
            responseHandler: function (res) {
                //如果不是200代表请求失败或者异常
                if (res.status === '900001') {
                    $.modal.alert(res.status + ":" + res.msg, modal_status.FAIL);
                    return [];
                } else {
                    return res.result;
                }
            },
            // 刷新
            refresh: function () {
                $.treeTable._treeTable.bootstrapTable('refresh');
            }
        },
        // 表单封装处理
        form: {
            // 表单重置
            reset: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                $("#" + currentId)[0].reset();
            },
            // 获取选中复选框项
            selectCheckeds: function (name) {
                var checkeds = "";
                $('input:checkbox[name="' + name + '"]:checked').each(function (i) {
                    if ('0' === i) {
                        checkeds = $(this).val();
                    } else {
                        checkeds += ("," + $(this).val());
                    }
                });
                return checkeds;
            },
            // 获取选中下拉框项
            selectSelects: function (name) {
                var selects = "";
                $('#' + name + ' option:selected').each(function (i) {
                    if ('0' === i) {
                        selects = $(this).val();
                    } else {
                        selects += ("," + $(this).val());
                    }
                });
                return selects;
            }
        },
        // 弹出层封装处理
        modal: {
            // 显示图标
            icon: function (type) {
                var icon = "";
                if (type === modal_status.WARNING) {
                    icon = 0;
                } else if (type === modal_status.SUCCESS) {
                    icon = 1;
                } else if (type === modal_status.FAIL) {
                    icon = 2;
                } else {
                    icon = 3;
                }
                return icon;
            },
            // 消息提示
            msg: function (content, type) {
                if (type !== undefined) {
                    layer.msg(content, {icon: $.modal.icon(type), time: 1000, shift: 5});
                } else {
                    layer.msg(content);
                }
            },
            // 错误消息
            msgError: function (content) {
                $.modal.msg(content, modal_status.FAIL);
            },
            // 成功消息
            msgSuccess: function (content) {
                $.modal.msg(content, modal_status.SUCCESS);
            },
            // 警告消息
            msgWarning: function (content) {
                $.modal.msg(content, modal_status.WARNING);
            },
            // 弹出提示
            alert: function (content, type) {
                layer.alert(content, {
                    icon: $.modal.icon(type),
                    title: "系统提示",
                    btn: ['确认'],
                    btnclass: ['btn btn-primary'],
                });
            },
            // 消息提示并刷新父窗体
            msgReload: function (msg, type) {
                layer.msg(msg, {
                        icon: $.modal.icon(type),
                        time: 500,
                        shade: [0.1, '#8F8F8F']
                    },
                    function () {
                        $.modal.reload();
                    });
            },
            // 错误提示
            alertError: function (content) {
                $.modal.alert(content, modal_status.FAIL);
            },
            // 成功提示
            alertSuccess: function (content) {
                $.modal.alert(content, modal_status.SUCCESS);
            },
            // 警告提示
            alertWarning: function (content) {
                $.modal.alert(content, modal_status.WARNING);
            },
            // 关闭窗体
            close: function () {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            },
            // 确认窗体
            confirm: function (content, callBack) {
                layer.confirm(content, {
                    icon: 3,
                    title: "系统提示",
                    btn: ['确认', '取消'],
                    btnclass: ['btn btn-primary', 'btn btn-danger'],
                }, function (index) {
                    layer.close(index);
                    callBack(true);
                });
            },
            // 弹出层指定宽度
            open: function (title, url, width, height) {
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                if ($.common.isEmpty(title)) {
                    title = false;
                }
                if ($.common.isEmpty(url)) {
                    url = "404.html";
                }
                if ($.common.isEmpty(width)) {
                    width = 1000;
                }
                if ($.common.isEmpty(height)) {
                    height = ($(window).height() - 45);
                }
                layer.open({
                    type: 2,
                    area: [width + 'px', height + 'px'],
                    fix: false,
                    //不固定
                    maxmin: true,
                    shade: 0.3,
                    title: title,
                    content: url,
                    btn: ['确定', '关闭'],
                    yes: function (index, layero) {
                        var body = top.layer.getChildFrame('body', index);
                        var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        //文档地址
                        //调用提交方法
                        iframeWin.contentWindow.doSubmit();
                    },
                    cancel: function (index) {

                    }
                });
            },
            // 弹出层指定宽度
            view: function (title, url, width, height) {
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                if ($.common.isEmpty(title)) {
                    title = false;
                }
                if ($.common.isEmpty(url)) {
                    url = "404.html";
                }
                if ($.common.isEmpty(width)) {
                    width = 1000;
                }
                if ($.common.isEmpty(height)) {
                    height = ($(window).height() - 50);
                }
                layer.open({
                    type: 2,
                    area: [width + 'px', height + 'px'],
                    fix: false,
                    //不固定
                    maxmin: true,
                    shade: 0.3,
                    title: title,
                    content: url,
                    btn: ['关闭'],
                    cancel: function (index) {

                    }
                });
            },
            // 弹出层全屏
            openFull: function (title, url, width, height) {
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                if ($.common.isEmpty(title)) {
                    title = false;
                }
                if ($.common.isEmpty(url)) {
                    url = "404.html";
                }
                if ($.common.isEmpty(width)) {
                    width = 800;
                }
                if ($.common.isEmpty(height)) {
                    height = ($(window).height() - 50);
                }
                var index = layer.open({
                    type: 2,
                    area: [width + 'px', height + 'px'],
                    fix: false,
                    //不固定
                    maxmin: true,
                    shade: 0.3,
                    title: title,
                    content: url
                });
                layer.full(index);
            },
            // 打开遮罩层
            loading: function (message) {
                return layer.msg(message, {
                    icon: 16,
                    shade: 0.01,
                    time: 0 //不自动关闭
                });
                /* $.blockUI({message: '<div class="loaderbox"><div class="loading-activity"></div> ' + message + '</div>'});*/
            },
            // 关闭遮罩层
            closeLoading: function (index) {
                setTimeout(function () {
                    layer.close(index);
                }, 50);
            },
            // 重新加载
            reload: function () {
                parent.location.reload();
            }
        },
        // 操作封装处理
        operate: {
            // 提交数据
            submit: function (url, type, dataType, data) {
                var index = $.modal.loading("正在处理中，请稍后...");
                var config = {
                    url: url,
                    type: type,
                    dataType: dataType,
                    data: data,
                    success: function (result) {
                        $.operate.ajaxSuccess(result, index);
                    }
                };
                $.ajax(config)
            },
            // post请求传输
            post: function (url, data) {
                $.operate.submit(url, "post", "json", data);
            },
            // 删除信息
            remove: function (id) {
                $.modal.confirm("确定删除该条" + $.table._option.modalName + "信息吗？", function () {
                    var url = $.table._option.removeUrl;
                    var data = {"id": id};
                    $.operate.submit(url, "post", "json", data);
                });
            },
            // 批量删除信息
            batRemove: function () {
                var rows = $.common.isEmpty($.table._option.id) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._option.id);
                if (rows.length === 0) {
                    $.modal.alertWarning("请至少选择一条记录");
                    return;
                }
                $.modal.confirm("确认要删除选中的" + rows.length + "条数据吗?", function () {
                    var url = $.table._option.batRemoveUrl;
                    var data = {"ids": rows.join()};
                    $.operate.submit(url, "post", "json", data);
                });
            },
            // 添加信息
            add: function (id) {
                var url = $.common.isEmpty(id) ? $.table._option.createUrl : $.table._option.createUrl.replace("{id}", id);
                $.modal.open("添加" + $.table._option.modalName, url);
            },
            // 修改信息
            edit: function (id) {
                var url = "/404.html";
                if ($.common.isNotEmpty(id)) {
                    url = $.table._option.updateUrl.replace("{id}", "?id=" + id);
                } else {
                    id = $.common.isEmpty($.table._option.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._option.uniqueId);
                    if (id.length === 0) {
                        $.modal.alertWarning("请至少选择一条记录");
                        return;
                    } else if (id.length > 1) {
                        $.modal.alertWarning("只能选择一条数据");
                        return;
                    } else {
                        url = $.table._option.updateUrl.replace("{id}", "?id=" + id);
                    }
                }
                $.modal.open("修改" + $.table._option.modalName, url);
            },
            view: function (id, url) {
                if ($.common.isNotEmpty(id)) {
                    if ($.common.isEmpty(url)) {
                        url = $.table._option.updateUrl.replace("{id}", "?id=" + id);
                    } else {
                        url = url + "?id=" + id;
                    }
                } else {
                    id = $.common.isEmpty($.table._option.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._option.uniqueId);
                    if (id.length === 0) {
                        $.modal.alertWarning("请至少选择一条记录");
                        return;
                    } else if (id.length > 1) {
                        $.modal.alertWarning("只能选择一条数据");
                        return;
                    } else {
                        if ($.common.isEmpty(url)) {
                            url = $.table._option.updateUrl.replace("{id}", "?id=" + id);
                        } else {
                            url = url + "?id=" + id;
                        }
                    }
                }
                $.modal.view("查看" + $.table._option.modalName, url);
            },
            // 添加信息 全屏
            addFull: function (id) {
                var url = $.common.isEmpty(id) ? $.table._option.createUrl : $.table._option.createUrl.replace("{id}", id);
                $.modal.openFull("添加" + $.table._option.modalName, url);
            },
            // 修改信息 全屏
            editFull: function (id) {
                var url = $.table._option.updateUrl.replace("{id}", id);
                $.modal.openFull("修改" + $.table._option.modalName, url);
            },
            // 保存信息
            save: function (url, data) {
                var index = $.modal.loading("正在处理中，请稍后...");
                var config = {
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        $.operate.saveSuccess(result, index);
                    }
                };
                $.ajax(config)
            },
            // 保存结果弹出msg刷新table表格
            ajaxSuccess: function (result, index) {
                if (result.status === web_status.SUCCESS) {
                    $.modal.msgSuccess(result.msg);
                    $.table.refresh();
                } else {
                    $.modal.alertError(result.msg);
                }
                $.modal.closeLoading(index);
            },
            // 保存结果提示msg
            saveSuccess: function (result, index) {
                if (result.status === web_status.SUCCESS) {
                    $.modal.msgReload("保存成功,正在刷新数据请稍后……", modal_status.SUCCESS);
                } else {
                    $.modal.alertError(result.msg);
                }
                $.modal.closeLoading(index);
            },
            //添加tab页
            addTab: function (id, title, url) {
                addTab({id: id, title: title, close: true, url: url})
            }
        }
    });
})(jQuery);

/** 消息状态码 */
web_status = {
    SUCCESS: '200',
    FAIL: '500'
};

/** 弹窗状态码 */
modal_status = {
    SUCCESS: "success",
    FAIL: "error",
    WARNING: "warning"
};