package ru.bramblehorse.cms.controller.commerce;

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
import ru.bramblehorse.cms.model.commerce.*;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.util.ImageFilesUtil;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 10.12.13
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 */
public class CatalogItemAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private Logger logger;
    private AbstractService<Item> itemService;
    private AbstractService<CatalogCategory> catalogCategoryService;
    private AbstractService<CatalogCategoryFilter> catalogCategoryFilterService;
    private AbstractService<FilterCriterion> filterCriterionService;
    private AbstractService<Brand> brandService;

    private final int THUMB_IMAGE_WIDTH = 150;
    private final int THUMB_IMAGE_HEIGHT = 150;


    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        logger = LoggerFactory.getLogger(CatalogItemAdminServlet.class);

        itemService = (AbstractService<Item>) context.getBean("itemService");
        catalogCategoryService = (AbstractService<CatalogCategory>) context.getBean("catalogCategoryService");
        catalogCategoryFilterService = (AbstractService<CatalogCategoryFilter>) context.getBean("catalogCategoryFilterService");
        filterCriterionService = (AbstractService<FilterCriterion>) context.getBean("filterCriterionService");
        brandService = (AbstractService<Brand>) context.getBean("brandService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equalsIgnoreCase(action)) {

            processGetCreateItem(req, resp);
            return;
        }
        if ("edit".equalsIgnoreCase(action)) {

            processGetEditItem(req, resp);
            return;
        }
        if ("delete".equalsIgnoreCase(action)) {

            processGetDeleteItem(req, resp);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {

            processPostMultipartItem(req, resp);
            return;

        } else {

            processPostItem(req, resp);
            return;
        }
    }

    private void processGetCreateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        req.setAttribute("filterList", catalogCategoryFilterService.getAll());
        req.setAttribute("brandList", brandService.getAll());
        req.setAttribute("adminAction", "new_item_description");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetEditItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {


    }

    private void processGetDeleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Integer idToDelete = null;
        try {

            idToDelete = Integer.parseInt(req.getParameter("itemId"));
        } catch (Exception e) {

            logger.error(e.getMessage());
        }
        String pathToDelete = req.getParameter("path");
        String thumbPathToDelete = req.getParameter("thumbPath");
        ImageFilesUtil.deleteOrphanImage(pathToDelete);
        ImageFilesUtil.deleteOrphanImage(thumbPathToDelete);
        if (idToDelete != null) {

            itemService.delete(idToDelete);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostMultipartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String action = null;
        String imagePath = null;
        String thumbImagePath = null;
        String imageFilePath = null;
        String thumbImageFilePath = null;
        String oldImageFilePath = null;
        String oldThumbImageFilePath = null;
        Integer idToSetImage = null;

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

                    String fileName = new Date().getTime() + item.getName().substring(item.getName().length() - 4, item.getName().length());
                    String thumbFileName = fileName.substring(0, fileName.length() - 4) + "_thumb" + fileName.substring(fileName.length() - 4, fileName.length());
                    String root = getServletContext().getRealPath("/");
                    File path = new File(root + "/commerce");
                    if (!path.exists()) {
                        path.mkdirs();
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
                    imagePath = "/commerce/" + fileName;
                    thumbImagePath = "/commerce/" + thumbFileName;
                } else {

                    if("itemId".equalsIgnoreCase(item.getFieldName())) {
                        idToSetImage = Integer.parseInt(item.getString());
                    }
                    if("action".equalsIgnoreCase(item.getFieldName())) {
                        action = item.getString();
                    }
                    if ("oldImageFilePath".equalsIgnoreCase(item.getFieldName())) {
                        oldImageFilePath = item.getString();
                    }
                    if ("oldThumbImageFilePath".equalsIgnoreCase(item.getFieldName())) {
                        oldThumbImageFilePath = item.getString();
                    }
                }
            }


        } catch (FileUploadException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        if("edit".equalsIgnoreCase(action)){

            ImageFilesUtil.deleteOrphanImage(oldImageFilePath);
            ImageFilesUtil.deleteOrphanImage(oldThumbImageFilePath);
        }
        Item item = itemService.getById(idToSetImage);
        item.setItemImagePath(imagePath);
        item.setItemImageFilePath(imageFilePath);
        item.setItemThumbImagePath(thumbImagePath);
        item.setItemThumbImageFilePath(thumbImageFilePath);
        itemService.edit(item);
        resp.sendRedirect("/admin.do");

    }

    private void processPostItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String action = req.getParameter("action");
        String catalogCategoryId = req.getParameter("catalogCategoryId");
        String itemId = req.getParameter("itemId");
        String itemName = req.getParameter("itemName");
        String itemDescription = req.getParameter("itemDescription");
        String itemPrice = req.getParameter("itemPrice");
        String brandId = req.getParameter("brandId");

        Item item = null;
        if("create".equalsIgnoreCase(action)){

            item = new Item();
        }
        if("edit".equalsIgnoreCase(action)){

            item = itemService.getById(Integer.parseInt(itemId));
        }
        item.setItemName(itemName);
        item.setItemCategory(catalogCategoryService.getById(Integer.parseInt(catalogCategoryId)));
        item.setItemDescription(itemDescription);
        item.setItemPrice(Integer.parseInt(itemPrice));
        item.setItemBrand(brandService.getById(Integer.parseInt(brandId)));
        List<FilterCriterion> criteria = new ArrayList<FilterCriterion>();
        for(FilterCriterion criterion : filterCriterionService.getAll()){

            if(criterion.getFilterCriterionId().equals
                    (Integer.parseInt(req.getParameter(criterion.getFilterCriterionValue())))){

                criteria.add(criterion);
                logger.info("ADDED CRITERION . .");
            }
        }
        item.setFilterCriteria(criteria);
        RequestDispatcher rd;
        if("create".equalsIgnoreCase(action)){

            Integer createdItemId = itemService.create(item);
            req.setAttribute("itemId", createdItemId);
            req.setAttribute("adminAction", "new_item_image");
            rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
            rd.forward(req,resp);
            return;
        }
        if("edit".equalsIgnoreCase(action)){

            itemService.edit(item);
            req.setAttribute("itemId", item.getItemId());
            rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
            rd.forward(req, resp);
        }
    }
}
