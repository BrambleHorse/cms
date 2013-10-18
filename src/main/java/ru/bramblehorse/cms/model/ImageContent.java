package ru.bramblehorse.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 20.09.13
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "image_content")
@PrimaryKeyJoinColumn(name = "content_id", referencedColumnName = "content_id")
public class ImageContent extends Content {
    @Column(name = "path")
    private String imagePath;
    @Column(name = "thumbpath")
    private String thumbImagePath;

    public String getThumbImagePath() {
        return thumbImagePath;
    }

    public void setThumbImagePath(String thumbImagePath) {
        this.thumbImagePath = thumbImagePath;
    }

    public ImageContent() {
        this.type = ContentType.IMAGE;
    }
    @Override
    public ContentType getType() {
        return type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
