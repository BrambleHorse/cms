package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.commerce.CatalogCategoryFilter;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
public class CatalogCategoryFilterServiceImpl implements AbstractService<CatalogCategoryFilter> {

    AbstractDao<CatalogCategoryFilter> catalogCategoryFilterDao;

    public AbstractDao<CatalogCategoryFilter> getCatalogCategoryFilterDao() {
        return catalogCategoryFilterDao;
    }

    public void setCatalogCategoryFilterDao(AbstractDao<CatalogCategoryFilter> catalogCategoryFilterDao) {
        this.catalogCategoryFilterDao = catalogCategoryFilterDao;
    }

    @Override
    public Integer create(CatalogCategoryFilter entity) {

       return catalogCategoryFilterDao.create(entity);
    }

    @Override
    public void delete(Integer id) {

        catalogCategoryFilterDao.delete(id);
    }

    @Override
    public void edit(CatalogCategoryFilter entity) {

        catalogCategoryFilterDao.edit(entity);
    }

    @Override
    public CatalogCategoryFilter getById(Integer id) {

        return catalogCategoryFilterDao.getById(id);
    }

    @Override
    public List<CatalogCategoryFilter> getAll() {

        return catalogCategoryFilterDao.getAll();
    }
}
