package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.TableContent;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 21.09.13
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public class TableContentServiceImpl implements AbstractService<TableContent> {
    AbstractDao<TableContent> tableContentDao;

    public AbstractDao<TableContent> getTableContentDao() {
        return tableContentDao;
    }

    public void setTableContentDao(AbstractDao<TableContent> tableContentDao) {
        this.tableContentDao = tableContentDao;
    }

    @Override
    public void create(TableContent entity) {
        tableContentDao.create(entity);
    }

    @Override
    public void delete(Integer id) {
        tableContentDao.delete(id);
    }

    @Override
    public void edit(TableContent entity) {
        tableContentDao.edit(entity);
    }

    @Override
    public TableContent getById(Integer id) {
        return tableContentDao.getById(id);
    }

    @Override
    public List<TableContent> getAll() {
        return tableContentDao.getAll();
    }
}
