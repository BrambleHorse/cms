package ru.bramblehorse.cms.controller.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.Content;
import ru.bramblehorse.cms.model.content.ContentType;
import ru.bramblehorse.cms.model.content.ImageContent;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.service.CategoryService;
import ru.bramblehorse.cms.util.ImageFilesUtil;

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
 * Date: 10.12.13
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public class ContentCategoryAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private CategoryService categoryService;
    private AbstractService<ImageContent> imageContentService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(ContentCategoryAdminServlet.class);
        categoryService = (CategoryService) context.getBean("categoryService");
        imageContentService = (AbstractService<ImageContent>) context.getBean("imageContentService");
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
        Integer idToDelete;
        if (categoryId != null) {
            idToDelete = Integer.parseInt(categoryId);
            List<Content> relatedContentList = categoryService.getById(idToDelete).getContent();
            for(Content c : relatedContentList){
                if(ContentType.IMAGE.equals(c.getType())){

                    ImageContent imageContentToDelete = (ImageContent)c;
                    ImageFilesUtil.deleteOrphanImage(imageContentToDelete.getImageFilePath());
                    ImageFilesUtil.deleteOrphanImage(imageContentToDelete.getThumbImageFilePath());
                }
            }

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
        String isVisible = req.getParameter("isVisible");
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
        if("visible".equalsIgnoreCase(isVisible)){
            temp.setIsVisible(true);
        }   else {
            temp.setIsVisible(false);
        }
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
        String isVisible = req.getParameter("isVisible");
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
        if("visible".equalsIgnoreCase(isVisible)){
            temp.setIsVisible(true);
        }  else {
            temp.setIsVisible(false);
        }
        if(categoryParentValue != null)
            temp.setParentCategory(categoryService.getById(categoryParentValue));
        categoryService.edit(temp);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
}
