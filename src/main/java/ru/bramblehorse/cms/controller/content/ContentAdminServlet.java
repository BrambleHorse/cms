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
    private AbstractService<TableContent> tableContentService;
    private AbstractService<TextContent> textContentService;
    private AbstractService<ImageContent> imageContentService;
    private AbstractService<WysiwygContent> wysiwygContentService;
    private AbstractService<LinkContent> linkContentService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(ContentAdminServlet.class);

        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        tableContentService = (AbstractService<TableContent>) context.getBean("tableContentService");
        textContentService = (AbstractService<TextContent>) context.getBean("textContentService");
        imageContentService = (AbstractService<ImageContent>) context.getBean("imageContentService");
        wysiwygContentService = (AbstractService<WysiwygContent>) context.getBean("wysiwygContentService");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equals(action)) {
            processPostCreateContent(req, resp);
            return;
        }
        if ("edit".equals(action)) {
            processPostEditContent(req, resp);
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

    private void processPostCreateContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryId = req.getParameter("categoryId");
        String contentType = req.getParameter("contentType");
        String contentPosition = req.getParameter("contentPosition");
        String isVisible = req.getParameter("isVisible");

        if (categoryId != null && contentType != null && contentPosition != null) {
            Category currentCategory = categoryService.getById(Integer.parseInt(categoryId));
            RequestDispatcher rd;
            switch (ContentType.getType(contentType)) {
                case TABLE:
                    String htmlTable = req.getParameter("tableValue");
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
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                case TEXT:
                    String text = req.getParameter("textValue");
                    TextContent tempTextContent = new TextContent();
                    tempTextContent.setText(text);
                    tempTextContent.setContentPosition(Integer.parseInt(contentPosition));
                    tempTextContent.setCategory(currentCategory);
                    if("visible".equalsIgnoreCase(isVisible)){
                        tempTextContent.setIsVisible(true);
                    }  else {
                        tempTextContent.setIsVisible(false);
                    }
                    textContentService.create(tempTextContent);
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                case WYSIWYG:
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
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                default:
                    break;
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostEditContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryId = req.getParameter("categoryId");
        String contentId = req.getParameter("contentId");
        String contentType = req.getParameter("contentType");
        String contentPosition = req.getParameter("contentPosition");
        String isVisible = req.getParameter("isVisible");

        if (contentType != null && contentPosition != null && contentId != null) {

            Category currentCategory = null;
            if (categoryId != null) {
                currentCategory = categoryService.getById(Integer.parseInt(categoryId));
            }
            Integer idToEdit = Integer.parseInt(contentId);
            RequestDispatcher rd;
            switch (ContentType.getType(contentType)) {
                case TABLE:
                    String htmlTable = req.getParameter("tableValue");
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
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                case TEXT:
                    String text = req.getParameter("textValue");
                    TextContent tempTextContent = new TextContent();
                    tempTextContent.setContentId(idToEdit);
                    tempTextContent.setText(text);
                    tempTextContent.setContentPosition(Integer.parseInt(contentPosition));
                    tempTextContent.setCategory(currentCategory);
                    if("visible".equalsIgnoreCase(isVisible)){
                        tempTextContent.setIsVisible(true);
                    }  else {
                        tempTextContent.setIsVisible(false);
                    }
                    textContentService.edit(tempTextContent);
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                case IMAGE:
                    String imagePath = req.getParameter("imagePath");
                    String thumbImagePath = req.getParameter("thumbImagePath");
                    String imageFilePath = req.getParameter("imageFilePath");
                    String thumbImageFilePath = req.getParameter("thumbImageFilePath");
                    ImageContent tempImageContent = new ImageContent();
                    tempImageContent.setContentId(idToEdit);
                    tempImageContent.setContentPosition(Integer.parseInt(contentPosition));
                    tempImageContent.setCategory(currentCategory);
                    tempImageContent.setImagePath(imagePath);
                    tempImageContent.setThumbImagePath(thumbImagePath);
                    tempImageContent.setImageFilePath(imageFilePath);
                    tempImageContent.setThumbImageFilePath(thumbImageFilePath);
                    if("visible".equalsIgnoreCase(isVisible)){
                        tempImageContent.setIsVisible(true);
                    }  else {
                        tempImageContent.setIsVisible(false);
                    }
                    imageContentService.edit(tempImageContent);
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                case WYSIWYG:
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
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                case LINK:
                    String linkValue = req.getParameter("linkValue");
                    String linkImagePath = req.getParameter("linkImagePath");
                    String linkImageFilePath = req.getParameter("linkImageFilePath");
                    LinkContent tempLinkContent = new LinkContent();
                    tempLinkContent.setContentId(idToEdit);
                    tempLinkContent.setContentPosition(Integer.parseInt(contentPosition));
                    tempLinkContent.setCategory(null);
                    tempLinkContent.setLinkValue(linkValue);
                    tempLinkContent.setLinkImagePath(linkImagePath);
                    tempLinkContent.setLinkImageFilePath(linkImageFilePath);
                    if("visible".equalsIgnoreCase(isVisible)){
                        tempLinkContent.setIsVisible(true);
                    }  else {
                        tempLinkContent.setIsVisible(false);
                    }
                    linkContentService.edit(tempLinkContent);
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                default:
                    break;
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
}
