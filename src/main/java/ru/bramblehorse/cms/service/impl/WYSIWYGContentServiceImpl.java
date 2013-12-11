package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.content.WysiwygContent;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 04.11.13
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class WysiwygContentServiceImpl implements AbstractService<WysiwygContent> {

    AbstractDao<WysiwygContent> wysiwygContentDao;

    public AbstractDao<WysiwygContent> getWysiwygContentDao() {
        return wysiwygContentDao;
    }

    public void setWysiwygContentDao(AbstractDao<WysiwygContent> wysiwygContentDao) {
        this.wysiwygContentDao = wysiwygContentDao;
    }

    @Override
    public void create(WysiwygContent entity) {
        wysiwygContentDao.create(entity);
    }

    @Override
    public void delete(Integer id) {
        wysiwygContentDao.delete(id);
    }

    @Override
    public void edit(WysiwygContent entity) {
        wysiwygContentDao.edit(entity);
    }

    @Override
    public WysiwygContent getById(Integer id) {
        return wysiwygContentDao.getById(id);
    }

    @Override
    public List<WysiwygContent> getAll() {
        return wysiwygContentDao.getAll();
    }
}
