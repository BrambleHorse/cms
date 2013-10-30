package ru.bramblehorse.cms.service;

import ru.bramblehorse.cms.model.Category;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 30.10.13
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryService extends AbstractService<Category> {
    public List<Category> getRootCategories();
}
