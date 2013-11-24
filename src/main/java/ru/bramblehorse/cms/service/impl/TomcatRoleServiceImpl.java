package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.SecurityDao;
import ru.bramblehorse.cms.model.security.TomcatRole;
import ru.bramblehorse.cms.service.SecurityService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 25.11.13
 * Time: 1:15
 * To change this template use File | Settings | File Templates.
 */
public class TomcatRoleServiceImpl implements SecurityService<TomcatRole> {

    SecurityDao<TomcatRole> tomcatRoleDao;

    public SecurityDao<TomcatRole> getTomcatRoleDao() {
        return tomcatRoleDao;
    }

    public void setTomcatRoleDao(SecurityDao<TomcatRole> tomcatRoleDao) {
        this.tomcatRoleDao = tomcatRoleDao;
    }

    @Override
    public void create(TomcatRole entity) {

        tomcatRoleDao.create(entity);
    }

    @Override
    public void delete(String uniqueName) {

        tomcatRoleDao.delete(uniqueName);
    }

    @Override
    public void edit(TomcatRole entity) {

        tomcatRoleDao.edit(entity);
    }

    @Override
    public TomcatRole getByName(String uniqueName) {

        return tomcatRoleDao.getByName(uniqueName);
    }

    @Override
    public List<TomcatRole> getAll() {

        return tomcatRoleDao.getAll();
    }

    @Override
    public TomcatRole getByNameWithRoles(String uniqueName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
