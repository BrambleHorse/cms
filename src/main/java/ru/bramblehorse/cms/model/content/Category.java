package ru.bramblehorse.cms.model.content;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 *
 */

@Entity
@Table(name = "categories")
public class Category implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;
    @Column(name = "category_position")
    private Integer categoryPosition;
    @Column(name = "category_name")
    private String name;
    @Column(name = "is_visible")
    private boolean isVisible;
    @ManyToOne
    private Category parentCategory;
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> childCategories;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category")
    private List<Content> content;

    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean hasChildren(){
        if(childCategories == null) return false;
        return childCategories.isEmpty() ? false : true;
    }
    public void sortChildren(){
        if(childCategories != null)
            Collections.sort(childCategories);
    }
    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Category> childCategories) {
        this.childCategories = childCategories;
    }

    public Integer getCategoryPosition() {
        return categoryPosition;
    }

    public void setCategoryPosition(Integer categoryPosition) {
        this.categoryPosition = categoryPosition;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null)
            throw new NullPointerException("Passed null to ru.bramblehorse.cms.model.content.Category#compareTo(Object)");
        if (!(o instanceof Category))
            throw new ClassCastException("ru.bramblehorse.cms.model.content.Category#compareTo(Object)");
        if (((Category) o).getCategoryPosition() < this.getCategoryPosition()) return 1;
        if (((Category) o).getCategoryPosition() > this.getCategoryPosition()) return -1;
        return 0;
    }
}
