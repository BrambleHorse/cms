package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.commerce.FilterCriterion;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
public class FilterCriterionServiceImpl implements AbstractService<FilterCriterion> {

    AbstractDao<FilterCriterion> filterCriterionDao;

    public AbstractDao<FilterCriterion> getFilterCriterionDao() {
        return filterCriterionDao;
    }

    public void setFilterCriterionDao(AbstractDao<FilterCriterion> filterCriterionDao) {
        this.filterCriterionDao = filterCriterionDao;
    }

    @Override
    public void create(FilterCriterion entity) {

        filterCriterionDao.create(entity);
    }

    @Override
    public void delete(Integer id) {

        filterCriterionDao.delete(id);
    }

    @Override
    public void edit(FilterCriterion entity) {

        filterCriterionDao.edit(entity);
    }

    @Override
    public FilterCriterion getById(Integer id) {

        return filterCriterionDao.getById(id);
    }

    @Override
    public List<FilterCriterion> getAll() {

        return filterCriterionDao.getAll();
    }
}
