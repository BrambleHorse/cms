package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.ImageContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 17.10.13
 * Time: 1:18
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class HibernateImageContentDao implements AbstractDao<ImageContent> {
    @Autowired
    HibernateTemplate ht;
    @Transactional
    @Override
    public void create(ImageContent entity) {
        ht.save(entity);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        ImageContent temp = ht.load(ImageContent.class, id);
        ht.delete(temp);
    }
    @Transactional
    @Override
    public void edit(ImageContent entity) {
        ht.update(entity);
    }
    @Transactional
    @Override
    public ImageContent getById(Integer id) {
        return ht.load(ImageContent.class,id);
    }
    @Transactional
    @Override
    public List<ImageContent> getAll() {
        return ht.loadAll(ImageContent.class);
    }
}
