package ${packageName}.service;

import ${packageName}.entity.${entityName};
import ${packageName}.common.page.PageBean;
import ${packageName}.common.page.PageParam;

/**
 * @author ${author}
 * @date ${date}
 * @description ${entityName}服务层接口
 */
public interface ${entityName}Service extends BaseService<${entityName}> {

    PageBean<${entityName}> list${entityName}(PageParam pageParam);

}