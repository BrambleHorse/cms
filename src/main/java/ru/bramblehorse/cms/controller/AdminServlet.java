package ru.bramblehorse.cms.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.Category;
import ru.bramblehorse.cms.model.Content;
import ru.bramblehorse.cms.model.TableContent;
import ru.bramblehorse.cms.model.TextContent;
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
 * Date: 29.08.13
 * Time: 23:52
 * To change this template use File | Settings | File Templates.
 */
public class AdminServlet extends HttpServlet {
    private WebApplicationContext context;
    private AbstractService<Category> categoryService;
    private AbstractService<TableContent> tableContentService;
    private AbstractService<TextContent> textContentService;

    @Override
    public void init() throws ServletException {
        context = ContextLoaderListener.getCurrentWebApplicationContext();
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        tableContentService = (AbstractService<TableContent>) context.getBean("tableContentService");
        textContentService = (AbstractService<TextContent>) context.getBean("textContentService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        String action = req.getParameter("action");
        if ("category".equals(mode)) {
            if (action == null) {
                List<Category> categoryList = categoryService.getAll();
                req.setAttribute("categoryList", categoryList);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/categories.jsp");
                rd.forward(req, resp);
                return;
            }
            if ("create".equals(action)) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/new_category.jsp");
                rd.forward(req, resp);
                return;
            }
            if ("edit".equals(action)) {
                String categoryId = req.getParameter("categoryId");
                req.setAttribute("currentCategory",categoryService.getById(Integer.parseInt(categoryId)));
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/edit_category.jsp");
                rd.forward(req, resp);
                return;
            }
            if ("delete".equals(action)) {
                String categoryId = req.getParameter("categoryId");
                if (categoryId != null) {
                    Integer idToDelete = Integer.parseInt(categoryId);
                    categoryService.delete(idToDelete);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                rd.forward(req, resp);
                return;
            }
            if ("content".equals(mode)) {
               if(action == null) {
                   String categoryId = req.getParameter("categoryId");
                   List<Content> contentList = categoryService.getById(Integer.parseInt(categoryId)).getContent();
                   Collections.sort(contentList);
                   req.setAttribute("contentList", contentList);

               }

            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        String action = req.getParameter("action");
        if ("categories".equals(mode)) {
            if ("create".equals(action)) {
                String categoryName = req.getParameter("title");
                String categoryPosition = req.getParameter("category_position");
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
                return;
            }
            if ("edit".equals(action)) {
                String categoryName = req.getParameter("title");
                String categoryPosition = req.getParameter("category_position");
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
                return;
            }
        }
        if ("content".equals(mode)) {

        }
    }
}
