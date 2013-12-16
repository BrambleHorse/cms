package ru.bramblehorse.cms.controller.commerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.commerce.Brand;
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
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class CatalogBrandAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<Brand> brandService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(CatalogBrandAdminServlet.class);

        brandService = (AbstractService<Brand>)context.getBean("brandService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if("create".equalsIgnoreCase(action)){

            processGetCreateBrand(req, resp);
            return;
        }
        if("edit".equalsIgnoreCase(action)){

            processGetEditBrand(req, resp);
            return;
        }
        if("delete".equalsIgnoreCase(action)){

            processGetDeleteBrand(req, resp);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if("create".equalsIgnoreCase(action)){

            processPostCreateBrand(req, resp);
            return;
        }

        if("edit".equalsIgnoreCase(action)){

           processPostEditBrand(req, resp);
        }

    }

    private void processGetCreateBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        req.setAttribute("adminAction", "new_brand");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetEditBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {


    }

    private void processGetDeleteBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {


    }

    private void processPostCreateBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {


    }

    private void processPostEditBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {


    }

}
