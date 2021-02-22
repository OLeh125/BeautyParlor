package org.fp.beauty.parlor.model.dao;



import java.util.List;

public interface Dao <T>{
    void insert(T t);
    void update(T t);
    void delete(T t);
    List<T> findAll();
}
