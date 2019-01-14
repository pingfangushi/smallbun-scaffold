$(function () {
    //@formatter:off
    var options = {
        modalName: '部门',
        url: contextPath + "org/list", /*列表URL*/
        createUrl: contextPath + "org/form", /*添加URL*/
        updateUrl: contextPath + "org/form{id}", /*修改URL*/
        removeUrl: contextPath + "org/removeById", /*删除URL*/
        exportUrl: contextPath + "org/export", /*导出URL*/
        idField: 'id',
        parentIdField: 'parentId',
        rootParentId: '0',
        treeShowField: 'orgName',
        initialState: 'collapsed',
        showToggle: 'false',
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'orgName', title: '机构名称', sortable: false, align: 'left'},
            {field: 'orgCode', title: '机构编码', sortable: false, align: 'left'},
            {field: 'orgTypeName', title: '机构类型', sortable: false, align: 'center'},
            {field: 'gradeName', title: '机构级别', sortable: false, align: 'left'},
            {field: 'principal', title: '负责人', sortable: false, width: 'auto', align: 'center'},
            {field: 'telephone', title: '电话', sortable: false, width: 'auto', align: 'center'},
            {field: 'useable', title: '是否可用', sortable: false, width: 'auto', align: 'center', formatter: 'useableFormatter'},
            {title: '操作', width: 220, formatter: function (value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn bg-orange btn-xs" href="#" onclick="$.operate.edit(\'' + row.id + '\')"><i class="fa fa-edit"></i> 编辑</a> ');
                    actions.push('<a class="btn bg-maroon btn-xs " href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-remove"></i> 删除</a> ');
                    actions.push('<a class="btn ibtn-white btn-xs " href="#" onclick="add(\'' + row.id + '\')"><i class="fa fa-bars"></i> 添加下级</a> ');
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