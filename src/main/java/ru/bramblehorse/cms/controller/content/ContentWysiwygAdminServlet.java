package ru.bramblehorse.cms.controller.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.ImageContent;
import ru.bramblehorse.cms.model.content.WysiwygContent;
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
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class ContentWysiwygAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<Category> categoryService;
    private AbstractService<WysiwygContent> wysiwygContentService;
    private AbstractService<ImageContent> imageContentService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(ContentWysiwygAdminServlet.class);
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        wysiwygContentService = (AbstractService<WysiwygContent>) context.getBean("wysiwygContentService");
        imageContentService = (AbstractService<ImageContent>) context.getBean("imageContentService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if("create".equalsIgnoreCase(action)){

            processGetCreateWysiwygContent(req, resp);
            return;
        }

        if("edit".equalsIgnoreCase(action)){

            processGetEditWysiwygContent(req, resp);
            return;
        }

        if("delete".equalsIgnoreCase(action)){

            processGetDeleteWysiwygContent(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equalsIgnoreCase(action)) {

            processPostCreateWysiwygContent(req, resp);
            return;
        }
        if ("edit".equalsIgnoreCase(action)) {

            processPostEditWysiwygContent(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetCreateWysiwygContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        List<ImageContent> availableImages = imageContentService.getAll();
        req.setAttribute("availableImages", availableImages);
        req.setAttribute("adminAction", "new_wysiwyg_content");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processGetEditWysiwygContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");
        WysiwygContent tempWysiwyg = wysiwygContentService.getById(Integer.parseInt(contentId));
        List<ImageContent> availableImages = imageContentService.getAll();
        req.setAttribute("availableImages", availableImages);
        req.setAttribute("content", tempWysiwyg);
        req.setAttribute("adminAction", "edit_wysiwyg_content");
        List<Category> categoryList = categoryService.getAll();
        Collections.sort(categoryList);
        req.setAttribute("categoryList", categoryList);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteWysiwygContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");

        Integer idToDelete = null;
        try{

            idToDelete = Integer.parseInt(contentId);
        }  catch (Exception e) {

            logger.error(e.getMessage());
        }

        wysiwygContentService.delete(idToDelete);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processPostCreateWysiwygContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String categoryId = req.getParameter("categoryId");
        String contentPosition = req.getParameter("contentPosition");
        String isVisible = req.getParameter("isVisible");
        Category currentCategory = categoryService.getById(Integer.parseInt(categoryId));
        String wysiwyg = req.getParameter("wysiwygValue");
        WysiwygContent tempWysiwygContent = new WysiwygContent();
        tempWysiwygContent.setWysiwygData(wysiwyg);
        tempWysiwygContent.setContentPosition(Integer.parseInt(contentPosition));
        tempWysiwygContent.setCategory(currentCategory);
        if("visible".equalsIgnoreCase(isVisible)){
            tempWysiwygContent.setIsVisible(true);
        }  else {
            tempWysiwygContent.setIsVisible(false);
        }
        wysiwygContentService.create(tempWysiwygContent);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processPostEditWysiwygContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String categoryId = req.getParameter("categoryId");
        String contentId = req.getParameter("contentId");
        String contentPosition = req.getParameter("contentPosition");
        String isVisible = req.getParameter("isVisible");
        Category currentCategory = categoryService.getById(Integer.parseInt(categoryId));
        Integer idToEdit = Integer.parseInt(contentId);
        String wysiwygValue = req.getParameter("wysiwygValue");
        WysiwygContent tempWysiwygContent = new WysiwygContent();
        tempWysiwygContent.setContentId(idToEdit);
        tempWysiwygContent.setContentPosition(Integer.parseInt(contentPosition));
        tempWysiwygContent.setCategory(currentCategory);
        tempWysiwygContent.setWysiwygData(wysiwygValue);
        if("visible".equalsIgnoreCase(isVisible)){
            tempWysiwygContent.setIsVisible(true);
        }  else {
            tempWysiwygContent.setIsVisible(false);
        }
        wysiwygContentService.edit(tempWysiwygContent);
        RequestDispatcher  rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
}
