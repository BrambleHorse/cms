package ru.bramblehorse.cms.controller.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.content.*;
import ru.bramblehorse.cms.service.AbstractService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 15.10.13
 * Time: 0:08
 * To change this template use File | Settings | File Templates.
 */
public class ContentAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<Category> categoryService;
    private AbstractService<LinkContent> linkContentService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(ContentAdminServlet.class);
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        linkContentService = (AbstractService<LinkContent>) context.getBean("linkContentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        String mode = req.getParameter("mode");

        if (action == null) {
            if ("links".equals(mode)) {
                processGetNoActionLinkContent(req, resp);
                return;
            }
            processGetNoActionContent(req, resp);
            return;
        }
        if ("create".equals(action)) {
            processGetCreateContent(req, resp);
            return;
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
        req.setAttribute("adminAction", "content");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processGetNoActionLinkContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LinkContent> linkList = linkContentService.getAll();
        Collections.sort(linkList);
        req.setAttribute("linkList", linkList);
        req.setAttribute("adminAction", "links");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetCreateContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contentType = req.getParameter("contentType");

        if (contentType != null) {
            RequestDispatcher rd;
            switch (ContentType.getType(contentType)) {
                case TABLE:

                    rd = getServletContext().getRequestDispatcher("/admin.content.table.do");
                    rd.forward(req, resp);
                    return;
                case TEXT:

                    rd = getServletContext().getRequestDispatcher("/admin.content.text.do");
                    rd.forward(req, resp);
                    return;
                case IMAGE:

                    rd = getServletContext().getRequestDispatcher("/admin.content.image.do");
                    rd.forward(req, resp);
                    return;
                case WYSIWYG:

                    rd = getServletContext().getRequestDispatcher("/admin.content.wysiwyg.do");
                    rd.forward(req, resp);
                    return;
                default:
                    break;
            }
        }
        req.setAttribute("adminAction", "new_content");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
}
