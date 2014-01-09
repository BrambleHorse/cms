package ru.bramblehorse.cms.service;

import ru.bramblehorse.cms.model.commerce.Brand;
import ru.bramblehorse.cms.model.commerce.CatalogCategory;
import ru.bramblehorse.cms.model.commerce.FilterCriterion;
import ru.bramblehorse.cms.model.commerce.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 07.01.14
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public interface ItemService extends AbstractService<Item> {

    public List<Item> getItems(int offset, int numberOfRecords, CatalogCategory catalogCategory,
                               List<FilterCriterion> criteria, List<Brand> brands);
}
