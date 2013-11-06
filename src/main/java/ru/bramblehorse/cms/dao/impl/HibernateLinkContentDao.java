package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.content.LinkContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 06.11.13
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class HibernateLinkContentDao implements AbstractDao<LinkContent> {

    @Autowired
    HibernateTemplate ht;
    @Transactional
    @Override
    public void create(LinkContent entity) {
        ht.save(entity);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        LinkContent temp = ht.load(LinkContent.class, id);
        ht.delete(temp);
    }
    @Transactional
    @Override
    public void edit(LinkContent entity) {
       ht.update(entity);
    }
    @Transactional
    @Override
    public LinkContent getById(Integer id) {
        return ht.load(LinkContent.class, id);
    }
    @Transactional
    @Override
    public List<LinkContent> getAll() {
        return ht.loadAll(LinkContent.class);
    }
}
