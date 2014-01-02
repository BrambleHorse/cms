package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.commerce.Brand;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.11.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class BrandServiceImpl implements AbstractService<Brand> {

    AbstractDao<Brand> brandDao;

    public AbstractDao<Brand> getBrandDao() {
        return brandDao;
    }

    public void setBrandDao(AbstractDao<Brand> brandDao) {
        this.brandDao = brandDao;
    }

    @Override
    public Integer create(Brand entity) {

        return brandDao.create(entity);
    }

    @Override
    public void delete(Integer id) {

        brandDao.delete(id);
    }

    @Override
    public void edit(Brand entity) {

        brandDao.edit(entity);
    }

    @Override
    public Brand getById(Integer id) {

        return brandDao.getById(id);
    }

    @Override
    public List<Brand> getAll() {

        return brandDao.getAll();
    }
}
