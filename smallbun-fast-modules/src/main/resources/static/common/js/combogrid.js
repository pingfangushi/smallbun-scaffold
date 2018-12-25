$(function ($) {
    $.extend({
        combogrid: {
            init: function (options) {
                $(options.comboGridId).combogrid({
                    panelHeight: $.combogrid.fixHeight(0.4),
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
                };

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
        }
    });
});
