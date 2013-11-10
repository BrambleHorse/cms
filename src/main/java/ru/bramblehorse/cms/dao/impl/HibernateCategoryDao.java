package ru.bramblehorse.cms.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.CategoryDao;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.Content;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 16.09.13
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class HibernateCategoryDao implements CategoryDao {
    @Autowired
    HibernateTemplate ht;

    @Transactional
    @Override
    public void create(Category entity) {
        ht.save(entity);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        Category temp = ht.load(Category.class,id);
        ht.delete(temp);
    }
    @Transactional
    @Override
    public void edit(Category entity) {
        ht.update(entity);
    }
    @Transactional
    @Override
    public Category getById(Integer id) {
        return ht.load(Category.class,id);
    }
    @Transactional
    @Override
    public List<Category> getAll() {
        return ht.loadAll(Category.class);
    }
    @Transactional
    @Override
    public List getRootCategories() {
        Criteria criteria = ht.getSessionFactory().getCurrentSession().createCriteria(Category.class);
        criteria.add(Restrictions.isNull("parentCategory"));
        return criteria.list();
    }

    @Override
    public List<Category> getVisibleRootCategories() {
        Criteria criteria = ht.getSessionFactory().getCurrentSession().createCriteria(Category.class);
        criteria.add(Restrictions.isNull("parentCategory"));
        criteria.add(Restrictions.eq("isVisible",true));
        return criteria.list();
    }
}
