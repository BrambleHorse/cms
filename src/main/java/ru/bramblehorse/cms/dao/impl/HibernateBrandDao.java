package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.commerce.Brand;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.11.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class HibernateBrandDao implements AbstractDao<Brand> {

    @Autowired
    HibernateTemplate ht;

    @Transactional
    @Override
    public Integer create(Brand entity) {

        return  (Integer)ht.save(entity);
    }

    @Transactional
    @Override
    public void delete(Integer id) {

        Brand temp = ht.load(Brand.class,id);
        ht.delete(temp);
    }

    @Transactional
    @Override
    public void edit(Brand entity) {

        ht.update(entity);
    }

    @Transactional
    @Override
    public Brand getById(Integer id) {

        return ht.load(Brand.class, id);
    }

    @Transactional
    @Override
    public List<Brand> getAll() {

        return ht.loadAll(Brand.class);
    }
}
