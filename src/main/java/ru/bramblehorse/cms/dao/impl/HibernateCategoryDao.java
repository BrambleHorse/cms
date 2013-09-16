package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.Category;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 16.09.13
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
public class HibernateCategoryDao implements AbstractDao<Category> {
    @Autowired
    HibernateTemplate ht;

    @Override
    public void create(Category entity) {
        ht.save(entity);
    }

    @Override
    public void delete(Category entity) {
        ht.delete(entity);
    }

    @Override
    public void edit(Category entity) {
        ht.saveOrUpdate(entity);
    }

    @Override
    public Category getById(Integer id) {
        return ht.load(Category.class,id);
    }
    @Override
    public List<Category> getAll() {
        return ht.loadAll(Category.class);
    }
}
