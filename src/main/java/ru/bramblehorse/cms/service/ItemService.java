package ru.bramblehorse.cms.service;

import ru.bramblehorse.cms.model.commerce.CatalogCategory;
import ru.bramblehorse.cms.model.commerce.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 03.12.13
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public interface ItemService extends AbstractService<Item> {

    public List<Item> getAllCatalogCategoryItems(CatalogCategory catalogCategory);
}
