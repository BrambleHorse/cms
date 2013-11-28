package ru.bramblehorse.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.dao.SecurityDao;
import ru.bramblehorse.cms.model.security.Account;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 24.11.13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class HibernateAccountDao implements SecurityDao<Account> {

    @Autowired
    HibernateTemplate ht;

    @Transactional
    @Override
    public void create(Account entity) {
        ht.save(entity);
    }

    @Transactional
    @Override
    public void delete(String uniqueName) {
        Account temp = ht.load(Account.class, uniqueName);
        ht.delete(temp);
    }

    @Transactional
    @Override
    public void edit(Account entity) {
        ht.update(entity);
    }

    @Transactional
    @Override
    public Account getByName(String uniqueName) {
        return ht.load(Account.class, uniqueName);
    }

    @Transactional
    @Override
    public List<Account> getAll() {
        return ht.loadAll(Account.class);
    }

    @Transactional
    @Override
    public Account getByNameWithRoles(String uniqueName) {

        Account temp = ht.load(Account.class,uniqueName);
        ht.initialize(temp.getRoles());
        return temp;
    }
}
