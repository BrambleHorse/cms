package ru.bramblehorse.cms.dao;

import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.Content;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 30.10.13
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryDao extends AbstractDao<Category> {
    public List<Category> getRootCategories();
    public List<Category> getVisibleRootCategories();
}
