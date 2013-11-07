package ru.bramblehorse.cms.controller;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imgscalr.Scalr;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.ContentType;
import ru.bramblehorse.cms.model.content.ImageContent;
import ru.bramblehorse.cms.model.content.LinkContent;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.service.CategoryService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.08.13
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public class LinkUploadServlet extends HttpServlet {

    private WebApplicationContext context;
    private AbstractService<LinkContent> linkContentService;

    @Override
    public void init() throws ServletException {
        context = ContextLoaderListener.getCurrentWebApplicationContext();
        linkContentService = (AbstractService<LinkContent>) context.getBean("linkContentService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String linkAction = req.getParameter("linkAction");
        if ("edit".equals(linkAction)) {

            processPostEditLinkContent(req, resp);
            return;
        }
        processPostLinkContent(req, resp);
    }


    private void processPostLinkContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contentPosition = null;
        String linkImagePath = null;
        String linkImageFilePath = null;
        String linkValue = null;
        String isVisible = null;

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
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
                        if ("linkValue".equalsIgnoreCase(item.getFieldName())) {
                            linkValue = item.getString();
                        }
                        if ("contentPosition".equalsIgnoreCase(item.getFieldName())) {
                            contentPosition = item.getString();
                        }
                        if("isVisible".equalsIgnoreCase(item.getFieldName())) {
                            isVisible = item.getString();
                        }
                    }
                }

                LinkContent tempLinkContent = new LinkContent();
                tempLinkContent.setLinkImagePath(linkImagePath);
                tempLinkContent.setLinkImageFilePath(linkImageFilePath);
                tempLinkContent.setLinkValue(linkValue);
                tempLinkContent.setContentPosition(Integer.parseInt(contentPosition));
                if("visible".equalsIgnoreCase(isVisible)){
                    tempLinkContent.setIsVisible(true);
                }  else {
                    tempLinkContent.setIsVisible(false);
                }
                linkContentService.create(tempLinkContent);

            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        resp.sendRedirect("/admin.do");

    }

    private void processPostEditLinkContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String linkImagePath = null;
        String linkImageFilePath = null;
        Integer idToEdit = null;

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                // Parse the request
                List<FileItem> items = upload.parseRequest(req);
                LinkContent tempLinkContent = null;
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
                        tempLinkContent = linkContentService.getById(idToEdit);
                        File oldFile = new File(tempLinkContent.getLinkImageFilePath());
                        if(oldFile.exists()){
                            oldFile.delete();
                        }
                        File uploadedFile = new File(path + "/" + fileName);
                        item.write(uploadedFile);
                        linkImageFilePath = uploadedFile.getAbsolutePath();
                        linkImagePath = "/link_icons/" + fileName;
                    }  else {
                        if ("contentId".equals(item.getFieldName())) {
                            idToEdit = Integer.parseInt(item.getString());
                        }
                    }
                }
                tempLinkContent.setLinkImagePath(linkImagePath);
                tempLinkContent.setLinkImageFilePath(linkImageFilePath);
                linkContentService.edit(tempLinkContent);
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/admin.do");
    }
}