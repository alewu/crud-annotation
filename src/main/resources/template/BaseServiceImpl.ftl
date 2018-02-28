package ${packageName}.service.impl;

import ${packageName}.dao.BaseDAO;
import ${packageName}.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author ${author}
 * @date ${date}
 * @description 基础服务层
 */
@Service
public class BaseServiceImpl<T> implements BaseService<T>{
    private BaseDAO<T> baseDAO;

    public void setBaseDAO(BaseDAO<T> baseDao) {
        this.baseDAO = baseDao;
    }

    @Override
    public int saveOne(T t) {
        return baseDAO.saveOne(t);
    }

    @Override
    public int removeOne(String id) {
        return baseDAO.removeOne(id);
    }

    @Override
    public int updateOne(T t) {
        return baseDAO.updateOne(t);
    }

    @Override
    public T getOne(Object obj) {
        return baseDAO.getOne(obj);
    }


}

