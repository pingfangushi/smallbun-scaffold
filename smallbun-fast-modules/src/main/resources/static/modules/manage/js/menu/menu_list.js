$(function () {
    //@formatter:off
    var options = {
        modalName: '菜单',
        url: contextPath + "menu/list", /*列表URL*/
        createUrl: contextPath + "menu/form/{id}", /*添加URL*/
        updateUrl: contextPath + "menu/form/{id}", /*修改URL*/
        removeUrl: contextPath + "menu/removeById",
        exportUrl: contextPath + "menu/export", /*导出URL*/
        batRemoveUrl: contextPath + "menu/removeByIds",
        idField: 'id',
        parentIdField: 'parentId',
        rootParentId: '0',
        treeShowField: 'menuName',
        initialState: 'collapsed',
        showToggle: 'false',
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'id', visible: false},
            {field: 'menuName', title: '名称', sortable: false, width: '200px', align: 'left'},
            {field: 'url', title: '地址', sortable: false, width: '300px', align: 'left'},
            {field: 'sort', title: '排序', sortable: false, width: '60px', align: 'center'},
            {field: 'permission', title: '权限值', width: '300px', sortable: false, align: 'left'},
            {field: 'icon', title: '图标', sortable: false, width: '100px', align: 'center', formatter: 'iconFormatter'},
            {field: 'menuType', title: '类型', sortable: false, width: 'auto', align: 'center', formatter: 'typeFormatter'},
            {field: 'menuStatus', title: '状态', sortable: false, width: 'auto', align: 'center', formatter: 'statusFormatter'},
            {title: '操作', width: 50, visible: true,formatter: function (value, row, index) {
                var actions = [];
                actions.push('<div class="btn-group"><button type="button" class="btn ibtn-primary btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog"></i>&nbsp;<span class="fa fa-chevron-down"></span></button>'+
                    '<ul class="dropdown-menu" role="menu">'+
                    '<li><a href="#" onclick="$.operate.editTab(\'' + row.id + '\')"><i class="fa fa-edit"></i>修改</a></li>'+
                    '<li><a href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-trash"></i>删除</a></li>'+
                    '<li><a href="#" onclick="add(\'' + row.id + '\')"><i class="fa fa-bars"></i>添加下级</a></li>'+
                    '</ul>'+
                    '</div>');
                return actions.join('');
                }
            }]
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
/**
 * 添加下级菜单
 * @param id
 */
function add(id) {
    $.modal.openTab("_tab" + Math.random().toString(36).substring(2), "添加" + $.table._option.modalName, $.table._option.createUrl.replace("{id}" ,"?parentId=" + id));
}