package ${packageName}.constants;
/**
 * @author ${author}
 * @date ${date}
 * @description  RestURIConstants 常量类
 */
<#if tableMetaDatas ?? >
public class RestURIConstants {

    public static final String APP_PREFIX  = "${appPrefix}";
    <#list tableMetaDatas>
    <#items as tableMetaData>
    public static final String ${tableMetaData.entityName?upper_case}S = "/${tableMetaData.entityName?uncap_first}s";
    public static final String ${tableMetaData.entityName?upper_case}_ID = "/{${tableMetaData.entityName?uncap_first}Id}";
    </#items>
    </#list>

}
</#if>