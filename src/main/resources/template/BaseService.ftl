package ${packageName}.service;

/**
 * @author ${author}
 * @date ${date}
 * @description 基础接口
 */
public interface BaseService<T> {

    int insert(T t);

    int delete(String id);

    int update(T t);

    T get(Object obj);



}