package ru.bramblehorse.cms.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.Category;
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
 * Date: 15.10.13
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
public class CategoryAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private AbstractService<Category> categoryService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            processGetNoActionCategory(req, resp);
            return;
        }
        if ("create".equals(action)) {
            processGetCreateCategory(req, resp);
            return;
        }
        if ("edit".equals(action)) {
            processGetEditCategory(req, resp);
            return;
        }
        if ("delete".equals(action)) {
            processGetDeleteCategory(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equals(action)) {
            processPostCreateCategory(req, resp);
            return;
        }
        if ("edit".equals(action)) {
            processPostEditCategory(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    /**
     *
     */

    private void processGetNoActionCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Category> categoryList = categoryService.getAll();
        Collections.sort(categoryList);
        req.setAttribute("categoryList", categoryList);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/categories.jsp");
        rd.forward(req, resp);
    }

    private void processGetCreateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/new_category.jsp");
        rd.forward(req, resp);
    }

    private void processGetEditCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryId = req.getParameter("categoryId");
        req.setAttribute("currentCategory", categoryService.getById(Integer.parseInt(categoryId)));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/edit_category.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryId = req.getParameter("categoryId");
        if (categoryId != null) {
            Integer idToDelete = Integer.parseInt(categoryId);
            categoryService.delete(idToDelete);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    /**
     *
     */

    private void processPostCreateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryName = req.getParameter("title");
        String categoryPosition = req.getParameter("categoryPosition");
        Integer categoryPositionValue;
        try {
            categoryPositionValue = Integer.parseInt(categoryPosition);
        } catch (Exception e) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/new_category.jsp");
            rd.forward(req, resp);
            return;
        }
        Category temp = new Category();
        if (categoryName == null || categoryName.isEmpty()) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/new_category.jsp");
            rd.forward(req, resp);
            return;
        }
        temp.setName(categoryName);
        if (categoryPositionValue != null)
            temp.setCategoryPosition(categoryPositionValue);
        categoryService.create(temp);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostEditCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryName = req.getParameter("title");
        String categoryPosition = req.getParameter("categoryPosition");
        Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Integer categoryPositionValue;
        try {
            categoryPositionValue = Integer.parseInt(categoryPosition);
        } catch (Exception e) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
            rd.forward(req, resp);
            return;
        }

        Category temp = new Category();
        if (categoryName == null || categoryName.isEmpty()) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/new_category.jsp");
            rd.forward(req, resp);
            return;
        }
        temp.setName(categoryName);
        temp.setId(categoryId);
        if (categoryPositionValue != null)
            temp.setCategoryPosition(categoryPositionValue);
        categoryService.edit(temp);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
}
