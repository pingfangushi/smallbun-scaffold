/**
 * 初始化
 */
$(function () {
    /**
     * comboTreeGrid 属性
     */
    var comboTreeGridId = '#parent';
    var idField = 'id';
    var treeField = 'menuName';
    var url = contextPath + 'menu/tree';
    var editable = false;
    var method = 'POST';
    var contrastField = '#id';
    var panelWidth = 410;
    var width = '100%';
    var columns = [[
        {field: 'menuName', title: '名称', width: 150},
        {field: 'menuTypeName', title: '类型', width: 50},
        {field: 'url', title: '链接', width: 200}
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