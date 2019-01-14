var prefix = contextPath + "menu";
$(function () {
    //@formatter:off
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
        initialState: 'collapsed',
        showToggle: 'false',
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'menuName', title: '名称', sortable: false, width: '200px', align: 'left'},
            {field: 'url', title: '地址', sortable: false, width: '300px', align: 'left'},
            {field: 'sort', title: '排序', sortable: false, width: '60px', align: 'center'},
            {field: 'permission', title: '权限值', width: '300px', sortable: false, align: 'left'},
            {field: 'icon', title: '图标', sortable: false, width: '100px', align: 'center', formatter: 'iconFormatter'},
            {field: 'type', title: '类型', sortable: false, width: 'auto', align: 'center', formatter: 'typeFormatter'},
            {field: 'menuStatus', title: '状态', sortable: false, width: 'auto', align: 'center', formatter: 'statusFormatter'}
        ]
    };
    $.treeTable.init(options);
    //@formatter:off
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