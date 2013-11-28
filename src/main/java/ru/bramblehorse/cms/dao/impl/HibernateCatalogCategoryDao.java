package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.commerce.CatalogCategory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 22:23
 * To change this template use File | Settings | File Templates.
 */
public class HibernateCatalogCategoryDao implements AbstractDao<CatalogCategory> {

    @Autowired
    HibernateTemplate ht;

    @Transactional
    @Override
    public void create(CatalogCategory entity) {

        ht.save(entity);
    }

    @Transactional
    @Override
    public void delete(Integer id) {

        CatalogCategory temp = ht.load(CatalogCategory.class,id);
        ht.delete(temp);
    }

    @Transactional
    @Override
    public void edit(CatalogCategory entity) {

        ht.update(entity);
    }

    @Transactional
    @Override
    public CatalogCategory getById(Integer id) {

        return ht.load(CatalogCategory.class, id);
    }

    @Transactional
    @Override
    public List<CatalogCategory> getAll() {

        return ht.loadAll(CatalogCategory.class);
    }
}
