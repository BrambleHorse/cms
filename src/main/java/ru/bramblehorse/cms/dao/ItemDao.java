package ru.bramblehorse.cms.dao;

import ru.bramblehorse.cms.model.commerce.Brand;
import ru.bramblehorse.cms.model.commerce.CatalogCategory;
import ru.bramblehorse.cms.model.commerce.FilterCriterion;
import ru.bramblehorse.cms.model.commerce.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 05.01.14
 * Time: 23:59
 * To change this template use File | Settings | File Templates.
 */
public interface ItemDao extends AbstractDao<Item> {

    public List<Item> getItems(int offset, int numberOfRecords,CatalogCategory catalogCategory,
                               List<FilterCriterion> criteria, List<Brand> brands);
}
