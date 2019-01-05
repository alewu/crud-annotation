<#if tableMetaData ?? >
<#assign customFields=tableMetaData.customFields>
<#assign entityName=tableMetaData.entityName>
package ${packageName}.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * @author ${author}
 * @date ${date}
 * @@description ${entityName} 数据库表对应的实体类
*/
@Data
@SuppressWarnings("serial")
public class ${entityName} extends BaseEntity implements Serializable {
    <#list customFields as customField>
    <#if !(customField.memberVariable ? starts_with("gmt"))>
    /** ${customField.remarks!} **/
    <#if customField.typeName = 'BIT' >
    private Boolean ${customField.memberVariable};
    </#if>
    <#if customField.typeName = 'INT' || customField.typeName = 'TINYINT' ||  customField.typeName = 'SMALLINT'>
    private Integer ${customField.memberVariable};
    </#if>
    <#if customField.typeName = 'BIGINT' >
    private Long ${customField.memberVariable};
    </#if>
    <#if customField.typeName = 'DOUBLE' >
    private Double ${customField.memberVariable};
    </#if>
    <#if customField.typeName = 'DECIMAL' >
    private Decimal ${customField.memberVariable};
    </#if>
    <#if customField.typeName = 'CHAR' || customField.typeName = 'VARCHAR' || customField.typeName = 'TEXT' >
    private String ${customField.memberVariable};
    </#if>
    <#if customField.typeName = 'BLOB' >
    private byte[] ${customField.memberVariable};
    </#if>
    <#if customField.typeName = 'DATETIME' || customField.typeName = 'TIMESTAMP' || customField.typeName = 'DATE'>
    private Date ${customField.memberVariable};
    </#if>
    </#if>
    </#list>


}
</#if>
