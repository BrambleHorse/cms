package ru.bramblehorse.cms.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 07.11.13
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 */
public class SettingsAdminServlet extends HttpServlet {

    private Properties settings;

    @Override
    public void init() throws ServletException {

        settings = new Properties();
        try {
            settings.load(getServletContext().getResourceAsStream("/WEB-INF/classes/settings.properties"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String settingsMode = req.getParameter("settingsMode");
        if("links".equalsIgnoreCase(settingsMode)){
            processLinkSettings(req,resp);
            return;
        }

    }

    private void processLinkSettings(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {

        String showFooterLinks = req.getParameter("showFooterLinks");
        String footerLinksSize = req.getParameter("footerLinksSize");
        if("true".equalsIgnoreCase(showFooterLinks)){
           settings.setProperty("show_footer_links","true");
        }
        if("false".equalsIgnoreCase(showFooterLinks)){
            settings.setProperty("show_footer_links","false");
        }
        settings.setProperty("footer_links_size", footerLinksSize);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req,resp);
    }

}
