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
$(function () {
    //@formatter:off
    var options = {
        url: contextPath + 'dict/type/page',
        createUrl: contextPath + "dict/type/form",
        updateUrl: contextPath + "dict/type/form/{id}",
        removeUrl: contextPath + "dict/type/removeById",
        batRemoveUrl: contextPath + "dict/type/removeByIds",
        exportUrl: contextPath + "/export",
        sortName: "gmtCreate",
        sortOrder: "desc",
        modalName: "字典",
        search: false,
        showExport: false,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        columns: [{checkbox: true},
            {field: 'id', visible: false},
            {field: 'typeName', title: '字典名称', formatter: $.table.view, sortable: true},
            {field: 'typeCode', title: '字典类型', sortable: true},
            {field: 'gmtModified', title: '更新时间', sortable: true},
            {title: '操作',align: 'center', width: 50, visible: true, formatter: function (value, row, index) {
                    var actions = [];
                    actions.push('<div class="btn-group"><button type="button" class="btn ibtn-primary btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog"></i>&nbsp;<span class="fa fa-chevron-down"></span></button>'+
                                    '<ul class="dropdown-menu" role="menu">'+
                                        '<li><a href="#" onclick="$.operate.view(\'' + row.id + '\',\'\')"><i class="fa fa-search-plus"></i>查看</a></li>'+
                                        '<li><a href="#" onclick="$.operate.edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>修改</a></li>'+
                                        '<li><a href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-trash"></i>删除</a></li>'+
                                        '<li><a href="#" onclick="$.modal.openTab(\'sys_dict_type' + row.id + '\', \'字典值\', \'dict/value?sysDictType.id=' + row.id + '\')"><i class="fa fa-plus"></i>字典值</a></li>'+
                                    '</ul>'+
                                '</div>');
                    return actions.join('');
                }
            }]
    };
    $.table.init(options);
    //@formatter:off
});
