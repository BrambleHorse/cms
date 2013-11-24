package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.dao.SecurityDao;
import ru.bramblehorse.cms.model.security.TomcatRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 24.11.13
 * Time: 23:49
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class HibernateTomcatRoleDao implements SecurityDao<TomcatRole> {

    @Autowired
    HibernateTemplate ht;

    @Transactional
    @Override
    public void create(TomcatRole entity) {
        ht.save(entity);
    }

    @Transactional
    @Override
    public void delete(String uniqueName) {
        TomcatRole temp = ht.load(TomcatRole.class,uniqueName);
        ht.delete(temp);
    }

    @Transactional
    @Override
    public void edit(TomcatRole entity) {
        ht.update(entity);
    }

    @Transactional
    @Override
    public TomcatRole getByName(String uniqueName) {
        return ht.load(TomcatRole.class, uniqueName);
    }

    @Transactional
    @Override
    public List<TomcatRole> getAll() {
        return ht.loadAll(TomcatRole.class);
    }

    @Override
    public TomcatRole getByNameWithRoles(String uniqueName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
