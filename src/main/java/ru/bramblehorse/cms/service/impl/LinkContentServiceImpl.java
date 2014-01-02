package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.content.LinkContent;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 06.11.13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class LinkContentServiceImpl implements AbstractService<LinkContent> {

    AbstractDao<LinkContent> linkContentDao;

    public AbstractDao<LinkContent> getLinkContentDao() {
        return linkContentDao;
    }

    public void setLinkContentDao(AbstractDao<LinkContent> linkContentDao) {
        this.linkContentDao = linkContentDao;
    }

    @Override
    public Integer create(LinkContent entity) {
       return linkContentDao.create(entity);
    }

    @Override
    public void delete(Integer id) {
        linkContentDao.delete(id);
    }

    @Override
    public void edit(LinkContent entity) {
       linkContentDao.edit(entity);
    }

    @Override
    public LinkContent getById(Integer id) {
        return linkContentDao.getById(id);
    }

    @Override
    public List<LinkContent> getAll() {
        return linkContentDao.getAll();
    }
}
