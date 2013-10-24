package ru.bramblehorse.cms.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imgscalr.Scalr;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.Category;
import ru.bramblehorse.cms.model.ImageContent;
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
public class ImageUploadServlet extends HttpServlet {

    private WebApplicationContext context;
    private AbstractService<Category> categoryService;
    private AbstractService<ImageContent> imageContentService;
    private final int THUMB_IMAGE_WIDTH = 100;
    private final int THUMB_IMAGE_HEIGHT = 150;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryId = null;
        String contentPosition = null;
        String imagePath = null;
        String thumbImagePath = null;
        String imageFilePath = null;
        String thumbImageFilePath =null;

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
                        String fileName = new Date().getTime() + item.getName().substring(item.getName().length() - 4,item.getName().length());
                        String thumbFileName = fileName.substring(0, fileName.length() - 4) + "_thumb" + fileName.substring(fileName.length()-4,fileName.length());
                        String root = getServletContext().getRealPath("/");
                        File path = new File(root +"/upload");
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
                        if("categoryId".equals(item.getFieldName())){
                           categoryId = item.getString();
                        }
                        if("contentPosition".equals(item.getFieldName())){
                            contentPosition = item.getString();
                        }
                    }
                }
                Category currentCategory = categoryService.getById(Integer.parseInt(categoryId));
                ImageContent tempImageContent = new ImageContent();
                tempImageContent.setImageFilePath(imageFilePath);
                tempImageContent.setThumbImageFilePath(thumbImageFilePath);
                tempImageContent.setImagePath(imagePath);
                tempImageContent.setThumbImagePath(thumbImagePath);
                tempImageContent.setContentPosition(Integer.parseInt(contentPosition));
                tempImageContent.setCategory(currentCategory);
                imageContentService.create(tempImageContent);
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("/admin.do");
    }


    @Override
    public void init() throws ServletException {
        context = ContextLoaderListener.getCurrentWebApplicationContext();
        categoryService = (AbstractService<Category>) context.getBean("categoryService");
        imageContentService = (AbstractService<ImageContent>) context.getBean("imageContentService");
    }
}