/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
        showToggle: 'false',
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'id', visible: false},
            {field: 'orgName', title: '机构名称', align: 'left',formatter: $.table.view},
            {field: 'orgCode', title: '机构编码', align: 'left'},
            {field: 'orgTypeName', title: '机构类型', align: 'left'},
            {field: 'gradeName', title: '机构级别', align: 'left'},
            {field: 'principal', title: '负责人', visible: false,sortable: false, width: 'auto', align: 'left'},
            {field: 'telephone', title: '电话', visible: false,  sortable: false, width: 'auto', align: 'left'},
            {field: 'useableName', title: '是否可用', width: 'auto', align: 'left'},
            {title: '操作', width: 50,align: 'center', visible: true,formatter: function (value, row, index) {
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
 * 添加方法
 * @param id
 */
function add(id) {
    $.modal.open("添加" + $.table._option.modalName, $.table._option.createUrl.replace("{id}" ,"?parentId=" + id));
}