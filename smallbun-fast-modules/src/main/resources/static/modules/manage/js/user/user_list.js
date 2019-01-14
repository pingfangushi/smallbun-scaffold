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
$(function () {

    var options = {
        url: contextPath + 'user/page',
        createUrl: "user/form",
        updateUrl: "user/form/{id}",
        removeUrl: contextPath + "/remove",
        exportUrl: contextPath + "/export",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "用户",
        search: false,
        showExport: false,
        showRefresh: false,
        showColumns: false,
        showToggle: false,
        columns: [{checkbox: true},
            {field: 'username', title: '用户名'},
            {field: 'fullName', title: '姓名', sortable: true},
            {field: 'phone', title: '手机'},
            {field: 'telephone', title: '电话'},
            {field: 'email', title: '邮箱', visible: false},
            {field: 'mobile', title: '归属公司'},
            {field: 'mobile', title: '归属部门'},
            {
                field: 'status', title: '状态', align: 'center',
                formatter: function (value, row, index) {
                    //return $.table.selectDictLabel(dist, value);
                }
            },
            {
                title: '操作', align: 'center', visible: false,
                formatter: function (value, row, index) {

                }
            }]
    };
    $.table.init(options);
});

