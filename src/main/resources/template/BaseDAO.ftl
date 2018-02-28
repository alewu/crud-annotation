package ${packageName}.dao;


/**
 * @author ${author}
 * @date ${date}
 * @description 基础接口
 */
public interface BaseDAO<T> {

    int saveOne(T t);

    int removeOne(String id);

    int updateOne(T t);

    T getOne(Object obj);



}