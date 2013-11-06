package ru.bramblehorse.cms.model.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 04.11.13
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "wysiwyg_content")
@PrimaryKeyJoinColumn(name = "content_id", referencedColumnName = "content_id")
public class WYSIWYGContent extends Content {
    @Column(name = "wysiwyg", columnDefinition = "LONGTEXT")
    private String wysiwygData;

    public WYSIWYGContent(){

        this.type = ContentType.WYSIWYG;
    }
    @Override
    public ContentType getType() {
        return type;
    }

    public String getWysiwygData() {
        return wysiwygData;
    }

    public void setWysiwygData(String wysiwygData) {
        this.wysiwygData = wysiwygData;
    }
}
