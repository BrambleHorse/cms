package ru.bramblehorse.cms.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.08.13
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public interface AbstractDao<T> {

    public T create(T entity);
    public void delete(T entity);
    public T edit (T entity);
    public T getById(Integer id);
    public List<T> getAll();

}
