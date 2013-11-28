package ru.bramblehorse.cms.controller;

import org.hibernate.Hibernate;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.commerce.CatalogCategory;
import ru.bramblehorse.cms.model.commerce.CatalogCategoryFilter;
import ru.bramblehorse.cms.model.commerce.FilterCriterion;
import ru.bramblehorse.cms.model.commerce.Item;
import ru.bramblehorse.cms.model.content.Category;
import ru.bramblehorse.cms.model.content.Content;
import ru.bramblehorse.cms.model.content.LinkContent;
import ru.bramblehorse.cms.model.security.Account;
import ru.bramblehorse.cms.model.security.TomcatRole;
import ru.bramblehorse.cms.service.AbstractService;
import ru.bramblehorse.cms.service.CategoryService;
import ru.bramblehorse.cms.service.SecurityService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

    private AbstractService<CatalogCategory> catalogCategoryService;
    private AbstractService<CatalogCategoryFilter> catalogCategoryFilterService;
    private AbstractService<FilterCriterion> filterCriterionService;
    private AbstractService<Item> itemService;

    private SecurityService<Account> accountService;
    private SecurityService<TomcatRole> tomcatRoleService;
    private Properties settings;

    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();

        categoryService = (CategoryService)context.getBean("categoryService");
        linkContentService = (AbstractService<LinkContent>) context.getBean("linkContentService");

        catalogCategoryService = (AbstractService<CatalogCategory>) context.getBean("catalogCategoryService");
        catalogCategoryFilterService = (AbstractService<CatalogCategoryFilter>) context.getBean("catalogCategoryFilterService");
        filterCriterionService = (AbstractService<FilterCriterion>) context.getBean("filterCriterionService");
        itemService = (AbstractService<Item>) context.getBean("itemService");

        accountService = (SecurityService<Account>) context.getBean("accountService");
        tomcatRoleService = (SecurityService<TomcatRole>) context.getBean("tomcatRoleService");

        settings = new Properties();
        try {
        settings.load(getServletContext().getResourceAsStream("/WEB-INF/classes/settings.properties"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        insertMockValues();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       processGetIndexPage(req, resp);
       return;
    }

    private void processGetIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        Boolean isSettingsChanged = (Boolean)getServletContext().getAttribute("isSettingsChanged");
        if(isSettingsChanged != null){
            if(isSettingsChanged){
                try {
                    settings.load(getServletContext().getResourceAsStream("/WEB-INF/classes/settings.properties"));
                    getServletContext().setAttribute("isSettingsChanged",false);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        String categoryId = req.getParameter("category");
        List<Category> categoryList = categoryService.getVisibleRootCategories();
        Collections.sort(categoryList);
        for(Category c : categoryList) {
            c.sortChildren();
        }
        req.setAttribute("categoryList",categoryList);
        if("true".equalsIgnoreCase(settings.getProperty("show_footer_links"))){
            List<LinkContent>tempLinkList = linkContentService.getAll();
            List<LinkContent>linkList = new ArrayList<LinkContent>();
            for(LinkContent l : tempLinkList){
                if(l.getIsVisible())
                    linkList.add(l);
            }
            Collections.sort(linkList);
            req.setAttribute("linkList", linkList);
            String footerLinksSize = settings.getProperty("footer_links_size");
            req.setAttribute("footerLinksSize", footerLinksSize);
        }

        if(categoryList != null) {
            if((!categoryList.isEmpty()) && (categoryId == null || categoryId.isEmpty())) {
                categoryId = String.valueOf(categoryList.get(0).getId());
            }
        }
        if(categoryId != null && !categoryId.isEmpty()) {
            List<Content> tempList = categoryService.getById(Integer.parseInt(categoryId)).getContent();
            List<Content> contentList = new ArrayList<Content>();
            for(Content c : tempList){
                if(c.getIsVisible())
                    contentList.add(c);
            }
            Collections.sort(contentList);
            req.setAttribute("contentList",contentList);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/index.jsp");
        rd.forward(req,resp);
    }

    private void insertMockValues(){

        CatalogCategory category1 = new CatalogCategory();
        category1.setCatalogCategoryName("category1");

        Item item1 = new Item();
        item1.setItemDescription("item desc");
        item1.setItemName("item name");
        item1.setItemPrice(35);
        List<Item> items = new ArrayList<Item>();
        items.add(item1);
        category1.setCatalogCategoryItems(items);
        catalogCategoryService.create(category1);

    }
}
