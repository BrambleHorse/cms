package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.WYSIWYGContent;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 04.11.13
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class WYSIWYGContentServiceImpl implements AbstractService<WYSIWYGContent> {

    AbstractDao<WYSIWYGContent> wysiwygContentDao;

    public AbstractDao<WYSIWYGContent> getWysiwygContentDao() {
        return wysiwygContentDao;
    }

    public void setWysiwygContentDao(AbstractDao<WYSIWYGContent> wysiwygContentDao) {
        this.wysiwygContentDao = wysiwygContentDao;
    }

    @Override
    public void create(WYSIWYGContent entity) {
        wysiwygContentDao.create(entity);
    }

    @Override
    public void delete(Integer id) {
        wysiwygContentDao.delete(id);
    }

    @Override
    public void edit(WYSIWYGContent entity) {
        wysiwygContentDao.edit(entity);
    }

    @Override
    public WYSIWYGContent getById(Integer id) {
        return wysiwygContentDao.getById(id);
    }

    @Override
    public List<WYSIWYGContent> getAll() {
        return wysiwygContentDao.getAll();
    }
}
