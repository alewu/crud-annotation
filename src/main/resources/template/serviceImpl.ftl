package ${packageName}.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${packageName}.common.util.PageUtil;
import ${packageName}.common.page.PageBean;
import ${packageName}.common.page.PageParam;
import ${packageName}.entity.${entityName};
import ${packageName}.dao.${entityName}DAO;
import ${packageName}.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ${author}
 * @date ${date}
 * @description ${entityName ? uncap_first}Service 接口业务逻辑实现类
 */
@Service("${entityName ? uncap_first}Service")
public class ${entityName}ServiceImpl extends BaseServiceImpl<${entityName}> implements ${entityName}Service{
    // 采用这种方式注入，为了设置父类中的dao
    private ${entityName}DAO ${entityName?uncap_first}DAO;
    @Autowired
    public void set${entityName}DAO(${entityName}DAO ${entityName?uncap_first}DAO) {
        super.setBaseDAO(${entityName?uncap_first}DAO);
        this.${entityName?uncap_first}DAO = ${entityName?uncap_first}DAO;
    }

    @Override
    public PageBean<${entityName}> list${entityName}(PageParam pageParam) {
        PageUtil.startPage(pageParam);
        List<${entityName}> list = ${entityName ? uncap_first}DAO.list${entityName}();
        return PageUtil.getPageBean(list);
    }

}