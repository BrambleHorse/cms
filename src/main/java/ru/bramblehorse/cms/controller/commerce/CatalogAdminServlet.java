package ru.bramblehorse.cms.controller.commerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.commerce.*;
import ru.bramblehorse.cms.service.AbstractService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public class CatalogAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;

    private AbstractService<CatalogCategory> catalogCategoryService;
    private AbstractService<CatalogCategoryFilter> catalogCategoryFilterService;
    private AbstractService<FilterCriterion> filterCriterionService;

    private AbstractService<Brand> brandService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(CatalogAdminServlet.class);

        catalogCategoryService = (AbstractService<CatalogCategory>) context.getBean("catalogCategoryService");
        catalogCategoryFilterService =
                (AbstractService<CatalogCategoryFilter>) context.getBean("catalogCategoryFilterService");
        filterCriterionService = (AbstractService<FilterCriterion>) context.getBean("filterCriterionService");

        brandService = (AbstractService<Brand>) context.getBean("brandService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mode = req.getParameter("mode");

        if ("brands".equalsIgnoreCase(mode)) {

            processGetNoActionBrands(req, resp);
            return;
        }
        if ("categories".equalsIgnoreCase(mode)) {

            processGetNoActionCategories(req, resp);
            return;
        }
        if ("filters".equalsIgnoreCase(mode)) {

            processGetNoActionFilters(req, resp);
            return;
        }
        if ("criteria".equalsIgnoreCase(mode)) {

            processGetNoActionFilterCriteria(req, resp);
            return;
        }
        if ("items".equalsIgnoreCase(mode)) {

            processGetNoActionItems(req, resp);
            return;
        }

        processGetNoActionCatalog(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void processGetNoActionCatalog(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        req.setAttribute("adminAction", "admin_commerce");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetNoActionCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        List<CatalogCategory> catalogCategoryList = catalogCategoryService.getAll();
        Collections.sort(catalogCategoryList);
        req.setAttribute("catalogCategoryList", catalogCategoryList);
        req.setAttribute("adminAction", "catalog_categories");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetNoActionFilters(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Integer catalogCategoryId = null;
        try {

            catalogCategoryId = Integer.parseInt(req.getParameter("catalogCategoryId"));

        } catch (Exception e) {

            logger.error(e.getMessage());
        }
        List<CatalogCategoryFilter> catalogCategoryFilterList =
                catalogCategoryService.getById(catalogCategoryId).getCatalogCategoryFilters();
        req.setAttribute("catalogCategoryFilterList", catalogCategoryFilterList);
        req.setAttribute("adminAction", "catalog_category_filters");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetNoActionFilterCriteria(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer catalogCategoryFilterId = null;
        try {

            catalogCategoryFilterId = Integer.parseInt(req.getParameter("catalogCategoryFilterId"));
        } catch (Exception e) {

            logger.error(e.getMessage());
        }
        List<FilterCriterion> filterCriterionList = catalogCategoryFilterService.getById(catalogCategoryFilterId).getFilterCriteria();
        req.setAttribute("filterCriterionList", filterCriterionList);
        req.setAttribute("adminAction", "filter_criteria");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetNoActionItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Integer catalogCategoryId = null;
        try {

            catalogCategoryId = Integer.parseInt(req.getParameter("catalogCategoryId"));
        }  catch (Exception e) {
            logger.error(e.getMessage());
        }
        if(catalogCategoryId != null){

            req.setAttribute("itemList", catalogCategoryService.getById(catalogCategoryId).getCatalogCategoryItems());
        }
        req.setAttribute("adminAction", "items");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetNoActionBrands(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        List<Brand> brandList = brandService.getAll();
        req.setAttribute("brandList", brandList);
        req.setAttribute("adminAction", "brands");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

}
