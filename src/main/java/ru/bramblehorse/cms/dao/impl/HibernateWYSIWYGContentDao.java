package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.content.WysiwygContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 04.11.13
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class HibernateWysiwygContentDao implements AbstractDao<WysiwygContent> {
    @Autowired
    HibernateTemplate ht;
    @Transactional
    @Override
    public void create(WysiwygContent entity) {
        ht.save(entity);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        WysiwygContent temp = ht.load(WysiwygContent.class, id);
        ht.delete(temp);
    }
    @Transactional
    @Override
    public void edit(WysiwygContent entity) {
        ht.update(entity);
    }
    @Transactional
    @Override
    public WysiwygContent getById(Integer id) {
        return ht.load(WysiwygContent.class,id);
    }
    @Transactional
    @Override
    public List<WysiwygContent> getAll() {
        return ht.loadAll(WysiwygContent.class);
    }
}
