package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.content.TextContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 30.08.13
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */

@Transactional
public class HibernateTextContentDao implements AbstractDao<TextContent> {
    @Autowired
    HibernateTemplate ht;
    @Transactional
    @Override
    public Integer create(TextContent entity) {

        return (Integer)ht.save(entity);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        TextContent temp = ht.load(TextContent.class, id);
        ht.delete(temp);
    }
    @Transactional
    @Override
    public void edit(TextContent entity) {
         ht.update(entity);
    }
    @Transactional
    @Override
    public TextContent getById(Integer id) {
        return ht.load(TextContent.class,id);
    }
    @Transactional
    @Override
    public List<TextContent> getAll() {
        return ht.loadAll(TextContent.class);
    }
}
