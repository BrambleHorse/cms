package ru.bramblehorse.cms.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.08.13
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "text_content")
@PrimaryKeyJoinColumn(name = "content_id", referencedColumnName = "content_id")
public class TextContent extends Content {
    @Column(name = "text",columnDefinition = "LONGTEXT")
    private String text;
    public TextContent() {
        this.type = ContentType.TEXT;
    }
    @Override
    public ContentType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
