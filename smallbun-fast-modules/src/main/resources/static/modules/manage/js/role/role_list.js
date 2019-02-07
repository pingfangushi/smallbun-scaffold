$(function () {
    //@formatter:off
    var options = {
        url: contextPath + 'role/page',
        createUrl: contextPath + "role/form",
        updateUrl: contextPath + "role/form/{id}",
        removeUrl: contextPath + "role/removeById",
        batRemoveUrl: contextPath + "role/removeByIds",
        exportUrl: contextPath + "role/export",
        sortName: "gmtCreate",
        sortOrder: "desc",
        modalName: "角色",
        search: false,
        showExport: false,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        columns: [{checkbox: true},
            {field: 'id', visible: false},
            {field: 'roleName', title: '角色名称', formatter: $.table.view, sortable: true},
            {field: 'enName', title: '角色编码', sortable: true},
            {field: 'roleTypeName', title: '角色类型'},
            {field: 'dataScopeName', title: '数据范围'},
            {field: 'useableName', title: '是否可用',width: 50},
            {field: 'gmtModified', title: '更新时间'},
            {title: '操作',align: 'center', width: 50,visible: true, formatter: function (value, row, index) {
              var actions = [];
              actions.push('<div class="btn-group"><button type="button" class="btn ibtn-primary btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog"></i>&nbsp;<span class="fa fa-chevron-down"></span></button>'+
                  '<ul class="dropdown-menu" role="menu">'+
                  '<li><a href="#" onclick="$.operate.view(\'' + row.id + '\',\'\')"><i class="fa fa-search-plus"></i>查看</a></li>'+
                  '<li><a href="#" onclick="$.operate.edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>修改</a></li>'+
                  '<li><a href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-trash"></i>删除</a></li>'+
                  '</ul>'+
                  '</div>');
              return actions.join('');
                }
            }]
    };
    $.table.init(options);
    //@formatter:off
});
