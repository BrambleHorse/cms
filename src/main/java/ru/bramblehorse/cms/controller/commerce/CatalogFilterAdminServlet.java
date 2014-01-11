package ru.bramblehorse.cms.controller.commerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.commerce.CatalogCategory;
import ru.bramblehorse.cms.model.commerce.CatalogCategoryFilter;
import ru.bramblehorse.cms.model.commerce.FilterCriterion;
import ru.bramblehorse.cms.service.AbstractService;

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
 * Date: 10.12.13
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class CatalogFilterAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<CatalogCategoryFilter> catalogCategoryFilterService;
    private AbstractService<CatalogCategory> catalogCategoryService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(CatalogFilterAdminServlet.class);
        catalogCategoryFilterService = (AbstractService<CatalogCategoryFilter>) context.getBean("catalogCategoryFilterService");
        catalogCategoryService = (AbstractService<CatalogCategory>)context.getBean("catalogCategoryService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if("create".equalsIgnoreCase(action)){

            processGetCreateFilter(req, resp);
            return;
        }
        if("edit".equalsIgnoreCase(action)){

            processGetEditFilter(req, resp);
            return;
        }
        if("delete".equalsIgnoreCase(action)){

            processGetDeleteFilter(req, resp);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if("create".equalsIgnoreCase(action)){

            processPostCreateFilter(req, resp);
            return;
        }
        if("edit".equalsIgnoreCase(action)){

            processPostEditFilter(req, resp);
            return;
        }
    }

    private void processGetCreateFilter(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        req.setAttribute("adminAction", "new_catalog_category_filter");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetEditFilter(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String catalogCategoryFilterId = req.getParameter("catalogCategoryFilterId");
        Integer idToEdit = null;
        try {

            idToEdit = Integer.parseInt(catalogCategoryFilterId);
        } catch (Exception e){

            logger.error(e.getMessage());
        }
        CatalogCategoryFilter catalogCategoryFilter = catalogCategoryFilterService.getById(idToEdit);
        req.setAttribute("catalogCategoryList", catalogCategoryService.getAll());
        req.setAttribute("catalogCategoryFilter", catalogCategoryFilter);
        req.setAttribute("adminAction", "edit_catalog_category_filter");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteFilter(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Integer idToDelete = null;
        try {

            idToDelete = Integer.parseInt(req.getParameter("catalogCategoryFilterId"));
        }   catch (Exception e) {

            logger.error(e.getMessage());
        }

        if(idToDelete != null) {

            catalogCategoryFilterService.delete(idToDelete);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostCreateFilter(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String catalogCategoryFilterName = req.getParameter("catalogCategoryFilterName");
        Integer catalogCategoryFilterPosition = null;
        Integer catalogCategoryId = null;
        CatalogCategoryFilter catalogCategoryFilter = new CatalogCategoryFilter();
        try {

            catalogCategoryFilterPosition = Integer.parseInt(req.getParameter("catalogCategoryFilterPosition"));
            catalogCategoryId = Integer.parseInt(req.getParameter("catalogCategoryId"));
        }   catch (Exception e) {

            logger.error(e.getMessage());
        }
        catalogCategoryFilter.setCatalogCategoryFilterName(catalogCategoryFilterName);
        catalogCategoryFilter.setCatalogCategoryFilterPosition(catalogCategoryFilterPosition);
        catalogCategoryFilter.setFilterCatalogCategory(catalogCategoryService.getById(catalogCategoryId));
        catalogCategoryFilterService.create(catalogCategoryFilter);
        resp.sendRedirect("/admin.catalog.do?mode=filters&catalogCategoryId=" + catalogCategoryId);

    }

    private void processPostEditFilter(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String catalogCategoryFilterName = req.getParameter("catalogCategoryFilterName");
        Integer idToEdit = null;
        Integer catalogCategoryFilterPosition = null;
        Integer catalogCategoryId = null;
        try {

            idToEdit = Integer.parseInt(req.getParameter("catalogCategoryFilterId"));
            catalogCategoryFilterPosition = Integer.parseInt(req.getParameter("catalogCategoryFilterPosition"));
            catalogCategoryId = Integer.parseInt(req.getParameter("catalogCategoryId"));
        }   catch (Exception e) {

            logger.error(e.getMessage());
        }
        CatalogCategoryFilter catalogCategoryFilter = new CatalogCategoryFilter();
        catalogCategoryFilter.setCatalogCategoryFilterId(idToEdit);
        catalogCategoryFilter.setCatalogCategoryFilterName(catalogCategoryFilterName);
        catalogCategoryFilter.setCatalogCategoryFilterPosition(catalogCategoryFilterPosition);
        catalogCategoryFilter.setFilterCatalogCategory(catalogCategoryService.getById(catalogCategoryId));
        catalogCategoryFilterService.edit(catalogCategoryFilter);
        resp.sendRedirect("/admin.catalog.do?mode=filters&catalogCategoryId=" + catalogCategoryId);
    }
}
