package ru.bramblehorse.cms.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.*;
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
               processGetNoActionCategory(req,resp);
               return;
            }
            if ("create".equals(action)) {
                processGetCreateCategory(req,resp);
                return;
            }
            if ("edit".equals(action)) {
                processGetEditCategory(req,resp);
                return;
            }
            if ("delete".equals(action)) {
                processGetDeleteCategory(req,resp);
                return;
            }
        }
        if ("content".equals(mode)) {
            if(action == null) {
                processGetNoActionContent(req,resp);
                return;
            }
            if("create".equals(action)){
                processGetCreateContent(req,resp);
                return;
            }
            if("edit".equals(action)){
                processGetEditContent(req,resp);
                return;
            }
            if("delete".equals(action)) {
                processGetDeleteContent(req, resp);
                return;
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
            if("create".equals(action)){

            }
            if("edit".equals(action)){

            }

        }
    }
    /**
     *
     */
    private void processGetNoActionCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAll();
        req.setAttribute("categoryList", categoryList);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/categories.jsp");
        rd.forward(req, resp);
    }
    private void processGetCreateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/new_category.jsp");
        rd.forward(req, resp);
    }
    private void processGetEditCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        String categoryId = req.getParameter("categoryId");
        req.setAttribute("currentCategory",categoryService.getById(Integer.parseInt(categoryId)));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/edit_category.jsp");
        rd.forward(req, resp);
    }
    private void processGetDeleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        String categoryId = req.getParameter("categoryId");
        if (categoryId != null) {
            Integer idToDelete = Integer.parseInt(categoryId);
            categoryService.delete(idToDelete);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
    private void processGetNoActionContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        List<Content> contentList = categoryService.getById(Integer.parseInt(categoryId)).getContent();
        Collections.sort(contentList);
        req.setAttribute("contentList", contentList);
        req.setAttribute("categoryId", categoryId);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/content.jsp");
        rd.forward(req, resp);
    }
    private void processGetCreateContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/new_content.jsp");
        rd.forward(req, resp);
    }
    private void processGetEditContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contentId = req.getParameter("contentId");
        ContentType contentType = ContentType.getType(req.getParameter("type"));
        switch (contentType) {
            case TABLE:
                TableContent tempTable = tableContentService.getById(Integer.parseInt(contentId));
                req.setAttribute("content", tempTable);
                break;
            case TEXT:
                TextContent tempText = textContentService.getById(Integer.parseInt(contentId));
                req.setAttribute("content", tempText);
                break;
            default:
                break;
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/edit_content.jsp");
        rd.forward(req, resp);
    }
    private void processGetDeleteContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        String contentId = req.getParameter("contentId");
        String contentType = req.getParameter("contentType");
        if (categoryId != null && contentId != null && contentType != null) {
            Integer idToDelete = Integer.parseInt(contentId);
            switch (ContentType.getType(contentType)) {
                case TABLE:
                    tableContentService.delete(idToDelete);
                    break;
                case TEXT:
                    textContentService.delete(idToDelete);
                    break;
                default:
                    break;
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
    /**
     *
     */
    private void processPostCreateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void processPostEditCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void processPostCreateContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void processPostEditContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
