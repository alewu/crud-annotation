package ${packageName}.dao;


/**
 * @author ${author}
 * @date ${date}
 * @description 基础接口
 */
public interface BaseDAO<T> {

    int insert(T t);

    int delete(String id);

    int update(T t);

    T get(Object obj);



}