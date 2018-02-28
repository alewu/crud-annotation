<#if tableMetaData??>
<#assign customFields=tableMetaData.customFields>
<#assign entityName=tableMetaData.entityName>
package ${packageName}.controller;

import ${packageName}.entity.${entityName};
import ${packageName}.common.page.PageBean;
import ${packageName}.common.page.PageParam;
import ${packageName}.common.response.Response;
import ${packageName}.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static ${packageName}.constants.RestURIConstants.*;

/**
 * @author ${author}
 * @date ${date}
 * @description ${entityName} 控制器
 */
@RestController
@RequestMapping(APP_PREFIX)
@Validated
public class ${entityName}Controller {
    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

    /**
     * @author ${author}
     * @date ${date}
     * @description 增加单个${entityName}
     */
    @PostMapping(${entityName?upper_case}S)
    public Response save${entityName}(@Validated ${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}Service.saveOne(${entityName?uncap_first});
        return Response.ok().put("${entityName?uncap_first}Id", ${entityName?uncap_first}.get${entityName}Id());
    }
    <#if !( customFields[2].columnName ? ends_with("_id") && customFields[3].columnName == "gmt_create" ) >

    /**
     * @author ${author}
     * @date ${date}
     * @description 查询单个${entityName}
     */
    @GetMapping(${entityName?upper_case}S + ${entityName?upper_case}_ID)
    public Response get${entityName}(@PathVariable String ${entityName?uncap_first}Id) {
        ${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.getOne(${entityName?uncap_first}Id);
        return Response.ok().put("${entityName?uncap_first}", ${entityName?uncap_first});
    }

    /**
     * @author ${author}
     * @date ${date}
     * @description 分页查询${entityName}
     */
    @GetMapping(${entityName?upper_case}S)
    public Response list${entityName}(PageParam pageParam) {
        PageBean<${entityName}> pageBean = ${entityName?uncap_first}Service.list${entityName}(pageParam);
        return Response.ok().put("pageBean", pageBean);
    }

    /**
     * @author ${author}
     * @date ${date}
     * @description 更新单个${entityName}
    */
    @PutMapping(${entityName?upper_case}S)
    public Response update${entityName}(@Validated ${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}Service.updateOne(${entityName?uncap_first});
        return Response.ok();
    }

    /**
     * @author ${author}
     * @date ${date}
     * @description 删除单个${entityName}
     */
    @DeleteMapping(${entityName?upper_case}S + ${entityName?upper_case}_ID)
    public Response remove${entityName}(@PathVariable String ${entityName?uncap_first}Id) {
        ${entityName?uncap_first}Service.removeOne(${entityName?uncap_first}Id);
        return Response.ok();
    }
    </#if>
}


</#if>