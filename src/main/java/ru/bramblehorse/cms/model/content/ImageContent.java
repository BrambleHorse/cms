package ru.bramblehorse.cms.model.content;

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
    @Column(name="image_name")
    private String imageName;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "thumb_image_path")
    private String thumbImagePath;
    @Column(name = "iamge_file_path")
    private String imageFilePath;
    @Column(name = "thumb_image_file_path")
    private String thumbImageFilePath;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public String getThumbImageFilePath() {
        return thumbImageFilePath;
    }

    public void setThumbImageFilePath(String thumbImageFilePath) {
        this.thumbImageFilePath = thumbImageFilePath;
    }

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
