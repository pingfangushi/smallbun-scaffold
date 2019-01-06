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
    let comboTreeGrid = $('#parent');
    comboTreeGrid.combotreegrid({
        width: '100%',
        panelWidth: 410,
        editable: false,
        idField: 'id',
        treeField: 'orgName',
        columns: [[
            {field: 'orgName', title: '名称', width: 200},
            {field: 'orgTypeName', title: '类型', width: 100},
            {field: 'gradeName', title: '级别', width: 100},
        ]],
        loader: loader,
        onChange: function (newValue, oldValue) {
            //如果选择的是同级节点
            if ($('#id').val() === newValue) {
                $.modal.alert("不能选择同级节点", modal_status.FAIL);
                comboTreeGrid.combotreegrid('clear', "none");
                comboTreeGrid.combotreegrid('setValue', oldValue);
            } else {
                comboTreeGrid.val(newValue);
            }
        }
    });
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
        type: 'POST', url: contextPath + 'org/tree', dataType: "json", success: function (data) {
            success(buildData(data))
        }
    });
}

function buildData(data) {
    return data.result;
}