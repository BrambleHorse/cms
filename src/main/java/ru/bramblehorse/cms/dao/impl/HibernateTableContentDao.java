package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.content.TableContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 21.09.13
 * Time: 2:43
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class HibernateTableContentDao  implements AbstractDao<TableContent> {
    @Autowired
    HibernateTemplate ht;
    @Transactional
    @Override
    public Integer create(TableContent entity) {

       return (Integer)ht.save(entity);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        TableContent temp = ht.load(TableContent.class, id);
        ht.delete(temp);
    }
    @Transactional
    @Override
    public void edit(TableContent entity) {
        ht.saveOrUpdate(entity);
    }
    @Transactional
    @Override
    public TableContent getById(Integer id) {
        return ht.load(TableContent.class,id);
    }
    @Transactional
    @Override
    public List<TableContent> getAll() {
        return ht.loadAll(TableContent.class);
    }
}
