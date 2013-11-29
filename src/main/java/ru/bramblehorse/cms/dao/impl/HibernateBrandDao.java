package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
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
public class HibernateBrandDao implements AbstractDao<Brand> {

    @Autowired
    HibernateTemplate ht;

    @Override
    public void create(Brand entity) {

        ht.save(entity);
    }

    @Override
    public void delete(Integer id) {

        Brand temp = ht.load(Brand.class,id);
        ht.delete(temp);
    }

    @Override
    public void edit(Brand entity) {

        ht.update(entity);
    }

    @Override
    public Brand getById(Integer id) {

        return ht.load(Brand.class, id);
    }

    @Override
    public List<Brand> getAll() {

        return ht.loadAll(Brand.class);
    }
}
