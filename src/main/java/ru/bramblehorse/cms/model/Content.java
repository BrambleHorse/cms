package ru.bramblehorse.cms.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 20.09.13
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "content")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Integer contentId;
    @Column(name = "content_type")
    protected ContentType type;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public ContentType getType() {
        return ContentType.NONE;
    }

    protected void setType(ContentType type) {
        this.type = type;
    }
}
