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
public class CatalogCategoryFilter implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filter_id")
    private Integer catalogCategoryFilterId;

    @Column(name = "filter_name")
    private String catalogCategoryFilterName;

    @Column(name = "filter_position")
    private Integer catalogCategoryFilterPosition;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "catalogCategoryFilter")
    private List<FilterCriterion> filterCriteria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_category_id")
    private CatalogCategory filterCatalogCategory;

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

    public Integer getCatalogCategoryFilterPosition() {
        return catalogCategoryFilterPosition;
    }

    public void setCatalogCategoryFilterPosition(Integer catalogCategoryFilterPosition) {
        this.catalogCategoryFilterPosition = catalogCategoryFilterPosition;
    }

    public CatalogCategory getFilterCatalogCategory() {
        return filterCatalogCategory;
    }

    public void setFilterCatalogCategory(CatalogCategory filterCatalogCategory) {
        this.filterCatalogCategory = filterCatalogCategory;
    }

    public List<FilterCriterion> getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(List<FilterCriterion> filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null)
            throw new NullPointerException("Passed null to ru.bramblehorse.cms.model.catalog.CatalogCategoryFilter#compareTo(Object)");
        if (!(o instanceof CatalogCategoryFilter))
            throw new ClassCastException("ru.bramblehorse.cms.model.catalog.CatalogCategoryFilter#compareTo(Object)");
        if (((CatalogCategoryFilter) o).getCatalogCategoryFilterPosition() < this.getCatalogCategoryFilterPosition()) return 1;
        if (((CatalogCategoryFilter) o).getCatalogCategoryFilterPosition() > this.getCatalogCategoryFilterPosition()) return -1;
        return 0;
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
