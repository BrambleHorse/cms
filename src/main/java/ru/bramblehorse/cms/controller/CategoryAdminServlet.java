package ru.bramblehorse.cms.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.Category;
import ru.bramblehorse.cms.service.CategoryService;

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
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        categoryService = (CategoryService) context.getBean("categoryService");
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

        List<Category> categoryList = categoryService.getRootCategories();
        Collections.sort(categoryList);
        for(Category c : categoryList) {
            c.sortChildren();
        }
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("adminAction","categories");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetCreateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Category> categoryList = categoryService.getRootCategories();
        Collections.sort(categoryList);
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("adminAction","new_category");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetEditCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryId = req.getParameter("categoryId");
        Category currentCategory = categoryService.getById(Integer.parseInt(categoryId));
        if(!currentCategory.hasChildren()) {
        List<Category> categoryList = categoryService.getRootCategories();
        Collections.sort(categoryList);
        req.setAttribute("categoryList", categoryList);
        }
        req.setAttribute("currentCategory", currentCategory);
        Category currentParentCategory = currentCategory.getParentCategory();
        if(currentParentCategory != null){
        req.setAttribute("parentCategoryId", currentCategory.getParentCategory().getId());
        }
        req.setAttribute("adminAction","edit_category");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
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
        String parentCategory = req.getParameter("parentCategory");
        Integer categoryPositionValue;
        Integer categoryParentValue;
        try {
            categoryPositionValue = Integer.parseInt(categoryPosition);
            if("null".equals(parentCategory)){
               categoryParentValue = null;
            }   else {
                categoryParentValue = Integer.parseInt(parentCategory);
            }
        } catch (Exception e) {
            req.setAttribute("adminAction","new_category");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
            rd.forward(req, resp);
            return;
        }
        Category temp = new Category();
        if (categoryName == null || categoryName.isEmpty()) {
            req.setAttribute("adminAction","new_category");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
            rd.forward(req, resp);
            return;
        }
        temp.setName(categoryName);
        temp.setCategoryPosition(categoryPositionValue);
        if(categoryParentValue != null)
            temp.setParentCategory(categoryService.getById(categoryParentValue));
        categoryService.create(temp);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostEditCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryName = req.getParameter("title");
        String categoryPosition = req.getParameter("categoryPosition");
        String parentCategory = req.getParameter("parentCategory");
        Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Integer categoryPositionValue;
        Integer categoryParentValue;
        try {
            categoryPositionValue = Integer.parseInt(categoryPosition);
            if("null".equals(parentCategory)){
                categoryParentValue = null;
            }   else {
                categoryParentValue = Integer.parseInt(parentCategory);
            }
        } catch (Exception e) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
            rd.forward(req, resp);
            return;
        }

        Category temp = new Category();
        if (categoryName == null || categoryName.isEmpty()) {
            req.setAttribute("adminAction","new_category");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
            rd.forward(req, resp);
            return;
        }
        temp.setName(categoryName);
        temp.setId(categoryId);
        temp.setCategoryPosition(categoryPositionValue);
        if(categoryParentValue != null)
            temp.setParentCategory(categoryService.getById(categoryParentValue));
        categoryService.edit(temp);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
}
