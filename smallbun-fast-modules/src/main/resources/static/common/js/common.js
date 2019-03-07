/**
 * 通用方法封装处理
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/9/7
 */
(function ($) {
    var $table = $('.table');
    $.extend({
        /**
         * 表格封装处理
         */
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
                    iconSize: 'sm',                                     // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
                    sidePagination: $.common.isNotEmpty(options.sidePagination) ? options.pagination : "server",                           // 启用服务端分页
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
                    //如果是本地分页,后台应使用AjaxResult返回数据
                    if ($.common.isNotEmpty($.table._option.sidePagination) && $.table._option.sidePagination === 'client') {
                        return {rows: res.result};
                    } else {
                        return {rows: res.page.records, total: res.page.total};
                    }
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
                $.modal.loading("正在导出数据，请稍后...");
                window.location.href = $.table._option.exportUrl + "?" + $("#" + currentId).serialize();
                $.modal.closeLoading();
            },
            // 导入
            importExcel: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                $.modal.loading("正在导出数据，请稍后...");
                $.post($.table._option.exportUrl, $("#" + currentId).serializeArray(), function (result) {
                    if (result.status === web_status.SUCCESS) {
                        window.location.href = contextPath + "fast/download?fileName=" + result.msg + "&delete=" + true;
                    }
                    $.modal.loading();
                });
            },
            // 下载导入模板
            importExcelTemplate: function (formId) {
                window.location.href = $.table._option.exportUrl + "?" + $("#" + currentId).serialize();
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
            view: function (value, row) {
                var actions = [];
                actions.push('<a  href="#" onclick="$.operate.view(\'' + row.id + '\',\'\')">' + value + '</a> ');
                return actions.join('');
            },
            //为空处理
            emptyProcessing: function (value, row, index) {
                if ($.common.isEmpty(value)) {
                    return '-'
                }
                return value;
            }
        },
        /**
         * 表格树封装处理
         */
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
                    clickToSelect: true,                                //是否启用点击选中行
                    toolbarAlign: 'left',                               //工具栏对齐方式
                    buttonsAlign: 'right',                              //按钮对齐方式
                    width: 'auto',
                    height: '760',
                    sidePagination: 'server',
                    toolbar: "#toolbar",
                    showRefresh: $.common.visible(options.showRefresh), // 是否显示刷新按钮
                    showColumns: $.common.visible(options.showColumns), // 是否显示隐藏某列下拉框
                    showToggle: $.common.visible(options.showToggle),   // 是否显示详细视图和列表视图的切换按钮
                    iconSize: 'sm',                                     // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
                    idField: options.idField,
                    rootParentId: options.rootParentId,
                    parentIdField: options.parentIdField,
                    columns: options.columns,
                    treeShowField: options.treeShowField,
                    responseHandler: $.treeTable.responseHandler,       // 回调函数
                    onLoadSuccess: function (res) {
                        $table.treegrid({
                            initialState: options.initialState,         //不展开
                            treeColumn: 1,
                            expanderExpandedClass: 'treegrid-expander glyphicon glyphicon-chevron-down',
                            expanderCollapsedClass: 'treegrid-expander glyphicon glyphicon-chevron-right',
                            onChange: function () {
                                $table.bootstrapTable('resetWidth');
                            }
                        });
                    },
                    onPostBody: function () {
                        $table.treegrid({
                            treeColumn: 1,
                            expanderExpandedClass: 'treegrid-expander glyphicon glyphicon-chevron-down',
                            expanderCollapsedClass: 'treegrid-expander glyphicon glyphicon-chevron-right',
                            onChange: function () {
                                $table.bootstrapTable('resetWidth')
                            }
                        })
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
                if (res.status !== web_status.SUCCESS) {
                    $.modal.alert(res.status + ":" + res.msg, modal_status.FAIL);
                    return [];
                } else {
                    return res.result;
                }
            },
            // 刷新
            refresh: function () {
                $.treeTable._treeTable.bootstrapTable('refresh');
            },
            //展开
            expandAll: function () {
                $table.removeClass('treegrid-collapsed');
                $table.treegrid('expandAll');
                $table.addClass('treegrid-expanded');
            },
            //折叠
            collapseAll: function () {
                $table.removeClass('treegrid-expanded');
                $table.treegrid('collapseAll');
                $table.addClass('treegrid-collapsed');
            },
            //展开或者折叠
            expandOrCollapse: function () {
                $table.addClass('treegrid-collapsed');
                if ($table.treegrid('isExpanded')) {
                    $.treeTable.collapseAll();
                    return;
                }
                if ($table.treegrid('isCollapsed')) {
                    $.treeTable.expandAll();
                }
            }
        },
        /**
         * 表单封装处理
         */
        form: {
            // 表单重置
            reset: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                $("#" + currentId)[0].reset();
                //刷新表格
                $.table.refresh();
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
                checkeds = checkeds.substring(1, checkeds.length);
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
        /**
         * 弹出层封装处理
         */
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
            open: function (title, url, width, height, callback) {
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
                if ($.common.isEmpty(callback)) {
                    callback = function (index, layero) {
                        var body = top.layer.getChildFrame('body', index);
                        var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        //文档地址
                        //调用提交方法
                        iframeWin.contentWindow.doSubmit();
                    }
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
                    yes: callback,
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
                App.blockUI({
                    boxed: true,
                    message: message,
                    animate: false
                });
                //$.blockUI({message: '<div class="loaderbox"><div class="loading-activity"></div> ' + message + '</div>'});
            },
            // 关闭遮罩层
            closeLoading: function () {
                setTimeout(function () {
                    App.unblockUI();
                }, 50);
            },
            // 重新加载
            reload: function () {
                parent.location.reload();
            },
            // 保存结果提示msg
            saveSuccess: function (result) {
                if (result.status === web_status.SUCCESS) {
                    $.modal.loading("保存成功,正在刷新数据请稍后……");
                    $.modal.reload();
                }
                $.modal.closeLoading();
            },
            //添加tab页
            openTab: function (id, title, url) {
                addTab({
                    id: id,
                    title: title,
                    close: true,
                    url: url,
                    list_id: $.common.isNotEmpty(window.frameElement) ? window.frameElement.getAttribute('id').substring(7, window.frameElement.getAttribute('id').length) : null
                })
            }
        },
        /**
         * 选择公共方法处理
         */
        select: {
            selectUser: function (input, callback, width, height) {
                //如果input是SPAN类型
                if (input.tagName === 'SPAN') {
                    input = $(input).prev();
                }
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                if ($.common.isEmpty(width)) {
                    width = ($(window).width() - 200);
                }
                if ($.common.isEmpty(height)) {
                    height = ($(window).height() - 50);
                }
                if ($.common.isEmpty(callback)) {
                    callback = function (index, layero) {
                        var body = top.layer.getChildFrame('body', index);
                        var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        //调用选择方法
                        var result = iframeWin.contentWindow.selectUsers();
                        //将返回的值赋给input
                        $(input).val(result.get('userName'));
                        $($(input).attr('idObj')).val(result.get('userId'));
                        //关闭
                        layer.close(index);
                    }
                }
                var index = layer.open({
                    type: 2,
                    area: [width + 'px', height + 'px'],
                    fix: false,
                    //不固定
                    maxmin: false,
                    shade: 0.3,
                    title: '选择用户',
                    content: contextPath + 'user/selectUser',
                    btn: ['确定', '关闭'],
                    yes: callback,
                    cancel: function (index) {

                    }
                });
                //layer.full(index);
            }
        },
        /**
         * 操作封装处理
         */
        operate: {
            // 提交数据
            submit: function (url, type, dataType, data) {
                $.modal.loading("正在处理中，请稍后...");
                var config = {
                    url: url,
                    type: type,
                    dataType: dataType,
                    data: data,
                    success: function (result) {
                        $.operate.ajaxSuccess(result);
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
                var url = $.common.isEmpty(id) ? $.table._option.createUrl.replace("{id}", '') : $.table._option.createUrl.replace("{id}", id);
                $.modal.open("添加" + $.table._option.modalName, url);
            }
            ,
            //添加，以tab页展现
            addTab: function (id) {
                var tab_id = "_tab" + Math.random().toString(36).substring(2);
                var url = $.common.isEmpty(id) ? $.table._option.createUrl.replace("{id}", '') : $.table._option.createUrl.replace("{id}", "?id=" + id);
                $.modal.openTab(tab_id, "添加" + $.table._option.modalName, url);
            },
            //查看，以tab页展现
            viewTab: function (id) {
                var tab_id = "_tab" + Math.random().toString(36).substring(2);
                var url = $.common.isEmpty(id) ? $.table._option.updateUrl.replace("{id}", '') : $.table._option.updateUrl.replace("{id}", "?id=" + id);
                $.modal.openTab(tab_id, "查看" + $.table._option.modalName, url);
            },
            //修改，以tab页打开
            editTab: function (id) {
                var tab_id = "_tab" + Math.random().toString(36).substring(2);
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
                $.modal.openTab(tab_id, "修改" + $.table._option.modalName, url);
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
            //tab页面展示查看
            tabView: function (id, url) {
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
                var tab_id = "_tab" + Math.random().toString(36).substring(2);
                $.modal.openTab(tab_id, "查看" + $.table._option.modalName, url);
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
            // 保存当前tab页
            saveCurrentTabPage: function (url, data) {
                $.modal.loading("正在处理中，请稍后...");
                var config = {
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.status === web_status.SUCCESS) {
                            $.modal.msgSuccess(result.msg);
                            //跳转list页面
                            parent.saveCurrentTabPage(window);
                        }
                        $.modal.closeLoading();
                    }
                };
                $.ajax(config)
            },
            // 保存信息
            save: function (url, data) {
                $.modal.loading("正在处理中，请稍后...");
                var config = {
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        $.modal.saveSuccess(result);
                    }
                };
                $.ajax(config)
            },
            // 保存结果弹出msg刷新table表格
            ajaxSuccess: function (result) {
                if (result.status === web_status.SUCCESS) {
                    $.modal.msgSuccess(result.msg);
                    $.table.refresh();
                }
                $.modal.closeLoading();
            },
            //导出
            exportExcel: function () {
                $.table.exportExcel();
            },
            //导入
            importExcel: function () {
                $.import.init($(".glyphicon-import"));
            }
        },
        /**
         * 通用方法封装处理
         */
        common: {
            //解析参数
            parseParam: function (param, key) {
                var paramStr = "";
                if (param instanceof String || param instanceof Number || param instanceof Boolean) {
                    paramStr += "&" + key + "=" + encodeURIComponent(param);
                } else {
                    $.each(param, function (i) {
                        var k = key == null ? i : key + (param instanceof Array ? "[" + i + "]" : "." + i);
                        paramStr += '&' + $.common.parseParam(this, k);
                    })
                }
                return paramStr.substr(1);
            },
            // 判断一个字符串是否为非空串
            isNotEmpty: function (value) {
                return !$.common.isEmpty(value);
            },
            // 判断字符串是否为空
            isEmpty: function (value) {
                return value == null || this.trim(value) === "";
            },
            // 是否显示数据 为空默认为显示
            visible: function (value) {
                return !!($.common.isEmpty(value) || value === true);
            },
            // 空格截取
            trim: function (value) {
                if (value == null) {
                    return "";
                }
                return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
            },
            // 指定随机数返回
            random: function (min, max) {
                return Math.floor((Math.random() * max) + min);
            }
        },
        /**
         * easyUI组合网格
         */
        comboGrid: {
            init: function (options) {
                $(options.comboGridId).combogrid({
                    panelHeight: $.comboGrid.fixHeight(0.4),
                    idField: options.idField,               //ID字段
                    textField: options.textField,           //显示的字段
                    url: options.url,
                    delay: $.common.isEmpty(options.delay) ? 500 : options.delay,                         //500ms延时查询
                    pageSize: $.common.isEmpty(options.pageSize) ? 10 : options.pageSize,                 //每页显示的记录条数，默认为10
                    pageList: $.common.isEmpty(options.pageList) ? 10 : [10, 15, 20, 25, 30],     //可以设置每页记录条数的列表
                    fitColumns: true,
                    striped: true,
                    editable: $.common.isEmpty(options.editable) ? false : true,
                    pagination: true,           //是否分页
                    rownumbers: false,          //序号
                    collapsible: false,         //是否可折叠的
                    fit: true,                  //自动大小
                    method: options.method,             //请求方法
                    columns: options.columns,         //展示列
                    keyHandler: {
                        up: function () {
                        },
                        down: function () {
                        },
                        enter: function () {
                        },
                        /**
                         * 【动态搜索】处理
                         * @param keyword
                         */
                        query: function (keyword) {
                            //调用查询方法
                            query(keyword);
                            $(options.comboGridId).combogrid("setValue", keyword);
                            //将查询条件存入隐藏域
                            $('#easyui-combogrid-id').val(keyword);
                            //清空隐藏域值
                            $(options.valueId).val('');

                        }
                    },
                    loader: loader,
                    /**
                     * 选中处理
                     */
                    onSelect: function () {
                        var selData = $(options.comboGridId).combogrid('grid').datagrid('getSelected');
                        //设置
                        $('#easyui-combogrid-id').val(selData['' + options.idField + '']);
                        //设置ID值
                        $(options.valueId).val(selData.id);
                        //移除验证
                        $(options.valueId + '-error').remove();
                        //移除样式
                        $('.input-group.panel-noscroll.has-error').removeClass('has-error');
                    }
                });

                //取得分页组件对象
                var pager = $(options.comboGridId).combogrid('grid').datagrid('getPager');

                if (pager) {
                    $(pager).pagination({
                        pageSize: options.pageSize,                 //每页显示的记录条数，默认为10
                        pageList: options.pageList,                 //可以设置每页记录条数的列表
                        beforePageText: '第',                //页数文本框前显示的汉字
                        afterPageText: '页    共 {pages} 页',
                        displayMsg: '共 {total} 条记录',//当前显示 {from} - {to} 条记录   共 {total} 条记录
                        //选择页的处理
                        onSelectPage: function (pageNumber, pageSize) { //按分页的设置取数据
                            getData(pageNumber, pageSize);
                            //设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
                            $(options.comboGridId).combogrid("grid").datagrid('options').pageSize = pageSize;
                            //将隐藏域中存放的查询条件显示在combogrid的文本框中
                            $(options.comboGridId).combogrid("setValue", $('#easyui-combogrid-id').val());
                        },
                        onChangePageSize: function () {
                        },
                        /**
                         * 改变页显示条数的处理 (处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了)
                         * @param pageNumber
                         * @param pageSize
                         */
                        onRefresh: function (pageNumber, pageSize) { //点击刷新的处理
                            getData(pageNumber, pageSize); //按分页的设置取数据
                            $(options.comboGridId).combogrid("setValue", $('#easyui-combogrid-id').val());//将隐藏域中存放的查询条件显示在combogrid的文本框中
                        }
                    });
                }

                /**
                 * 获取数据
                 * @param current
                 * @param size
                 */
                var getData = function (current, size) {
                    $.ajax({
                        type: options.method,
                        url: options.url,
                        data: {
                            "current": current,
                            "size": size
                        },
                        success: function (data) {
                            $(options.comboGridId).combogrid("grid").datagrid("loadData", buildData(data));
                        }
                    });
                };

                /**
                 * 查询方法
                 * @param data
                 */
                var query = function (data) {
                    var json = {};
                    json["" + options.textField + ""] = data;
                    $.ajax({
                        type: options.method,
                        url: options.url,
                        data: json,
                        dataType: "json",
                        success: function (data) {
                            $(options.comboGridId).combogrid("grid").datagrid("loadData", buildData(data));
                        }
                    });
                };

                /**
                 * 加载
                 * @param param
                 * @param success
                 * @param error
                 * @returns {boolean}
                 */
                function loader(param, success, error) {
                    var that = $(this);
                    var opts = that.datagrid("options");
                    if (!opts.url) {
                        return false
                    }
                    var cache = that.data().datagrid.cache;
                    var data = {"current": opts.pageNumber, "size": opts.pageSize};
                    if (!cache) {
                        $.ajax({
                            type: opts.method, url: opts.url, data: data, dataType: "json", success: function (data) {
                                that.data().datagrid["cache"] = data;
                                success(buildData(data))
                            }
                        })
                    } else {
                        success(buildData(cache))
                    }

                    function buildData(data) {
                        var temp = $.extend({}, data);
                        var tempRows = [];
                        var start = (param.page - 1) * parseInt(param.rows);
                        var end = start + parseInt(param.rows);
                        var rows = data.page.records;
                        for (var i = start; i < end; i++) {
                            if (rows[i]) {
                                tempRows.push(rows[i])
                            } else {
                                break
                            }
                        }
                        temp.rows = tempRows;
                        temp.total = data.page.total;
                        return temp
                    }
                }

                /**
                 * 绑定参数
                 * @param data
                 */
                var buildData = function (data) {
                    var temp = $.extend({});
                    temp.rows = data.page.records;
                    temp.total = data.page.total;
                    return temp
                }

            },
            /**
             * 调整高度
             * @param percent
             * @returns {number}
             */
            fixHeight: function (percent) {
                return (document.body.clientHeight) * percent
            },
            /**
             * 调整宽度
             * @param percent
             * @returns {number}
             */
            fixWidth: function (percent) {
                return (document.body.clientWidth - 5) * percent
            }
        },
        /**
         *comboTreeGrid 封装
         */
        comboTreeGrid: {
            init: function (options) {
                var comboTreeGrid = $(options.comboTreeGridId);
                comboTreeGrid.combotreegrid({
                    width: $.common.isEmpty(options.width) ? '100%' : options.width,
                    panelWidth: $.common.isEmpty(options.panelWidth) ? '400' : options.panelWidth,
                    editable: $.common.isEmpty(options.editable) ? false : options.editable,
                    idField: $.common.isEmpty(options.idField) ? '#id' : options.idField,               //ID字段
                    treeField: options.treeField,           //显示的字段
                    columns: options.columns,               //展示列
                    loader: loader,
                    onChange: function (newValue, oldValue) {
                        //如果选择的是同级节点
                        var contrastField = $.common.isEmpty($(options.contrastField).val()) ? '#id' : $(options.contrastField).val();
                        if (contrastField === newValue) {
                            $.modal.alert("不能选择同级节点,请重新选择！", modal_status.FAIL);
                            comboTreeGrid.combotreegrid('clear', "none");
                            comboTreeGrid.combotreegrid('setValue', oldValue);
                        } else {
                            comboTreeGrid.val(newValue);
                        }
                    }
                });

                /**
                 * 加载数据
                 * @param param
                 * @param success
                 * @param error
                 * @returns {boolean}
                 */
                function loader(param, success, error) {
                    $.ajax({
                        type: options.method, url: options.url, dataType: "json", success: function (data) {
                            success(buildData(data))
                        }
                    });
                }

                /**
                 * buildDatabuildData
                 * @param data
                 * @returns {*}
                 */
                function buildData(data) {
                    return data.result;
                }
            }
        },
        /**
         * ztree 封装
         */
        ztree: {
            /**
             * 搜索节点
             * @param obj
             */
            searchNode: function (obj) {

            },
            /**
             * 展开树
             * @param obj
             */
            expandTree: function (obj) {
                var tree = $.fn.zTree.getZTreeObj(obj);
                tree.expandAll(true);
            },

            /**
             * 收起树：只展开根节点下的一级节点
             * @param obj
             */
            closeTree: function (obj) {
                var tree = $.fn.zTree.getZTreeObj(obj);
                tree.expandAll(false);
            },

            /**
             * 勾选全部
             * @param obj
             */
            checkAllTrue: function (obj) {
                var tree = $.fn.zTree.getZTreeObj(obj);
                tree.checkAllNodes(true);
            },

            /**
             * 取消勾选
             * @param obj
             */
            checkAllFalse: function (obj) {
                var tree = $.fn.zTree.getZTreeObj(obj);
                tree.checkAllNodes(false);
            },

            /**
             * 获取选中的节点
             * @param obj
             * @returns {*}
             */
            getCheckedNodes: function (obj) {
                var tree = $.fn.zTree.getZTreeObj(obj);
                return tree.getCheckedNodes(true);
            }
        },
        /**
         * 弹出树
         */
        pop_up_tree: {
            init: function (options) {
                //为了防止页面多个，使用随机数生产ID
                var treeId = "zTree" + Math.random().toString(36).substring(2);
                var treeLayerId = "tree-layer" + Math.random().toString(36).substring(2);
                /**
                 * zTree配置
                 */
                var setting = {
                    data: {
                        simpleData: {
                            enable: true,
                            idKey: options.idKey,
                            pIdKey: options.pIdKey,
                            rootPId: options.rootPId
                        },
                        key: {
                            name: options.name,
                            url: "x"
                        }
                    }
                };
                var ztree;
                var obj = $('#' + options.obj);
                //在当前对象后面追加html内容
                obj.after(
                    '<div id="' + treeLayerId + '" style="display: none;">' +
                    '   <div class="box-header" id="search" style="display: block;padding: 20px;">' +
                    '    <label for="keyword">关键字：</label><input autocomplete="off" type="text" id="keyword" style="line-height: 24px; width:60%;border: 1px solid #bbb;padding: 0 4px;" maxlength="50">' +
                    '    <a class="btn btn-xs ibtn-primary" style="line-height: 22px;" onclick="$.ztree.searchNode(\'' + String(treeId) + '\')">搜索</a>' +
                    '   </div>' +
                    '   <div style="padding:10px 20px">' +
                    '      <div class="pull-right">' +
                    '         <a class="btn btn-box-tool" id="expand_default"   onclick="$.ztree.expandTree(\'' + String(treeId) + '\')">展开</a>/' +
                    '         <a class="btn btn-box-tool" id="collapse_default" onclick="$.ztree.closeTree(\'' + String(treeId) + '\')">折叠</a>' +
                    '      </div>' +
                    '      <input  id="' + options.value + '" type="text"  name="' + options.value + '" class="hidden"/>' +
                    '      <ul id="' + treeId + '" class="ztree"></ul>' +
                    '   </div>' +
                    '</div>'
                );
                var value = $('#' + options.value);
                //如果不为空，设置值
                if ($.common.isNotEmpty(obj.val())) {
                    value.val(obj.val());
                    //如果是顶级菜单，设置值为主目录
                    if (obj.val() === options.rootPId) {
                        obj.val($.common.isNotEmpty(options.topName) ? options.topName : '主目录')
                    }
                }
                //为空
                else {
                    if (!options.required && !obj.attr('required') && !obj.hasClass('required')) {
                        value.val(options.rootPId);
                        obj.val($.common.isNotEmpty(options.topName) ? options.topName : '主目录')
                    } else {
                        obj.attr("required", "required");
                        obj.val(options.topName)
                    }
                }
                /**
                 * 发送ajax请求
                 */
                $.ajax({
                    type: options.type,
                    url: options.url,
                    contentType: "application/json",
                    async: false,
                    dataType: "json",
                    success: function (result) {
                        //如果成功
                        if (result.status === web_status.SUCCESS) {
                            //渲染ztree
                            ztree = $.fn.zTree.init($("#" + treeId), setting, result.result);
                            //根据已经选择的节点ID进行渲染
                            var node = ztree.getNodeByParam(options.idKey, value.val());
                            //如果node不为空进行查询
                            if (node != null) {
                                ztree.selectNode(node);
                                //当前输入框添加内容
                                obj.val(node[options.name]);
                            }
                            ztree.expandAll(options.expand);
                        }
                    },
                    error: function () {
                        alert("系统错误，请稍后重试！");
                    }
                });
                /**
                 * 绑定单击事件（input）
                 */
                obj.bind('click', open);
                /**
                 * 绑定单击事件（btn）
                 */
                obj.parent().find('.input-group-addon').bind('click', open);

                /**
                 * 弹出框
                 */
                function open() {
                    var width = '300px';
                    var height = '450px';
                    //如果是移动端，就使用自适应大小弹窗
                    if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                        width = 'auto';
                        height = 'auto';
                    }
                    /**
                     * 弹出layer
                     */
                    layer.open({
                        type: 1,
                        offset: '50px',
                        title: "请选择",
                        area: [width, height],
                        shade: 0,
                        shadeClose: false,
                        content: jQuery("#" + treeLayerId), //弹框内容
                        btn: ['确定', '取消'],
                        btn1: function (index) {
                            var node = ztree.getSelectedNodes();
                            if (node.length > 0) {
                                //将选中的值放入隐藏value框
                                value.val(node[0][options.idKey]);
                                //将名称会显示内容框
                                obj.val(node[0][options.name]);
                            }
                            //选择上级菜单
                            layer.close(index);
                            try {
                                //调用验证，清除验证消息，可能会发生异常，进行捕获
                                obj.valid();
                            } catch (e) {
                            }
                        }
                    });
                }
            }
        },
        /**
         * 导入
         */
        import: {
            init: function () {
                /**
                 * 弹出框
                 */
                var width = '400px';
                var height = '230px';
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                /**
                 * 弹出layer
                 */
                layer.open({
                    type: 1,
                    title: "导入" + $.table._option.modalName + "数据",
                    area: [width, height],
                    shade: 0,
                    shadeClose: false,
                    content: '<form id="importForm" enctype="multipart/form-data" class="layui-layer-wrap" style="margin-top: 20px;">' +
                        '<div class="col-xs-offset-1">' +
                        '<input type="file" id="file" name="file">' +
                        '<div style="margin-top:10px">' +
                        '<input type="checkbox" id="updateSupport" name="updateSupport" title="如果登录账户已经存在，更新这条数据。">是否更新已经存在的' + $.table._option.modalName + '数据' +
                        ' &nbsp;<a onclick="$.table.importExcelTemplate()" class="btn btn-default btn-xs"><i class="fa fa-file-excel-o"></i>下载模板</a>' +
                        '</div>' +
                        '<font color="red" class="pull-left" style="margin-top:10px">' +
                        '提示：仅允许导入“xls”或“xlsx”格式文件！' +
                        '</font>' +
                        '</div>' +
                        '</form>', //弹框内容
                    success: function (layero, index) {
                        layer.iframeAuto(index);
                    },
                    btn: ['确定', '取消'],
                    btn1: function (index) {

                    }
                });
            }
        }

    });
})(jQuery);
/** 消息状态码 */
web_status = {
    SUCCESS: '200',
    FAIL: '500',
    DEMO_ERROR: '900001'
};

/** 弹窗状态码 */
modal_status = {
    SUCCESS: "success",
    FAIL: "error",
    WARNING: "warning"
};