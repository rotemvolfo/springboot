package com.example.demo.Dao;

public interface IDao<T> {

    T get(String id);
    T save(T t);
    void update(T t);
    void delete(T t);

}
