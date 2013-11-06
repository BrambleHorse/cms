package ru.bramblehorse.cms.model.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 06.11.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "link_content")
@PrimaryKeyJoinColumn(name = "content_id", referencedColumnName = "content_id")
public class LinkContent extends Content {

    @Column(name = "path")
    private String linkImagePath;
    @Column(name = "filepath")
    private String linkImageFilePath;
    @Column(name = "link")
    private String linkValue;

    public LinkContent() {
        this.type = ContentType.LINK;
    }

    public String getLinkImagePath() {
        return linkImagePath;
    }

    public void setLinkImagePath(String linkImagePath) {
        this.linkImagePath = linkImagePath;
    }

    public String getLinkValue() {
        return linkValue;
    }

    public void setLinkValue(String linkValue) {
        this.linkValue = linkValue;
    }

    public String getLinkImageFilePath() {
        return linkImageFilePath;
    }

    public void setLinkImageFilePath(String linkImageFilePath) {
        this.linkImageFilePath = linkImageFilePath;
    }
}
