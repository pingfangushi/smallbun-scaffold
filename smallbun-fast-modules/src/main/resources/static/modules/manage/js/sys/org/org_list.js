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


var prefix = basePath + "org";

$(function () {

    var options = {
        modalName: '部门',
        url: prefix + "/list", /*列表URL*/
        createUrl: prefix + "/form", /*添加URL*/
        updateUrl: prefix + "/form/{id}", /*修改URL*/
        removeUrl: prefix + "/remove", /*删除URL*/
        exportUrl: prefix + "/export", /*导出URL*/
        idField: 'id',
        parentIdField: 'parentId',
        rootParentId: '0',
        treeShowField: 'orgName',
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'orgName', title: '机构名称', sortable: false, align: 'left'},
            {field: 'orgCode', title: '机构编码', sortable: false, align: 'left'},
            {field: 'orgTypeName', title: '机构类型', sortable: false, align: 'center'},
            {field: 'gradeName', title: '机构级别', sortable: false, align: 'left'},
            {field: 'principal', title: '负责人', sortable: false, width: 'auto', align: 'center'},
            {field: 'telephone', title: '电话', sortable: false, width: 'auto', align: 'center'},
            {field: 'useable', title: '是否可用', sortable: false, width: 'auto', align: 'center', formatter: 'useableFormatter'},
            {title: '操作', width: 220,
                formatter: function (value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn bg-orange btn-xs" href="#" onclick="$.operate.edit(\'' + row.id + '\')"><i class="fa fa-edit"></i> 编辑</a> ');
                    actions.push('<a class="btn bg-maroon btn-xs " href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-remove"></i> 删除</a> ');
                    actions.push('<a class="btn ibtn-white btn-xs " href="#" onclick="$.operate.add(\'' + row.id + '\')"><i class="fa fa-bars"></i> 添加下级</a> ');
                    return actions.join('');
                }
            }
        ]
    };
    $.treeTable.init(options);
});

/**
 * 可用
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function useableFormatter(value, row, index) {
    if (value === '0') {
        return '<span class="label label-success">可用</span>';
    }
    if (value === '1') {
        return '<span class="label label-danger">不可用</span>';
    }
}