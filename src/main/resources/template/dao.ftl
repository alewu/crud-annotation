package ${packageName}.dao;

import ${packageName}.entity.${entityName};
import java.util.List;
import org.springframework.stereotype.Repository;

/**
* @author ${author}
* @date ${date}
* @description ${entityName}DAO持久层映射接口
*/
@Repository
public interface ${entityName}DAO extends BaseDAO<${entityName}> {

    List<${entityName}> list${entityName}();

}