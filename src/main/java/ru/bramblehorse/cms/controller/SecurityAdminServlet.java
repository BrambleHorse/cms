package ru.bramblehorse.cms.controller;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import ru.bramblehorse.cms.model.security.Account;
import ru.bramblehorse.cms.model.security.TomcatRole;
import ru.bramblehorse.cms.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 18.11.13
 * Time: 23:00
 * To change this template use File | Settings | File Templates.
 */
public class SecurityAdminServlet extends HttpServlet {

    private WebApplicationContext context;
    private SecurityService<Account> accountService;
    private SecurityService<TomcatRole> tomcatRoleService;


    @Override
    public void init() throws ServletException {

        context = ContextLoaderListener.getCurrentWebApplicationContext();
        accountService = (SecurityService<Account>) context.getBean("accountService");
        tomcatRoleService = (SecurityService<TomcatRole>) context.getBean("tomcatRoleService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accountsAction = req.getParameter("accountsAction");
        if("create".equalsIgnoreCase(accountsAction)){

            processGetCreateAccount(req,resp);
            return;
        }
        if("edit".equalsIgnoreCase(accountsAction)){

            processGetEditAccount(req, resp);
            return;

        }
        if("delete".equalsIgnoreCase(accountsAction)){

            processGetDeleteAccount(req, resp);
            return;
        }

        processGetNoActionAccount(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accountsAction = req.getParameter("accountsAction");

        if("create".equalsIgnoreCase(accountsAction)){

            processPostCreateAccount(req,resp);
            return;
        }
        if("edit".equalsIgnoreCase(accountsAction)){

            processPostEditAccount(req, resp);
            return;
        }

    }

    private void processGetNoActionAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Account> accountsList = accountService.getAll();
        req.setAttribute("accountsList", accountsList);
        req.setAttribute("adminAction", "accounts");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }
    private void processGetCreateAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("adminAction", "new_account");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetEditAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        Account account = accountService.getByName(userName);
        req.setAttribute("account", account);
        req.setAttribute("adminAction", "edit_account");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processGetDeleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accountToDelete = req.getParameter("userName");
        if (accountToDelete != null) {

            if (!"admin".equals(accountToDelete)) {
                accountService.delete(accountToDelete);
            }
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostCreateAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String userPasswordRepeat = req.getParameter("userPasswordRepeat");
        if(userName != null && userPassword != null && userPasswordRepeat != null){
            if(!userName.isEmpty() && !userPassword.isEmpty() && userPassword.equals(userPasswordRepeat)){

                List<Account>accountsList = accountService.getAll();
                for(Account a : accountsList) {
                    if(a.getUserName().equals(userName))
                        return;
                }

                Account temp = new Account();
                TomcatRole tempRole = tomcatRoleService.getByName("administrator");
                Set<TomcatRole>roles = new HashSet<TomcatRole>();
                roles.add(tempRole);
                temp.setUserName(userName);
                temp.setUserPassword(userPassword);
                temp.setRoles(roles);
                accountService.create(temp);
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

    private void processPostEditAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String userPasswordRepeat = req.getParameter("userPasswordRepeat");

        if(userName != null && userPassword != null && userPasswordRepeat != null){
            if(!userName.isEmpty() && !userPassword.isEmpty() && userPassword.equals(userPasswordRepeat)){

                Account temp = new Account();
                TomcatRole tempRole = tomcatRoleService.getByName("administrator");
                Set<TomcatRole>roles = new HashSet<TomcatRole>();
                roles.add(tempRole);
                temp.setUserName(userName);
                temp.setUserPassword(userPassword);
                temp.setRoles(roles);
                accountService.edit(temp);
            }
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req, resp);
    }

}
