package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.content.TextContent;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 30.08.13
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
public class TextContentServiceImpl implements AbstractService<TextContent> {
    AbstractDao<TextContent> textContentDao;

    public AbstractDao<TextContent> getTextContentDao() {
        return textContentDao;
    }

    public void setTextContentDao(AbstractDao<TextContent> textContentDao) {
        this.textContentDao = textContentDao;
    }

    @Override
    public Integer create(TextContent entity) {
        return textContentDao.create(entity);
    }

    @Override
    public void delete(Integer id) {
        textContentDao.delete(id);
    }

    @Override
    public void edit(TextContent entity) {
        textContentDao.edit(entity);
    }

    @Override
    public TextContent getById(Integer id) {
        return textContentDao.getById(id);
    }

    @Override
    public List<TextContent> getAll() {
        return textContentDao.getAll();
    }
}
