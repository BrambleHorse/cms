package ru.bramblehorse.cms.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public class CatalogAdminServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mode = req.getParameter("mode");
        if ("categories".equalsIgnoreCase(mode)) {

            processGetNoActionCategories(req, resp);
            return;
        }

        if ("filters".equalsIgnoreCase(mode)) {

            processGetNoActionFilters(req, resp);
            return;
        }

        if ("items".equalsIgnoreCase(mode)) {

            processGetNoActionItems(req, resp);
            return;
        }

        if ("brands".equalsIgnoreCase(mode)) {

            processGetNoActionBrands(req, resp);
            return;
        }

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

    private void processGetCreateCatalogCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    private void processGetCreateBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    private void processGetCreateCatalogCategoryFilter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    private void processGetCreateFilterCriterion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    private void processGetCreateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


}
