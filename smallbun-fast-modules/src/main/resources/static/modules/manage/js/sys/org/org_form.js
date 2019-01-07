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
/**
 * 提交事件
 */
function doSubmit() {
    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        $.operate.save($('.form-horizontal').attr('action'), $('.form-horizontal').serializeArray());
    }
}

$(function () {
    /**
     * comboTreeGrid 属性
     */
    var comboTreeGridId = '#parent';
    var idField = 'id';
    var treeField = 'orgName';
    var url = contextPath + 'org/list';
    var editable = false;
    var method = 'POST';
    var contrastField = '#id';
    var panelWidth = 410;
    var width = '100%';
    var columns = [[
        {field: 'orgName', title: '名称', width: 200},
        {field: 'orgTypeName', title: '类型', width: 100},
        {field: 'gradeName', title: '级别', width: 100},
    ]];
    $.comboTreeGrid.init(
        {
            comboTreeGridId: comboTreeGridId,
            panelWidth: panelWidth,
            width: width,
            idField: idField,
            treeField: treeField,
            url: url,
            editable: editable,
            method: method,
            columns: columns,
            contrastField: contrastField
        }
    );
});

