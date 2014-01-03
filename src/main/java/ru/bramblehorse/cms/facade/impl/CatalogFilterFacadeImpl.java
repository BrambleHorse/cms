package ru.bramblehorse.cms.facade.impl;

import ru.bramblehorse.cms.facade.CatalogFilterFacade;
import ru.bramblehorse.cms.model.commerce.*;
import ru.bramblehorse.cms.service.AbstractService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 16.12.13
 * Time: 21:49
 * To change this template use File | Settings | File Templates.
 */
public class CatalogFilterFacadeImpl implements CatalogFilterFacade {

    AbstractService<CatalogCategory> catalogCategoryService;
    AbstractService<CatalogCategoryFilter> catalogCategoryFilterService;
    AbstractService<FilterCriterion> filterCriterionService;
    AbstractService<Item> itemService;
    AbstractService<Brand> brandService;

    public AbstractService<CatalogCategory> getCatalogCategoryService() {
        return catalogCategoryService;
    }

    public void setCatalogCategoryService(AbstractService<CatalogCategory> catalogCategoryService) {
        this.catalogCategoryService = catalogCategoryService;
    }

    public AbstractService<CatalogCategoryFilter> getCatalogCategoryFilterService() {
        return catalogCategoryFilterService;
    }

    public void setCatalogCategoryFilterService(AbstractService<CatalogCategoryFilter> catalogCategoryFilterService) {
        this.catalogCategoryFilterService = catalogCategoryFilterService;
    }

    public AbstractService<FilterCriterion> getFilterCriterionService() {
        return filterCriterionService;
    }

    public void setFilterCriterionService(AbstractService<FilterCriterion> filterCriterionService) {
        this.filterCriterionService = filterCriterionService;
    }

    public AbstractService<Item> getItemService() {
        return itemService;
    }

    public void setItemService(AbstractService<Item> itemService) {
        this.itemService = itemService;
    }

    public AbstractService<Brand> getBrandService() {
        return brandService;
    }

    public void setBrandService(AbstractService<Brand> brandService) {
        this.brandService = brandService;
    }

    @Override
    public List<Item> getItemsList(HttpServletRequest req, CatalogCategory category)
            throws ServletException, IOException {

        boolean isAllFilterCriteriaUnchecked = true;
        if (category == null) {

            return null;
        }
        List<Brand> brandList = brandService.getAll();
        List<CatalogCategoryFilter> filterList = category.getCatalogCategoryFilters();
        List<Item> resultItemList = category.getCatalogCategoryItems();
        if (filterList == null) {

            return resultItemList;

        } else {

            for (CatalogCategoryFilter filter : filterList) {
                List<Item> filterRelatedItemList = new ArrayList<Item>();
                List<FilterCriterion> criteria = filter.getFilterCriteria();
                for (FilterCriterion criterion : criteria) {

                    if ("checked".equalsIgnoreCase(req.getParameter(criterion.getFilterCriterionValue()))) {

                        filterRelatedItemList.addAll(criterion.getItems());
                        isAllFilterCriteriaUnchecked = false;
                        req.setAttribute("criterion" + criterion.getFilterCriterionId(), true);
                    }
                }
                if(!isAllFilterCriteriaUnchecked){

                    resultItemList.retainAll(filterRelatedItemList);
                    isAllFilterCriteriaUnchecked = true;
                }
            }
        }
        if (brandList != null) {

            for (Brand brand : brandList) {

                if ("checked".equalsIgnoreCase(req.getParameter(brand.getBrandName()))) {


                    req.setAttribute("brand" + brand.getBrandId(), true);
                }
            }
        }

        return resultItemList;
    }
}
