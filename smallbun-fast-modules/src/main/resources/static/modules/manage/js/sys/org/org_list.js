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
            {field: 'orgName', title: '名称', sortable: false, width: '200px', align: 'left'},
            {field: 'telephone', title: '电话', sortable: false, width: '300px', align: 'left'},
            {field: 'grade', title: '机构等级', sortable: false, width: '60px', align: 'center'},
            {field: 'type', title: '机构类型', width: '300px', sortable: false, align: 'left'},
            {
                field: 'orgStatus',
                title: '状态',
                sortable: false,
                width: 'auto',
                align: 'center',
                formatter: 'statusFormatter'
            }
        ]
    };
    $.treeTable.init(options);
});

/**
 * 格式化状态
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function statusFormatter(value, row, index) {
    if (value === '0') {
        return '<span class="label label-success">正常</span>';
    }
    if (value === '1') {
        return '<span class="label label-danger">锁定</span>';
    }
}