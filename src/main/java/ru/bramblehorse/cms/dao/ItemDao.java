package ru.bramblehorse.cms.dao;

import ru.bramblehorse.cms.model.commerce.CatalogCategory;
import ru.bramblehorse.cms.model.commerce.CatalogCategoryFilter;
import ru.bramblehorse.cms.model.commerce.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public interface ItemDao extends AbstractDao<Item> {

    public List<Item> getAllCatalogCategoryItems(CatalogCategory catalogCategory, List<CatalogCategoryFilter> filters);

}
