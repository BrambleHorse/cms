package ru.bramblehorse.cms.controller.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.TableContent;
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
 * Date: 10.12.13
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class ContentTableAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<Category> categoryService;
    private AbstractService<TableContent> tableContentService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(ContentTableAdminServlet.class);
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        tableContentService = (AbstractService<TableContent>) context.getBean("tableContentService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if("create".equalsIgnoreCase(action)){

            processGetCreateTableContent(req, resp);
            return;
        }

        if("edit".equalsIgnoreCase(action)){

            processGetEditTableContent(req, resp);
            return;
        }

        if("delete".equalsIgnoreCase(action)){

            processGetDeleteTableContent(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equalsIgnoreCase(action)) {

            processPostCreateTableContent(req, resp);
            return;
        }
        if ("edit".equalsIgnoreCase(action)) {

            processPostEditTableContent(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetCreateTableContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        req.setAttribute("adminAction", "new_table_content");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processGetEditTableContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");
        TableContent tempTable = tableContentService.getById(Integer.parseInt(contentId));
        req.setAttribute("content", tempTable);
        req.setAttribute("adminAction", "edit_table_content");
        List<Category> categoryList = categoryService.getAll();
        Collections.sort(categoryList);
        req.setAttribute("categoryList", categoryList);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteTableContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");

        Integer idToDelete = null;
        try{

            idToDelete = Integer.parseInt(contentId);
        }  catch (Exception e) {

            logger.error(e.getMessage());
        }

        if(idToDelete != null){

            tableContentService.delete(idToDelete);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostCreateTableContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String categoryId = req.getParameter("categoryId");
        String contentPosition = req.getParameter("contentPosition");
        String isVisible = req.getParameter("isVisible");
        String htmlTable = req.getParameter("tableValue");
        Category currentCategory = categoryService.getById(Integer.parseInt(categoryId));
        TableContent tempTableContent = new TableContent();
        tempTableContent.setHtmlTable(htmlTable);
        tempTableContent.setContentPosition(Integer.parseInt(contentPosition));
        tempTableContent.setCategory(currentCategory);
        if("visible".equalsIgnoreCase(isVisible)){
            tempTableContent.setIsVisible(true);
        }  else {
            tempTableContent.setIsVisible(false);
        }
        tableContentService.create(tempTableContent);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostEditTableContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String categoryId = req.getParameter("categoryId");
        String contentId = req.getParameter("contentId");
        String contentPosition = req.getParameter("contentPosition");
        String isVisible = req.getParameter("isVisible");
        String htmlTable = req.getParameter("tableValue");
        Category currentCategory = categoryService.getById(Integer.parseInt(categoryId));
        Integer idToEdit = Integer.parseInt(contentId);
        TableContent tempTableContent = new TableContent();
        tempTableContent.setContentId(idToEdit);
        tempTableContent.setHtmlTable(htmlTable);
        tempTableContent.setContentPosition(Integer.parseInt(contentPosition));
        tempTableContent.setCategory(currentCategory);
        if("visible".equalsIgnoreCase(isVisible)){
            tempTableContent.setIsVisible(true);
        }  else {
            tempTableContent.setIsVisible(false);
        }
        tableContentService.edit(tempTableContent);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }
}
