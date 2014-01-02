package ru.bramblehorse.cms.controller.commerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.commerce.CatalogCategoryFilter;
import ru.bramblehorse.cms.model.commerce.FilterCriterion;
import ru.bramblehorse.cms.service.AbstractService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 10.12.13
 * Time: 13:23
 * To change this template use File | Settings | File Templates.
 */
public class CatalogFilterCriterionAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<FilterCriterion> filterCriterionService;
    private AbstractService<CatalogCategoryFilter> catalogCategoryFilterService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(CatalogFilterCriterionAdminServlet.class);
        filterCriterionService = (AbstractService<FilterCriterion>) context.getBean("filterCriterionService");
        catalogCategoryFilterService = (AbstractService<CatalogCategoryFilter>) context.getBean("catalogCategoryFilterService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if("create".equalsIgnoreCase(action)){

            processGetCreateFilterCriterion(req, resp);
            return;
        }
        if("edit".equalsIgnoreCase(action)){

            processGetEditFilterCriterion(req, resp);
            return;
        }
        if("delete".equalsIgnoreCase(action)){

            processGetDeleteFilterCriterion(req, resp);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if("create".equalsIgnoreCase(action)){

            processPostCreateFilterCriterion(req, resp);
            return;
        }
        if("edit".equalsIgnoreCase(action)){

            processPostEditFilterCriterion(req, resp);
            return;
        }
    }

    private void processGetCreateFilterCriterion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("adminAction", "new_filter_criterion");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processGetEditFilterCriterion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer idToEdit = null;
        try {

            idToEdit = Integer.parseInt(req.getParameter("filterCriterionId"));
        } catch (Exception e){

            logger.error(e.getMessage());
        }
        FilterCriterion filterCriterion = filterCriterionService.getById(idToEdit);
        req.setAttribute("filterCriterion", filterCriterion);
        req.setAttribute("adminAction", "edit_filter_criterion");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteFilterCriterion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer idToDelete = null;
        try {

            idToDelete = Integer.parseInt(req.getParameter("filterCriterionId"));
        }   catch (Exception e) {

            logger.error(e.getMessage());
        }

        if(idToDelete != null) {

            filterCriterionService.delete(idToDelete);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processPostCreateFilterCriterion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String filterCriterionValue = req.getParameter("filterCriterionValue");
        Integer catalogCategoryFilterId = null;
        FilterCriterion filterCriterion = new FilterCriterion();
        try {

           catalogCategoryFilterId = Integer.parseInt(req.getParameter("catalogCategoryFilterId"));
        }   catch (Exception e) {

            logger.error(e.getMessage());
        }
        filterCriterion.setFilterCriterionValue(filterCriterionValue);
        filterCriterion.setCatalogCategoryFilter(catalogCategoryFilterService.getById(catalogCategoryFilterId));
        filterCriterionService.create(filterCriterion);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processPostEditFilterCriterion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String filterCriterionValue = req.getParameter("filterCriterionValue");
        Integer idToEdit = null;
        Integer catalogCategoryFilterId = null;
        try {

            idToEdit = Integer.parseInt(req.getParameter("filterCriterionId"));
            catalogCategoryFilterId = Integer.parseInt(req.getParameter("catalogCategoryFilterId"));
        }   catch (Exception e) {

            logger.error(e.getMessage());
        }
        FilterCriterion filterCriterion = new FilterCriterion();
        filterCriterion.setFilterCriterionId(idToEdit);
        filterCriterion.setFilterCriterionValue(filterCriterionValue);
        filterCriterion.setCatalogCategoryFilter(catalogCategoryFilterService.getById(catalogCategoryFilterId));
        filterCriterionService.edit(filterCriterion);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }
}
