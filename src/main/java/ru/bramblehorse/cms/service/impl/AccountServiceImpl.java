package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.dao.SecurityDao;
import ru.bramblehorse.cms.dao.impl.HibernateAccountDao;
import ru.bramblehorse.cms.model.security.Account;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.service.SecurityService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 24.11.13
 * Time: 23:05
 * To change this template use File | Settings | File Templates.
 */
public class AccountServiceImpl implements SecurityService<Account> {

    SecurityDao<Account> accountDao;

    public SecurityDao<Account> getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(SecurityDao<Account> accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void create(Account entity) {
        accountDao.create(entity);
    }

    @Override
    public void delete(String uniqueName) {
        accountDao.delete(uniqueName);
    }

    @Override
    public void edit(Account entity) {
        accountDao.edit(entity);
    }

    @Override
    public Account getByName(String uniqueName) {
        return accountDao.getByName(uniqueName);
    }

    @Override
    public List<Account> getAll() {
        return accountDao.getAll();
    }

    @Override
    public Account getByNameWithRoles(String uniqueName) {

        return accountDao.getByNameWithRoles(uniqueName);
    }
}
