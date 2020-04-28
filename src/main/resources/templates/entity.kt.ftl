<#--

    Copyright (c) 2018-2020. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

-->
package ${package.Entity}

<#list table.importPackages as pkg>
    import ${pkg}
</#list>
<#if swagger2>
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
</#if>
/**
* <p>
    * ${table.comment}
    * </p>
*
* @author SanLi Automatic generated
* Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  ${date}
*/
<#if table.convert>
    @TableName("${table.name}")
</#if>
<#if swagger2>
    @ApiModel(value="${entity}对象", description="${table.comment!}")
</#if>
<#if superEntityClass??>
    class ${entity} : ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
    class ${entity} : Model<${entity}>() {
<#else>
    class ${entity} : Serializable {
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
        <#if swagger2>
            @ApiModelProperty(value = "${field.comment}")
        <#else>
            /**
            * ${field.comment}
            */
        </#if>
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
        <#if field.keyIdentityFlag>
            @TableId(value = "${field.name}", type = IdType.AUTO)
        <#elseif idType ??>
            @TableId(value = "${field.name}", type = IdType.${idType})
        <#elseif field.convert>
            @TableId("${field.name}")
        </#if>
    <#-- 普通字段 -->
    <#elseif field.fill??>
    <#-- -----   存在字段填充设置   ----->
        <#if field.convert>
            @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
        <#else>
            @TableField(fill = FieldFill.${field.fill})
        </#if>
    <#elseif field.convert>
        @TableField("${field.name}")
    </#if>
<#-- 乐观锁注解 -->
    <#if (versionFieldName!"") == field.name>
        @Version
    </#if>
<#-- 逻辑删除注解 -->
    <#if (logicDeleteFieldName!"") == field.name>
        @TableLogic
    </#if>
    <#if field.propertyType == "Integer">
        var ${field.propertyName}: Int? = null
    <#else>
        var ${field.propertyName}: ${field.propertyType}? = null
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------->


<#if entityColumnConstant>
    companion object {
    <#list table.fields as field>

        const val ${field.name.toUpperCase()} : String = "${field.name}"

    </#list>
    }

</#if>
<#if activeRecord>
    override fun pkVal(): Serializable? {
    <#if keyPropertyName??>
        return ${keyPropertyName}
    <#else>
        return null
    </#if>
    }

</#if>
override fun toString(): String {
return "${entity}{" +
<#list table.fields as field>
    <#if field_index==0>
        "${field.propertyName}=" + ${field.propertyName} +
    <#else>
        ", ${field.propertyName}=" + ${field.propertyName} +
    </#if>
</#list>
"}"
}
}
