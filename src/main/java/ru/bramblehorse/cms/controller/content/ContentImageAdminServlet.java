package ru.bramblehorse.cms.controller.content;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.ImageContent;
import ru.bramblehorse.cms.service.AbstractService;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 */

public class ContentImageAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<Category> categoryService;
    private AbstractService<ImageContent> imageContentService;
    private final int THUMB_IMAGE_WIDTH = 100;
    private final int THUMB_IMAGE_HEIGHT = 150;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(ContentImageAdminServlet.class);
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        imageContentService = (AbstractService<ImageContent>) context.getBean("imageContentService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("create".equalsIgnoreCase(action)) {

            processGetCreateImageContent(req, resp);
            return;
        }
        if ("edit".equalsIgnoreCase(action)) {

            processGetEditImageContent(req, resp);
            return;
        }
        if ("delete".equalsIgnoreCase(action)) {

            processGetDeleteImageContent(req, resp);
            return;
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {

            req.setCharacterEncoding("UTF-8");
            processPostMultipartImageContent(req, resp);

        } else {

            processPostImageContent(req, resp);
        }
    }

    private void processGetCreateImageContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        req.setAttribute("adminAction", "new_image_content");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetEditImageContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");
        ImageContent tempImage = imageContentService.getById(Integer.parseInt(contentId));
        req.setAttribute("content", tempImage);
        req.setAttribute("adminAction", "edit_image_content");
        List<Category> categoryList = categoryService.getAll();
        Collections.sort(categoryList);
        req.setAttribute("categoryList", categoryList);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteImageContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String contentId = req.getParameter("contentId");

        Integer idToDelete = null;
        try {

            idToDelete = Integer.parseInt(contentId);
        } catch (Exception e) {

            logger.error(e.getMessage());
        }

        String pathToDelete = req.getParameter("path");
        String thumbPathToDelete = req.getParameter("thumbPath");
        File fileToDelete = new File(pathToDelete);
        File thumbFileToDelete = new File(thumbPathToDelete);
        if (fileToDelete.exists()) {
            fileToDelete.delete();
            logger.info("Image file deleted . .");
        } else {
            logger.info("No image file exists, nothing to delete . .");
        }
        if (thumbFileToDelete.exists()) {
            thumbFileToDelete.delete();
            logger.info("Thumb image file deleted . .");
        } else {
            logger.info("No thumb image file exists, nothing to delete . .");
        }
        if (idToDelete != null) {

            imageContentService.delete(idToDelete);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostMultipartImageContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String action = null;
        String categoryId = null;
        String contentId = null;
        String contentPosition = null;
        String imageName = null;
        String imagePath = null;
        String thumbImagePath = null;
        String imageFilePath = null;
        String thumbImageFilePath = null;
        String isVisible = null;

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
                    imageName = item.getName();
                    String fileName = new Date().getTime() + item.getName().substring(item.getName().length() - 4, item.getName().length());
                    String thumbFileName = fileName.substring(0, fileName.length() - 4) + "_thumb" + fileName.substring(fileName.length() - 4, fileName.length());
                    String root = getServletContext().getRealPath("/");
                    File path = new File(root + "/upload");
                    if (!path.exists()) {
                        boolean status = path.mkdirs();
                    }
                    File uploadedFile = new File(path + "/" + fileName);
                    item.write(uploadedFile);
                    BufferedImage bi = ImageIO.read(uploadedFile);
                    BufferedImage thumbnail =
                            Scalr.resize(bi, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                                    THUMB_IMAGE_HEIGHT, THUMB_IMAGE_WIDTH, Scalr.OP_ANTIALIAS);
                    File thumbFile = new File(path + "/" + thumbFileName);
                    ImageIO.write(thumbnail, "jpg", thumbFile);
                    imageFilePath = uploadedFile.getAbsolutePath();
                    thumbImageFilePath = thumbFile.getAbsolutePath();
                    imagePath = "/upload/" + fileName;
                    thumbImagePath = "/upload/" + thumbFileName;
                } else {

                    if ("action".equalsIgnoreCase(item.getFieldName())) {

                        action = item.getString();
                    }
                    if ("categoryId".equalsIgnoreCase(item.getFieldName())) {
                        categoryId = item.getString();
                    }
                    if("contentId".equalsIgnoreCase(item.getFieldName())) {
                        contentId = item.getString();
                    }
                    if ("contentPosition".equalsIgnoreCase(item.getFieldName())) {
                        contentPosition = item.getString();
                    }
                    if ("isVisible".equalsIgnoreCase(item.getFieldName())) {
                        isVisible = item.getString();
                    }
                }
            }
        } catch (FileUploadException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        Integer currentCategoryId = null;
        try{

            currentCategoryId = Integer.parseInt(categoryId);

        }   catch (Exception e){
            logger.error(e.getMessage());
        }

        Category currentCategory = categoryService.getById(currentCategoryId);
        ImageContent tempImageContent = new ImageContent();
        tempImageContent.setImageName(imageName);
        tempImageContent.setImageFilePath(imageFilePath);
        tempImageContent.setThumbImageFilePath(thumbImageFilePath);
        tempImageContent.setImagePath(imagePath);
        tempImageContent.setThumbImagePath(thumbImagePath);
        tempImageContent.setContentPosition(Integer.parseInt(contentPosition));
        tempImageContent.setCategory(currentCategory);
        if ("visible".equalsIgnoreCase(isVisible)) {
            tempImageContent.setIsVisible(true);
        } else {
            tempImageContent.setIsVisible(false);
        }

        if ("create".equalsIgnoreCase(action)) {


            imageContentService.create(tempImageContent);
        }

        if ("edit".equalsIgnoreCase(action)) {

            Integer imageContentId = null;

            try{

                imageContentId = Integer.parseInt(contentId);

            }   catch (Exception e){
                logger.error(e.getMessage());
            }

            tempImageContent.setContentId(imageContentId);
            imageContentService.edit(tempImageContent);
        }

        resp.sendRedirect("/admin.do");
    }

    private void processPostImageContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String categoryId = req.getParameter("categoryId");
        String contentId = req.getParameter("contentId");
        String contentPosition = req.getParameter("contentPosition");
        String isVisible = req.getParameter("isVisible");
        String imageName = req.getParameter("imageName");
        String imagePath = req.getParameter("imagePath");
        String thumbImagePath = req.getParameter("thumbImagePath");
        String imageFilePath = req.getParameter("imageFilePath");
        String thumbImageFilePath = req.getParameter("thumbImageFilePath");
        Integer idToEdit = null;
        Integer currentCategoryId = null;
        try {
            idToEdit = Integer.parseInt(contentId);
            currentCategoryId = Integer.parseInt(categoryId);
        }  catch (Exception e){

            logger.error(e.getMessage());
        }

        ImageContent tempImageContent = new ImageContent();
        Category currentCategory = categoryService.getById(currentCategoryId);
        tempImageContent.setContentId(idToEdit);
        tempImageContent.setContentPosition(Integer.parseInt(contentPosition));
        tempImageContent.setCategory(currentCategory);
        tempImageContent.setImageName(imageName);
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
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
}
