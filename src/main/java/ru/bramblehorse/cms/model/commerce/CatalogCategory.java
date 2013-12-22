package ru.bramblehorse.cms.model.commerce;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 24.11.13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "catalog_category")
public class CatalogCategory implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_category_id")
    private Integer catalogCategoryId;

    @Column(name = "catalog_category_name")
    private String catalogCategoryName;

    @Column(name = "catalog_category_position")
    private Integer catalogCategoryPosition;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "itemCategory")
    private List<Item> catalogCategoryItems;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "filterCatalogCategory")
    private List<CatalogCategoryFilter> catalogCategoryFilters;

    public Integer getCatalogCategoryId() {
        return catalogCategoryId;
    }

    public void setCatalogCategoryId(Integer catalogCategoryId) {
        this.catalogCategoryId = catalogCategoryId;
    }

    public String getCatalogCategoryName() {
        return catalogCategoryName;
    }

    public void setCatalogCategoryName(String catalogCategoryName) {
        this.catalogCategoryName = catalogCategoryName;
    }

    public List<Item> getCatalogCategoryItems() {
        return catalogCategoryItems;
    }

    public void setCatalogCategoryItems(List<Item> catalogCategoryItems) {
        this.catalogCategoryItems = catalogCategoryItems;
    }

    public List<CatalogCategoryFilter> getCatalogCategoryFilters() {
        return catalogCategoryFilters;
    }

    public void setCatalogCategoryFilters(List<CatalogCategoryFilter> catalogCategoryFilters) {
        this.catalogCategoryFilters = catalogCategoryFilters;
    }

    public Integer getCatalogCategoryPosition() {
        return catalogCategoryPosition;
    }

    public void setCatalogCategoryPosition(Integer catalogCategoryPosition) {
        this.catalogCategoryPosition = catalogCategoryPosition;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null)
            throw new NullPointerException("Passed null to ru.bramblehorse.cms.model.commerce.CatalogCategory#compareTo(Object)");
        if (!(o instanceof CatalogCategory))
            throw new ClassCastException("Passed invalid type to ru.bramblehorse.cms.model.commerce.CatalogCategory#compareTo(Object)");
        if (((CatalogCategory) o).getCatalogCategoryPosition() < this.getCatalogCategoryPosition()) return 1;
        if (((CatalogCategory) o).getCatalogCategoryPosition() > this.getCatalogCategoryPosition()) return -1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CatalogCategory)) return false;

        CatalogCategory category = (CatalogCategory) o;

        if (!catalogCategoryId.equals(category.catalogCategoryId)) return false;

        return true;
    }

    @Override
    public int hashCode() {

        return 7 * catalogCategoryId.hashCode();
    }
}
