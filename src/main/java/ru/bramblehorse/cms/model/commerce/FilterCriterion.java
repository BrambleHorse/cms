package ru.bramblehorse.cms.model.commerce;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "filter_criteria")
public class FilterCriterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filter_criterion_id")
    private Integer filterCriterionId;

    @Column(name = "filter_criterion_value")
    private String filterCriterionValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_id")
    private CatalogCategoryFilter catalogCategoryFilter;

    @ManyToMany(mappedBy = "filterCriteria")
    private List<Item> items;

    public Integer getFilterCriterionId() {
        return filterCriterionId;
    }

    public void setFilterCriterionId(Integer filterCriterionId) {
        this.filterCriterionId = filterCriterionId;
    }

    public String getFilterCriterionValue() {
        return filterCriterionValue;
    }

    public void setFilterCriterionValue(String filterCriterionValue) {
        this.filterCriterionValue = filterCriterionValue;
    }

    public CatalogCategoryFilter getCatalogCategoryFilter() {
        return catalogCategoryFilter;
    }

    public void setCatalogCategoryFilter(CatalogCategoryFilter catalogCategoryFilter) {
        this.catalogCategoryFilter = catalogCategoryFilter;
    }

    public List<Item> getItems() {

        return items;
    }

    public void setItems(List<Item> items) {

        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterCriterion)) return false;

        FilterCriterion that = (FilterCriterion) o;

        if (!filterCriterionId.equals(that.filterCriterionId)) return false;

        return true;
    }

    @Override
    public int hashCode() {

        return 11 * filterCriterionId.hashCode();
    }
}
