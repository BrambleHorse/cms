package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.commerce.FilterCriterion;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public class HibernateFilterCriterionDao implements AbstractDao<FilterCriterion> {

    @Autowired
    HibernateTemplate ht;

    @Transactional
    @Override
    public void create(FilterCriterion entity) {

        ht.save(entity);
    }

    @Transactional
    @Override
    public void delete(Integer id) {

        FilterCriterion temp = ht.load(FilterCriterion.class, id);
        ht.delete(temp);
    }

    @Transactional
    @Override
    public void edit(FilterCriterion entity) {

        ht.update(entity);
    }

    @Transactional
    @Override
    public FilterCriterion getById(Integer id) {

        return ht.load(FilterCriterion.class, id);
    }

    @Transactional
    @Override
    public List<FilterCriterion> getAll() {

        return ht.loadAll(FilterCriterion.class);
    }
}
