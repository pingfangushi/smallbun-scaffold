$(function () {
    //@formatter:off
    var options = {
        modalName: '部门',
        url: contextPath + "org/list", /*列表URL*/
        createUrl: contextPath + "org/form{id}", /*添加URL*/
        updateUrl: contextPath + "org/form{id}", /*修改URL*/
        removeUrl: contextPath + "org/removeById", /*删除URL*/
        batRemoveUrl: contextPath + "org/removeByIds",
        exportUrl: contextPath + "org/export", /*导出URL*/
        idField: 'id',
        parentIdField: 'parentId',
        rootParentId: '0',
        treeShowField: 'orgName',
        initialState: 'collapsed',
        showToggle: 'false',
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'id', visible: false},
            {field: 'orgName', title: '机构名称', sortable: false, align: 'left'},
            {field: 'orgCode', title: '机构编码', sortable: false, align: 'left'},
            {field: 'orgTypeName', title: '机构类型', sortable: false, align: 'center'},
            {field: 'gradeName', title: '机构级别', sortable: false, align: 'left'},
            {field: 'principal', title: '负责人', visible: false,sortable: false, width: 'auto', align: 'center'},
            {field: 'telephone', title: '电话', visible: false,  sortable: false, width: 'auto', align: 'center'},
            {field: 'useable', title: '是否可用', sortable: false, width: 'auto', align: 'center', formatter: 'useableFormatter'},
            {title: '操作', width: 50, visible: true,formatter: function (value, row, index) {
                var actions = [];
                actions.push('<div class="btn-group"><button type="button" class="btn ibtn-primary btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog"></i>&nbsp;<span class="fa fa-chevron-down"></span></button>'+
                    '<ul class="dropdown-menu" role="menu">'+
                    '<li><a href="#" onclick="$.operate.view(\'' + row.id + '\',\'\')"><i class="fa fa-search-plus"></i>查看</a></li>'+
                    '<li><a href="#" onclick="$.operate.edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>修改</a></li>'+
                    '<li><a href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-trash"></i>删除</a></li>'+
                    '<li><a href="#" onclick="add(\'' + row.id + '\')"><i class="fa fa-bars"></i>添加下级</a></li>'+
                    '</ul>'+
                    '</div>');
                return actions.join('');
                }
            }
        ]
    };
    $.treeTable.init(options);
    //@formatter:off
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
/**
 * 添加方法
 * @param id
 */
function add(id) {
    $.modal.open("添加" + $.table._option.modalName, $.table._option.createUrl.replace("{id}" ,"?parentId=" + id));
}