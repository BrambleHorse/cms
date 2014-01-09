package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.dao.ItemDao;
import ru.bramblehorse.cms.model.commerce.Brand;
import ru.bramblehorse.cms.model.commerce.CatalogCategory;
import ru.bramblehorse.cms.model.commerce.FilterCriterion;
import ru.bramblehorse.cms.model.commerce.Item;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.service.ItemService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.11.13
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
public class ItemServiceImpl implements ItemService {

    ItemDao itemDao;

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
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

    @Override
    public List<Item> getItems(int offset, int numberOfRecords, CatalogCategory catalogCategory,
                               List<FilterCriterion> criteria, List<Brand> brands) {

        return itemDao.getItems(offset, numberOfRecords, catalogCategory, criteria, brands);
    }
}
