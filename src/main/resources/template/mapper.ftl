<#if tableMetaData??>
<#assign customFields=tableMetaData.customFields>
<#assign entityName=tableMetaData.entityName>
<#assign tableName=tableMetaData.tableName>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.dao.${entityName}DAO">

    <#list customFields>
    <!-- 通用查询结果列：数据库表字段 -->
    <sql id="Base_Column_List">
    <#items as customField >
        ${customField.columnName}<#sep>,
    </#items>

    </sql>
    </#list>
    <#list customFields>
    <!-- 通用resultMap：实体类成员变量一一对应数据库表字段 -->
    <resultMap type="${packageName}.entity.${entityName}" id="BaseResultMap">
    <#items as customField>
    <#if customField ? index == 0>
        <id property="${customField.memberVariable}" column="${customField.columnName}" />
    <#else>
        <result property="${customField.memberVariable}" column="${customField.columnName}" />
    </#if>
    </#items>
    </resultMap>
    </#list>

    <#list customFields>
    <insert id="insert" useGeneratedKeys="true" keyProperty="${customFields[0].memberVariable}" parameterType="${packageName}.entity.${entityName}">
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#items as customField>
            <#if !customField.memberVariable ? contains("gmtModified") >
        <#if customField.typeName ='VARCHAR' >
            <if test="${customField.memberVariable} != null and ${customField.memberVariable} !=''">
                ${customField.columnName},
            </if>
        <#else>
            <if test="${customField.memberVariable} != null">
                ${customField.columnName},
            </if>
        </#if>
            </#if>
        </#items>
        </trim>
    </#list>
    <#list customFields>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#items as customField>
             <#if !customField.memberVariable ? contains("gmtModified") >
        <#if (customField.typeName ='VARCHAR') >
            <if test="${customField.memberVariable} != null and ${customField.memberVariable} !=''">
                ${r'#{'}${customField.memberVariable}${r'}'},
            </if>
        <#else>
            <if test="${customField.memberVariable} != null">
                ${r'#{'}${customField.memberVariable}${r'}'},
            </if>
        </#if>
             </#if>
        </#items>
        </trim>
    </#list>
    </insert>
    <delete id="delete" parameterType="java.lang.Integer">
        DELETE FROM ${tableName}
        WHERE ${customFields[0].columnName} = ${r'#{'}${customFields[0].memberVariable}${r'}'}
    </delete>
<#if !(customFields[3].columnName == "gmt_create" && customFields[2].columnName ? ends_with("_id")) >
<#list customFields>
    <update id="update" parameterType="${packageName}.entity.${entityName}">
        UPDATE ${tableName}
        <set>
    <#items as customField>
        <#if customField ? index != 0>
            <#if !customField.memberVariable ? contains("gmtCreate") >
        <#if (customField.typeName ='VARCHAR') >
            <if test="${customField.memberVariable} != null and ${customField.memberVariable} !=''">
                ${customField.columnName} = ${r'#{'}${customField.memberVariable}${r'}'},
            </if>
        <#else>
            <if test="${customField.memberVariable} != null">
                ${customField.columnName} = ${r'#{'}${customField.memberVariable}${r'}'},
            </if>
        </#if>
            </#if>
        </#if>
    </#items>
        </set>
        WHERE ${customFields[0].columnName} = ${r'#{'}${customFields[0].memberVariable}${r'}'}
    </update>
</#list>
    <select id="get" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List"/>
        FROM ${tableName}
        WHERE ${customFields[0].columnName} = ${r'#{'}${customFields[0].memberVariable}${r'}'}
    </select>

    <select id="list${entityName}" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List"/>
        FROM ${tableName}
        <where>
            <if test="keyword != null and keyword != ''">
                <bind name="pattern_keyword" value="'%' + keyword + '%'"/>
                columnName LIKE  ${r'#{'}pattern_keyword${r'}'}
            </if>
        </where>
    </select>
</#if>


</mapper>


</#if>