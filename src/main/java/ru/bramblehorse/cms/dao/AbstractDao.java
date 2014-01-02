package ru.bramblehorse.cms.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.08.13
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public interface AbstractDao<T> {

    public Integer create(T entity);
    public void delete(Integer id);
    public void edit(T entity);
    public T getById(Integer id);
    public List<T> getAll();
}
