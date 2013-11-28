package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.commerce.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.11.13
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */
public class HibernateItemDao implements AbstractDao<Item> {

    @Autowired
    HibernateTemplate ht;

    @Override
    public void create(Item entity) {

        ht.save(entity);
    }

    @Override
    public void delete(Integer id) {

        Item temp = ht.load(Item.class, id);
        ht.delete(temp);
    }

    @Override
    public void edit(Item entity) {

        ht.update(entity);
    }

    @Override
    public Item getById(Integer id) {

        return ht.load(Item.class, id);
    }

    @Override
    public List<Item> getAll() {

        return ht.loadAll(Item.class);
    }
}
