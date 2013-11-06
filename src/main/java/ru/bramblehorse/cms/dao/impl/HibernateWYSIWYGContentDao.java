package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.content.WYSIWYGContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 04.11.13
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class HibernateWYSIWYGContentDao implements AbstractDao<WYSIWYGContent> {
    @Autowired
    HibernateTemplate ht;
    @Transactional
    @Override
    public void create(WYSIWYGContent entity) {
        ht.save(entity);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        WYSIWYGContent temp = ht.load(WYSIWYGContent.class, id);
        ht.delete(temp);
    }
    @Transactional
    @Override
    public void edit(WYSIWYGContent entity) {
        ht.update(entity);
    }
    @Transactional
    @Override
    public WYSIWYGContent getById(Integer id) {
        return ht.load(WYSIWYGContent.class,id);
    }
    @Transactional
    @Override
    public List<WYSIWYGContent> getAll() {
        return ht.loadAll(WYSIWYGContent.class);
    }
}
