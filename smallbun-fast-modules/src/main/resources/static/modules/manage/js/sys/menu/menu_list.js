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
var prefix = basePath + "menu";
$(function () {

    var options = {
        modalName: '菜单',
        createUrl: prefix + "/dict_type_form.html", /*添加URL*/
        updateUrl: prefix + "/edit/{id}", /*修改URL*/
        removeUrl: prefix + "/remove", /*删除URL*/
        exportUrl: prefix + "/export", /*导出URL*/
        url: prefix + "/list", /*列表URL*/
        idField: 'id',
        parentIdField: 'parentId',
        rootParentId: '0',
        treeShowField: 'menuName',
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'menuName', title: '名称', sortable: false, width: '200px', align: 'left'},
            {field: 'url', title: '地址', sortable: false, width: '300px', align: 'left'},
            {field: 'sort', title: '排序', sortable: false, width: '60px', align: 'center'},
            {field: 'permission', title: '权限值', width: '300px', sortable: false, align: 'left'},
            {
                field: 'icon',
                title: '图标',
                sortable: false,
                width: '100px',
                align: 'center',
                formatter: 'iconFormatter'
            },
            {
                field: 'type',
                title: '类型',
                sortable: false,
                width: 'auto',
                align: 'center',
                formatter: 'typeFormatter'
            },
            {
                field: 'menuStatus',
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


/**
 * 图标
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function iconFormatter(value, row, index) {
    return '<span class="' + value + '"></span>';
}

/**
 *
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function typeFormatter(value, row, index) {
    if (value === 0) {
        return '<span class="label label-info">目录</span>';
    }
    if (value === 1) {
        return '<span class="label label-primary">菜单</span>';
    }
    if (value === 2) {
        return '<span class="label label-warning">按钮</span>';
    }
    return '';
}