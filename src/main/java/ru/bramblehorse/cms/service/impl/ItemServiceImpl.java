package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.commerce.Item;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.11.13
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
public class ItemServiceImpl implements AbstractService<Item> {

    AbstractDao<Item> itemDao;

    public AbstractDao<Item> getItemDao() {
        return itemDao;
    }

    public void setItemDao(AbstractDao<Item> itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public Integer create(Item entity) {

        return itemDao.create(entity);
    }

    @Override
    public void delete(Integer id) {

        itemDao.delete(id);
    }

    @Override
    public void edit(Item entity) {

        itemDao.edit(entity);
    }

    @Override
    public Item getById(Integer id) {

        return itemDao.getById(id);
    }

    @Override
    public List<Item> getAll() {

        return itemDao.getAll();
    }
}
