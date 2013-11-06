package ru.bramblehorse.cms.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.Content;
import ru.bramblehorse.cms.model.content.LinkContent;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.service.CategoryService;

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
 * Date: 29.08.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class MainServlet extends HttpServlet {

    private WebApplicationContext context;
    private CategoryService categoryService;
    private AbstractService<LinkContent> linkContentService;

    @Override
    public void init() throws ServletException {
        context = ContextLoaderListener.getCurrentWebApplicationContext();
        categoryService = (CategoryService)context.getBean("categoryService");
        linkContentService = (AbstractService<LinkContent>) context.getBean("linkContentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryId = req.getParameter("category");
        List<Category> categoryList = categoryService.getRootCategories();
        Collections.sort(categoryList);
        for(Category c : categoryList) {
            c.sortChildren();
        }
        req.setAttribute("categoryList",categoryList);
        List<LinkContent>linkList = linkContentService.getAll();
        Collections.sort(linkList);
        req.setAttribute("linkList", linkList);
        if(categoryList != null) {
            if((!categoryList.isEmpty()) && (categoryId == null || categoryId.isEmpty())) {
                categoryId = String.valueOf(categoryList.get(0).getId());
            }
        }
        if(categoryId != null && !categoryId.isEmpty()) {
            List<Content> contentList = categoryService.getById(Integer.parseInt(categoryId)).getContent();
            Collections.sort(contentList);
            req.setAttribute("contentList",contentList);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/index.jsp");
        rd.forward(req,resp);
    }
}
