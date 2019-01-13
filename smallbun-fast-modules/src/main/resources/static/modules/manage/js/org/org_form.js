/**
 * 初始化
 */
$(function () {
    /**
     * comboTreeGrid 属性
     */
    var comboTreeGridId = '#parent';
    var idField = 'id';
    var treeField = 'orgName';
    var url = contextPath + 'org/tree';
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

/**
 * 提交事件
 */
function doSubmit() {
    // 手动触发校验代码
    if ($('.form-horizontal').valid()) {
        $.operate.save($('.form-horizontal').attr('action'), $('.form-horizontal').serializeArray());
    }
}

/**
 * 验证
 */
$(".form-horizontal").validate({
    rules: {
        telephone: {
            isTel: true
        },
        orgCode: {
            required: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "org/unique",
                type: "post",
                dataType: 'JSON',
                data: {
                    id: function () {
                        return $("#id").val();
                    },
                    typeCode: function () {
                        return $("#orgCode").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        },
        orgName: {
            required: true,
            remote: {
                async: false, //同步方法，如果用异步的话，flag永远为false
                url: contextPath + "org/unique",
                type: "post",
                dataType: 'JSON',
                data: {
                    id: function () {
                        return $("#id").val();
                    },
                    typeCode: function () {
                        return $("#orgCode").val();
                    }
                },
                dataFilter: function (data, type) {
                    data = JSON.parse(data);
                    return data.result;
                }
            }
        },
        zipCode: {
            isZipCode: true
        },
        fax: {
            fax: true
        },
        principal: {
            realName: true
        }
    },
    messages: {
        orgCode: {
            remote: "机构编码已存在"
        },
        orgName: {
            remote: "机构名称已存在"
        }
    }
});

