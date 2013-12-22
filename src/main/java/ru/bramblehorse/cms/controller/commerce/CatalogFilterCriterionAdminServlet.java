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
        logger = LoggerFactory.getLogger(CatalogCategoryAdminServlet.class);
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


    }

    private void processGetDeleteFilterCriterion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


    }

    private void processPostCreateFilterCriterion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


    }

    private void processPostEditFilterCriterion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


    }
}
