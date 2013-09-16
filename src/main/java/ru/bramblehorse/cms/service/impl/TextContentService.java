package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.TextContent;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 30.08.13
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
public class TextContentService implements AbstractService<TextContent> {
    AbstractDao<TextContent> textContentDao;

    public AbstractDao<TextContent> getTextContentDao() {
        return textContentDao;
    }

    public void setTextContentDao(AbstractDao<TextContent> textContentDao) {
        this.textContentDao = textContentDao;
    }

    @Override
    public void create(TextContent entity) {

    }

    @Override
    public void delete(TextContent entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void edit(TextContent entity) {

    }

    @Override
    public TextContent getById(Integer id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TextContent> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
