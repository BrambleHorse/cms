package ru.bramblehorse.cms.controller.content;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.LinkContent;
import ru.bramblehorse.cms.service.AbstractService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 10.12.13
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class ContentLinkAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<Category> categoryService;
    private AbstractService<LinkContent> linkContentService;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(ContentLinkAdminServlet.class);
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        linkContentService = (AbstractService<LinkContent>) context.getBean("linkContentService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equalsIgnoreCase(action)) {

            processGetCreateLinkContent(req, resp);
            return;
        }

        if ("edit".equalsIgnoreCase(action)) {

            processGetEditLinkContent(req, resp);
            return;
        }

        if ("delete".equalsIgnoreCase(action)) {

            processGetDeleteLinkContent(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {

            processPostMultipartLinkContent(req, resp);

        } else {

            processPostLinkContent(req, resp);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetCreateLinkContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {


    }

    private void processGetEditLinkContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        RequestDispatcher rd;
        String contentId = req.getParameter("contentId");
        LinkContent tempLink = linkContentService.getById(Integer.parseInt(contentId));
        String editImage = req.getParameter("editImage");
        req.setAttribute("content", tempLink);
        if ("true".equalsIgnoreCase(editImage)) {
            req.setAttribute("adminAction", "edit_link_content");
            rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
            rd.forward(req, resp);
            return;
        }
        req.setAttribute("adminAction", "edit_link");
        rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteLinkContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");

        Integer idToDelete = null;
        try {

            idToDelete = Integer.parseInt(contentId);
        } catch (Exception e) {

            logger.error(e.getMessage());
        }

        String linkImagePathToDelete = req.getParameter("linkImageFilePath");
        File linkFileToDelete = new File(linkImagePathToDelete);
        if (linkFileToDelete.exists()) {
            linkFileToDelete.delete();
            logger.info("Link image file deleted . .");
        } else {

            logger.info("No link image file exists, nothing to delete . .");
        }
        linkContentService.delete(idToDelete);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);

    }

    private void processPostMultipartLinkContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = null;
        String contentPosition = null;
        String linkImagePath = null;
        String linkImageFilePath = null;
        String linkValue = null;
        String isVisible = null;
        String action = null;

        // Create a factory for disk-based file items
        FileItemFactory factory = new DiskFileItemFactory();
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            // Parse the request
            List<FileItem> items = upload.parseRequest(req);
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                if (!item.isFormField()) {
                    String fileName = item.getName();
                    String root = getServletContext().getRealPath("/");
                    File path = new File(root + "/link_icons");
                    if (!path.exists()) {
                        path.mkdirs();
                    }
                    File uploadedFile = new File(path + "/" + fileName);
                    item.write(uploadedFile);
                    linkImageFilePath = uploadedFile.getAbsolutePath();
                    linkImagePath = "/link_icons/" + fileName;
                } else {
                    if("action".equalsIgnoreCase(item.getFieldName())) {
                        action = item.getString();
                    }
                    if("contentId".equalsIgnoreCase(item.getFieldName())){

                        contentId = item.getString();
                    }
                    if ("linkValue".equalsIgnoreCase(item.getFieldName())) {
                        linkValue = item.getString();
                    }
                    if ("contentPosition".equalsIgnoreCase(item.getFieldName())) {
                        contentPosition = item.getString();
                    }
                    if ("isVisible".equalsIgnoreCase(item.getFieldName())) {
                        isVisible = item.getString();
                    }
                }
            }

            LinkContent tempLinkContent = new LinkContent();
            tempLinkContent.setLinkImagePath(linkImagePath);
            tempLinkContent.setLinkImageFilePath(linkImageFilePath);
            tempLinkContent.setLinkValue(linkValue);
            tempLinkContent.setContentPosition(Integer.parseInt(contentPosition));
            if ("visible".equalsIgnoreCase(isVisible)) {
                tempLinkContent.setIsVisible(true);
            } else {
                tempLinkContent.setIsVisible(false);
            }

            if("create".equalsIgnoreCase(action)){

                linkContentService.create(tempLinkContent);
            }
            if("edit".equalsIgnoreCase(action)){

                Integer idToEdit = Integer.parseInt(contentId);
                tempLinkContent.setContentId(idToEdit);
                linkContentService.edit(tempLinkContent);
            }

        } catch (FileUploadException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        resp.sendRedirect("/admin.do");
    }

    private void processPostLinkContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");
        String contentPosition = req.getParameter("contentPosition");
        String isVisible = req.getParameter("isVisible");
        String linkValue = req.getParameter("linkValue");
        String linkImagePath = req.getParameter("linkImagePath");
        String linkImageFilePath = req.getParameter("linkImageFilePath");
        LinkContent tempLinkContent = new LinkContent();
        Integer idToEdit = null;
        Integer currentLinkPosition = null;
        try {

            idToEdit = Integer.parseInt(contentId);
            currentLinkPosition = Integer.parseInt(contentPosition);
        }  catch (Exception e){
            logger.error(e.getMessage());
        }
        tempLinkContent.setContentId(idToEdit);
        tempLinkContent.setContentPosition(currentLinkPosition);
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
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
}
