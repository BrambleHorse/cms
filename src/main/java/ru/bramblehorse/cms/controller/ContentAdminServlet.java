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
    private AbstractService<Category> categoryService;
    private AbstractService<TableContent> tableContentService;
    private AbstractService<TextContent> textContentService;
    private AbstractService<ImageContent> imageContentService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        tableContentService = (AbstractService<TableContent>) context.getBean("tableContentService");
        textContentService = (AbstractService<TextContent>) context.getBean("textContentService");
        imageContentService = (AbstractService<ImageContent>) context.getBean("imageContentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            processGetNoActionContent(req, resp);
            return;
        }
        if ("create".equals(action)) {
            processGetCreateContent(req, resp);
            return;
        }
        if ("edit".equals(action)) {
            processGetEditContent(req, resp);
            return;
        }
        if ("delete".equals(action)) {
            processGetDeleteContent(req, resp);
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
        req.setAttribute("adminAction","content");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetCreateContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contentType = req.getParameter("contentType");
        String categoryId = req.getParameter("categoryId");
        req.setAttribute("contentType", contentType);
        req.setAttribute("categoryId", categoryId);
        if (contentType != null) {
            RequestDispatcher rd;
            switch (ContentType.getType(contentType)) {
                case TABLE:
                    req.setAttribute("adminAction","new_table_content");
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                case TEXT:
                    req.setAttribute("adminAction","new_text_content");
                    rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                    rd.forward(req, resp);
                    return;
                case IMAGE:
                        req.setAttribute("adminAction","new_image_content");
                        rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                        rd.forward(req, resp);
                        return;

                default:
                    break;
            }
        }
        req.setAttribute("adminAction","new_content");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetEditContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contentId = req.getParameter("contentId");
        ContentType contentType = ContentType.getType(req.getParameter("contentType"));
        List<Category> categoryList = categoryService.getAll();
        Collections.sort(categoryList);
        req.setAttribute("categoryList", categoryList);
        RequestDispatcher rd;
        switch (contentType) {
            case TABLE:
                TableContent tempTable = tableContentService.getById(Integer.parseInt(contentId));
                req.setAttribute("content", tempTable);
                req.setAttribute("adminAction","edit_table_content");
                rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                rd.forward(req, resp);
                return;
            case TEXT:
                TextContent tempText = textContentService.getById(Integer.parseInt(contentId));
                req.setAttribute("content", tempText);
                req.setAttribute("adminAction","edit_text_content");
                rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                rd.forward(req, resp);
                return;
            case IMAGE:
                ImageContent tempImage = imageContentService.getById(Integer.parseInt(contentId));
                req.setAttribute("content", tempImage);
                req.setAttribute("adminAction","edit_image_content");
                rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
                rd.forward(req, resp);
                return;
            default:
                break;
        }
        rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
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
                case IMAGE:
                    String pathToDelete = req.getParameter("path");
                    String thumbPathToDelete = req.getParameter("thumbPath");
                    File fileToDelete = new File(pathToDelete);
                    File thumbFileToDelete = new File(thumbPathToDelete);
                    if(fileToDelete.exists()){
                       fileToDelete.delete();
                        System.out.println("deleted . .");
                    }   else {
                        System.out.println("no file exists . .");
                    }
                    if(thumbFileToDelete.exists()){
                        thumbFileToDelete.delete();
                        System.out.println("thumb deleted . .");
                    }   else {
                        System.out.println("no thumb file exists . .");
                    }
                    imageContentService.delete(idToDelete);
                default:
                    break;
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostCreateContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryId = req.getParameter("categoryId");
        String contentType = req.getParameter("contentType");
        String contentPosition = req.getParameter("contentPosition");

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
                    textContentService.create(tempTextContent);
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

        if (categoryId != null && contentType != null && contentPosition != null && contentId != null) {
            Category currentCategory = categoryService.getById(Integer.parseInt(categoryId));
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
                    imageContentService.edit(tempImageContent);
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
