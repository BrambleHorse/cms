package ru.bramblehorse.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 21.09.13
 * Time: 1:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "sheet_content")
@PrimaryKeyJoinColumn(name = "content_id", referencedColumnName = "content_id")
public class TableContent extends Content {
    @Column(name = "sheet")
    private String htmlTable;

    public TableContent() {
        this.type = ContentType.TABLE;
    }

    public String getHtmlTable() {
        return htmlTable;
    }

    public void setHtmlTable(String htmlTable) {
        this.htmlTable = htmlTable;
    }
}
