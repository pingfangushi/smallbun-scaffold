/*
 *
 *   Copyright(c)[2018] [smallbun] www.smallbun.org
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
$(function () {

    var options = {
        url: basePath + 'dict/value/page',
        createUrl: basePath + "dict/value/form?dictType=" + $('#dictType').val(),
        updateUrl: basePath + "dict/value/form/{id}",
        removeUrl: basePath + "dict/value/remove",
        exportUrl: basePath + "dict/value/export",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "字典",
        queryParams: queryParams,
        search: false,
        showExport: false,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        columns: [{checkbox: true},
            {field: 'id', visible: false},
            {field: 'sysDictType.id', visible: false},
            {field: 'sysDictType.typeName', title: '字典类型', formatter: viewDictType},
            {field: 'dictLabel', title: '字典标签', formatter: $.table.view},
            {field: 'dictValue', title: '字典键值', sortable: true},
            {field: 'sort', title: '排序', visible: false},
            {field: 'gmtModified', title: '更新时间'},
            {
                title: '操作', width: 200,
                formatter: function (value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn bg-purple btn-xs " href="#" onclick="$.operate.view(\'' + row.id + '\',\'\')"><i class="fa fa-search-plus"></i> 查看</a> ');
                    actions.push('<a class="btn bg-orange btn-xs" href="#" onclick="$.operate.edit(\'' + row.id + '\')"><i class="fa fa-edit"></i> 编辑</a> ');
                    actions.push('<a class="btn bg-maroon btn-xs " href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-remove"></i> 删除</a> ');
                    return actions.join('');
                }
            }]
    };
    $.table.init(options);

    /**
     * 自定义查询
     * @param params
     * @returns {{dictType: (*|jQuery), pageSize: (boolean|*), pageNum: number, searchValue: *, orderByColumn: *, isAsc: *}}
     */
    function queryParams(params) {
        return {
            'sysDictType.id': $("#dictType").val(),
            pageSize: params.limit,
            pageNum: params.offset / params.limit + 1,
            searchValue: params.search,
            orderByColumn: params.sort,
            isAsc: params.order
        };
    }
});

/**
 * 查看字典类型
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function viewDictType(value, row, index) {
    var actions = [];
    actions.push('<a  href="#" onclick="$.operate.view(\'' + row.sysDictType.id + '\',basePath +\'dict/type/form\')">' + value + '</a> ');
    return actions.join('');
}

