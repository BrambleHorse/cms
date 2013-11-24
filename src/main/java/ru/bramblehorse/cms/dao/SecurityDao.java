package ru.bramblehorse.cms.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 24.11.13
 * Time: 23:59
 * To change this template use File | Settings | File Templates.
 */
public interface SecurityDao<T> {

    public void create(T entity);
    public void delete(String uniqueName);
    public void edit(T entity);
    public T getByName(String uniqueName);
    public T getByNameWithRoles(String uniqueName);
    public List<T> getAll();

}
