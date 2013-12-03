package ru.bramblehorse.cms.model.commerce;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "catalog_category_filters")
public class CatalogCategoryFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filter_id")
    private Integer catalogCategoryFilterId;

    @Column(name = "filter_name")
    private String catalogCategoryFilterName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "catalogCategoryFilter")
    private List<FilterCriterion> filterCriterions;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name="catalog_filters_categories",
            joinColumns = @JoinColumn(name = "filter_id"),
            inverseJoinColumns = @JoinColumn(name="catalog_category_id"))
    private List<CatalogCategory> catalogCategories;

    public Integer getCatalogCategoryFilterId() {
        return catalogCategoryFilterId;
    }

    public void setCatalogCategoryFilterId(Integer catalogCategoryFilterId) {
        this.catalogCategoryFilterId = catalogCategoryFilterId;
    }

    public String getCatalogCategoryFilterName() {
        return catalogCategoryFilterName;
    }

    public void setCatalogCategoryFilterName(String catalogCategoryFilterName) {
        this.catalogCategoryFilterName = catalogCategoryFilterName;
    }

    public List<CatalogCategory> getCatalogCategories() {
        return catalogCategories;
    }

    public void setCatalogCategories(List<CatalogCategory> catalogCategories) {
        this.catalogCategories = catalogCategories;
    }

    public List<FilterCriterion> getFilterCriterions() {
        return filterCriterions;
    }

    public void setFilterCriterions(List<FilterCriterion> filterCriterions) {
        this.filterCriterions = filterCriterions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CatalogCategoryFilter)) return false;

        CatalogCategoryFilter filter = (CatalogCategoryFilter) o;

        if (!catalogCategoryFilterId.equals(filter.catalogCategoryFilterId)) return false;

        return true;
    }

    @Override
    public int hashCode() {

        return 13 * catalogCategoryFilterId.hashCode();
    }
}
