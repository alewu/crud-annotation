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
    public int insert(T t) {
        return baseDAO.insert(t);
    }

    @Override
    public int delete(String id) {
        return baseDAO.delete(id);
    }

    @Override
    public int update(T t) {
        return baseDAO.update(t);
    }

    @Override
    public T get(Object obj) {
        return baseDAO.get(obj);
    }


}

