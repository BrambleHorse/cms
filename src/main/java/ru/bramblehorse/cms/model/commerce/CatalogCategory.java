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
public class CatalogCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_category_id")
    private Integer catalogCategoryId;

    @Column(name = "catalog_category_name")
    private String catalogCategoryName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "itemCategory")
    private List<Item> catalogCategoryItems;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name="catalog_filters_categories",
    joinColumns = @JoinColumn(name = "catalog_category_id"),
    inverseJoinColumns = @JoinColumn(name="filter_id"))
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
}
