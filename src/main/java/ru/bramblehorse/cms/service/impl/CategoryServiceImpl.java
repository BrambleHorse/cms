package ru.bramblehorse.cms.service.impl;


import ru.bramblehorse.cms.dao.CategoryDao;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.service.CategoryService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 16.09.13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao;

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void create(Category entity) {
        categoryDao.create(entity);
    }

    @Override
    public void delete(Integer id) {
        categoryDao.delete(id);
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

    @Override
    public List<Category> getRootCategories() {
        return categoryDao.getRootCategories();
    }

    @Override
    public List<Category> getVisibleRootCategories() {
        return categoryDao.getVisibleRootCategories();
    }
}
