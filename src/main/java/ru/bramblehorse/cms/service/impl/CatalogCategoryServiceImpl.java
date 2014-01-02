package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.commerce.CatalogCategory;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class CatalogCategoryServiceImpl implements AbstractService<CatalogCategory> {

    AbstractDao<CatalogCategory> catalogCategoryDao;

    public AbstractDao<CatalogCategory> getCatalogCategoryDao() {
        return catalogCategoryDao;
    }

    public void setCatalogCategoryDao(AbstractDao<CatalogCategory> catalogCategoryDao) {
        this.catalogCategoryDao = catalogCategoryDao;
    }

    @Override
    public Integer create(CatalogCategory entity) {

       return catalogCategoryDao.create(entity);
    }

    @Override
    public void delete(Integer id) {

        catalogCategoryDao.delete(id);
    }

    @Override
    public void edit(CatalogCategory entity) {

        catalogCategoryDao.edit(entity);
    }

    @Override
    public CatalogCategory getById(Integer id) {

        return catalogCategoryDao.getById(id);
    }

    @Override
    public List<CatalogCategory> getAll() {

        return catalogCategoryDao.getAll();
    }
}
