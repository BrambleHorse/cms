package ru.bramblehorse.cms.controller.commerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.commerce.CatalogCategory;
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
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 */
public class CatalogCategoryAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<CatalogCategory> catalogCategoryService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(CatalogCategoryAdminServlet.class);

        catalogCategoryService = (AbstractService<CatalogCategory>)context.getBean("catalogCategoryService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if("create".equalsIgnoreCase(action)){

            processGetCreateCatalogCategory(req, resp);
            return;
        }
        if("edit".equalsIgnoreCase(action)){

            processGetEditCatalogCategory(req, resp);
            return;
        }
        if("delete".equalsIgnoreCase(action)){

            processGetDeleteCatalogCategory(req, resp);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if("create".equalsIgnoreCase(action)){

            processPostCreateCatalogCategory(req, resp);
            return;
        }
        if("edit".equalsIgnoreCase(action)){

            processPostEditCatalogCategory(req, resp);
            return;
        }

    }

    private void processGetCreateCatalogCategory(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {

        req.setAttribute("adminAction", "new_catalog_category");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetEditCatalogCategory(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {

        String brandId = req.getParameter("catalogCategoryId");
        Integer idToEdit = null;
        try {

            idToEdit = Integer.parseInt(brandId);
        } catch (Exception e){

            logger.error(e.getMessage());
        }
        CatalogCategory catalogCategory = catalogCategoryService.getById(idToEdit);
        req.setAttribute("catalogCategory", catalogCategory);
        req.setAttribute("adminAction", "edit_catalog_category");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteCatalogCategory(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {

        Integer idToDelete = null;
        try {

            idToDelete = Integer.parseInt(req.getParameter("catalogCategoryId"));
        }   catch (Exception e) {

            logger.error(e.getMessage());
        }

        if(idToDelete != null) {

            catalogCategoryService.delete(idToDelete);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processPostCreateCatalogCategory(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {


    }

    private void processPostEditCatalogCategory(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {


    }

}
