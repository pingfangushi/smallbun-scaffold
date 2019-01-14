/*
 *
 * Copyright(c)[2018] [smallbun] www.smallbun.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
$(function () {
    //@formatter:off
    var options = {
        url: contextPath + 'role/page',
        createUrl: contextPath + "role/form",
        updateUrl: contextPath + "role/form/{id}",
        removeUrl: contextPath + "role/removeById",
        batRemoveUrl: contextPath + "role/removeByIds",
        exportUrl: contextPath + "role/export",
        sortName: "gmtCreate",
        sortOrder: "desc",
        modalName: "角色",
        search: false,
        showExport: false,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        columns: [{checkbox: true},
            {field: 'id', visible: false},
            {field: 'roleName', title: '角色名称', formatter: $.table.view},
            {field: 'enName', title: '角色编码', sortable: true},
            {field: 'roleTypeName', title: '角色类型', sortable: true},
            {field: 'dataScopeName', title: '数据范围', sortable: true},
            {field: 'useable', title: '是否可用', sortable: true, formatter: useableFormatter,width: 50},
            {field: 'gmtModified', title: '更新时间'},
            {title: '操作', width: 205, formatter: function (value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn ibtn-white btn-xs " href="#" onclick="$.operate.addTab(\'sys_dict_type' + row.id + '\', \'字典值\', \'dict/value?sysDictType.id=' + row.id + '\')"><i class="fa fa-bars"></i> 分配用户</a> ');
                    actions.push('<a class="btn bg-orange btn-xs" href="#" onclick="$.operate.edit(\'' + row.id + '\')"><i class="fa fa-edit"></i> 编辑</a> ');
                    actions.push('<a class="btn bg-maroon btn-xs " href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-remove"></i> 删除</a> ');
                    return actions.join('');
                }
            }]
    };
    $.table.init(options);
    //@formatter:off
});

function useableFormatter(value, row, index) {
    if (value === '0') {
        return '<span class="label label-success">可用</span>';
    }
    if (value === '1') {
        return '<span class="label label-danger">不可用</span>';
    }
}
