package ru.bramblehorse.cms.controller.commerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.controller.core.IndexServlet;
import ru.bramblehorse.cms.model.commerce.Brand;
import ru.bramblehorse.cms.model.commerce.CatalogCategory;
import ru.bramblehorse.cms.model.commerce.CatalogCategoryFilter;
import ru.bramblehorse.cms.model.commerce.FilterCriterion;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.service.ItemService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private ItemService itemService;
    private AbstractService<Brand> brandService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(CatalogAdminServlet.class);

        catalogCategoryService = (AbstractService<CatalogCategory>) context.getBean("catalogCategoryService");
        catalogCategoryFilterService = (AbstractService<CatalogCategoryFilter>) context.getBean("catalogCategoryFilterService");
        filterCriterionService = (AbstractService<FilterCriterion>) context.getBean("filterCriterionService");
        itemService = (ItemService) context.getBean("itemService");
        brandService = (AbstractService<Brand>) context.getBean("brandService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mode = req.getParameter("mode");
        String action = req.getParameter("action");

        processGetNoActionCatalog(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void processGetNoActionCatalog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("adminAction", "admin_commerce");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetNoActionCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CatalogCategory> catalogCategoryList = catalogCategoryService.getAll();

        req.setAttribute("catalogCategoryList", catalogCategoryList);
        req.setAttribute("adminAction", "catalog_categories");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetNoActionFilters(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("adminAction", "catalog_filters");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetNoActionItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("adminAction", "catalog_items");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetNoActionBrands(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("adminAction", "catalog_brands");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }







}
