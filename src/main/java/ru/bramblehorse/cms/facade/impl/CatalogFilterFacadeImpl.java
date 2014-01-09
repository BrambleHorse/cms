package ru.bramblehorse.cms.facade.impl;

import ru.bramblehorse.cms.facade.CatalogFilterFacade;
import ru.bramblehorse.cms.model.commerce.*;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    ItemService itemService;
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

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public AbstractService<Brand> getBrandService() {
        return brandService;
    }

    public void setBrandService(AbstractService<Brand> brandService) {
        this.brandService = brandService;
    }

    @Override
    public List<Item> processItemsList(HttpServletRequest req, HttpServletResponse resp, Integer offset,
                                       Integer numberOfRecords, CatalogCategory category)
            throws ServletException, IOException {

        if (category == null) {

            return null;
        }
        List<Brand> brandList = new ArrayList<Brand>();
        List<CatalogCategoryFilter> filterList = category.getCatalogCategoryFilters();
        List<FilterCriterion> checkedCriteriaList = new ArrayList<FilterCriterion>();
        for (Item item : category.getCatalogCategoryItems()) {

            if (!brandList.contains(item.getItemBrand()))
                brandList.add(item.getItemBrand());

        }
        if (filterList == null && brandList.isEmpty()) {

            return category.getCatalogCategoryItems();

        } else {

            for (CatalogCategoryFilter filter : filterList) {

                for (FilterCriterion criterion : filter.getFilterCriteria()) {

                    if ("checked".equalsIgnoreCase(req.getParameter(criterion.getFilterCriterionValue()))) {

                        checkedCriteriaList.add(criterion);
                        req.setAttribute("criterion" + criterion.getFilterCriterionId(), true);
                    }
                }
            }
        }

        List<Brand> checkedBrandList = new ArrayList<Brand>();
        for (Brand brand : brandList) {

            if ("checked".equalsIgnoreCase(req.getParameter(brand.getBrandName()))) {

                checkedBrandList.add(brand);
                req.setAttribute("brand" + brand.getBrandId(), true);
            }
        }

        req.setAttribute("brandList", brandList);
        return itemService.getItems(offset, numberOfRecords, category, checkedCriteriaList, checkedBrandList);

    }
}
