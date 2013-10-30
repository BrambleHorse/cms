package ru.bramblehorse.cms.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.*;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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



    @Override
    public void init() throws ServletException {
        context = ContextLoaderListener.getCurrentWebApplicationContext();
        categoryService = (CategoryService)context.getBean("categoryService");
        //uncomment if you want to fill your database with mock values
//        insertMockValues();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getRootCategories();

        String categoryId = req.getParameter("category");
        Collections.sort(categoryList);
        req.setAttribute("categoryList",categoryList);
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

   private void insertMockValues() {
      TextContent content1 = new TextContent();
      TextContent content2 = new TextContent();
      TableContent table1 = new TableContent();
      TableContent table2 = new TableContent();
      table1.setHtmlTable("<tr><td>11111</td><td>2222</td></tr><tr><td>3333</td><td>4444</td></tr>");
      table2.setHtmlTable("<tr><td>555</td><td>66666</td></tr><tr><td>77777</td><td>8888</td></tr>");
      table1.setContentPosition(1);
      table2.setContentPosition(0);
      content1.setText("Content 1");
      content1.setContentPosition(0);
      content2.setText("Content 2");
      content2.setContentPosition(1);
      Category category1 = new Category();
      Category category2 = new Category();
      category1.setName("Главная");
      category2.setName("Бетон");
      category2.setParentCategory(category1);
      category1.setCategoryPosition(0);
      category2.setCategoryPosition(1);
      List<Content> list1 = new ArrayList<Content>();
      List<Content> list2 = new ArrayList<Content>();
      list1.add(content1);
      list1.add(table1);
      list2.add(content2);
      list2.add(table2);
      category1.setContent(list1);
      category2.setContent(list2);
      content1.setCategory(category1);
      content2.setCategory(category2);
      table1.setCategory(category1);
      table2.setCategory(category2);
      categoryService.create(category1);
      categoryService.create(category2);
   }
}
