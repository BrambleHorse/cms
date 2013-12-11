package ru.bramblehorse.cms.controller.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.TextContent;
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
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class ContentTextAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<Category> categoryService;
    private AbstractService<TextContent> textContentService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(ContentTextAdminServlet.class);
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        textContentService = (AbstractService<TextContent>) context.getBean("textContentService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if("create".equalsIgnoreCase(action)){

            processGetCreateTextContent(req, resp);
            return;
        }

        if("edit".equalsIgnoreCase(action)){

            processGetEditTextContent(req, resp);
            return;
        }

        if("delete".equalsIgnoreCase(action)){

            processGetDeleteTextContent(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equalsIgnoreCase(action)) {

            processPostCreateTextContent(req, resp);
            return;
        }
        if ("edit".equalsIgnoreCase(action)) {

            processPostEditTextContent(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetCreateTextContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        req.setAttribute("adminAction", "new_text_content");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processGetEditTextContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");
        TextContent tempText = textContentService.getById(Integer.parseInt(contentId));
        req.setAttribute("content", tempText);
        req.setAttribute("adminAction", "edit_text_content");
        List<Category> categoryList = categoryService.getAll();
        Collections.sort(categoryList);
        req.setAttribute("categoryList", categoryList);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteTextContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");

        Integer idToDelete = null;
        try{

            idToDelete = Integer.parseInt(contentId);
        }  catch (Exception e) {

            logger.error(e.getMessage());
        }

        textContentService.delete(idToDelete);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processPostCreateTextContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {



    }

    private void processPostEditTextContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {



    }
}
