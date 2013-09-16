package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.Category;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 16.09.13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class CategoryService implements AbstractService<Category> {
    AbstractDao<Category> categoryDao;

    public AbstractDao<Category> getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(AbstractDao<Category> categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void create(Category entity) {
        categoryDao.create(entity);
    }

    @Override
    public void delete(Category entity) {
        categoryDao.delete(entity);
    }

    @Override
    public void edit(Category entity) {
       categoryDao.edit(entity);
    }

    @Override
    public Category getById(Integer id) {
        return categoryDao.getById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }
}
