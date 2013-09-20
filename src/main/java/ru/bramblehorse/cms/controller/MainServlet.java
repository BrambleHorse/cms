package ru.bramblehorse.cms.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.Category;
import ru.bramblehorse.cms.model.Content;
import ru.bramblehorse.cms.model.ContentType;
import ru.bramblehorse.cms.model.TextContent;
import ru.bramblehorse.cms.service.AbstractService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
    private AbstractService<Category> categoryService;
    private AbstractService<TextContent> textContentService;

    @Override
    public void init() throws ServletException {
        context = ContextLoaderListener.getCurrentWebApplicationContext();
        categoryService = (AbstractService<Category>)context.getBean("categoryService");
        textContentService = (AbstractService<TextContent>) context.getBean("textContentService");
       // insertMockValues();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAll();
        req.setAttribute("categoryList",categoryList);
        String categoryId = req.getParameter("category");
        if(categoryId != null && !categoryId.isEmpty()) {
            List<Content> contentList = categoryService.getById(Integer.parseInt(categoryId)).getContent();
            for(Content c : contentList) {
                switch (c.getType()) {
                    case TEXT:
                        break;
                    case TABLE:
                        break;
                    case IMAGE:
                        break;
                    default:
                        break;
                }
            }
            req.setAttribute("contentList",contentList);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/index.jsp");
        rd.forward(req,resp);
    }

   private void insertMockValues() {
      TextContent content1 = new TextContent();
      TextContent content2 = new TextContent();
      content1.setText("Content 1");
      content2.setText("Content 2");
      Category category1 = new Category();
      Category category2 = new Category();
      category1.setName("Главная");
      category2.setName("Бетон");
      List<Content> list1 = new ArrayList<Content>();
      List<Content> list2 = new ArrayList<Content>();
      list1.add(content1);
      list2.add(content2);
      category1.setContent(list1);
      category2.setContent(list2);
      content1.setCategory(category1);
      content2.setCategory(category2);
      categoryService.create(category1);
      categoryService.create(category2);
      // textContentService.create(content1);
      // textContentService.create(content2);
   }
}
