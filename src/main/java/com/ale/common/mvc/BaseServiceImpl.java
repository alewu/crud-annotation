package com.ale.common.mvc;

/**
 * @author alewu
 * @date 2017-12-19
 * @description 基础服务层
 */
public class BaseServiceImpl<T> implements BaseService<T>{
    private BaseDAO<T> baseDAO;

    public void setBaseDAO(BaseDAO<T> baseDao) {
        this.baseDAO = baseDao;
    }

    @Override
    public void insert(T t) {
         baseDAO.insert(t);
    }

    @Override
    public void delete(T t) {
         baseDAO.delete(t);
    }

    @Override
    public void update(T t) {
         baseDAO.update(t);
    }

    @Override
    public T get(Object obj) {
        return baseDAO.get(obj);
    }


}

