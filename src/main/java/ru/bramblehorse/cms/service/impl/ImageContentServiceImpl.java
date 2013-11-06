package ru.bramblehorse.cms.service.impl;

import ru.bramblehorse.cms.dao.AbstractDao;
import ru.bramblehorse.cms.model.content.ImageContent;
import ru.bramblehorse.cms.service.AbstractService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 17.10.13
 * Time: 1:30
 * To change this template use File | Settings | File Templates.
 */
public class ImageContentServiceImpl implements AbstractService<ImageContent> {
    AbstractDao<ImageContent> imageContentDao;

    public AbstractDao<ImageContent> getImageContentDao() {
        return imageContentDao;
    }

    public void setImageContentDao(AbstractDao<ImageContent> imageContentDao) {
        this.imageContentDao = imageContentDao;
    }

    @Override
    public void create(ImageContent entity) {
        imageContentDao.create(entity);
    }

    @Override
    public void delete(Integer id) {
        imageContentDao.delete(id);
    }

    @Override
    public void edit(ImageContent entity) {
        imageContentDao.edit(entity);
    }

    @Override
    public ImageContent getById(Integer id) {
        return imageContentDao.getById(id);
    }

    @Override
    public List<ImageContent> getAll() {
        return imageContentDao.getAll();
    }
}
