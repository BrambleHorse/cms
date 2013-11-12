package ru.bramblehorse.cms.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    private File settingsFile;

    @Override
    public void init() throws ServletException {

        settings = new Properties();
        try {

            settingsFile = new File(getServletContext().getRealPath("/WEB-INF/classes/settings.properties"));
            settings.load(new FileInputStream(settingsFile));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String settingsMode = req.getParameter("settingsMode");
        if("links".equalsIgnoreCase(settingsMode)){
            processGetLinkSettings(req,resp);
            return;
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

    private void processGetLinkSettings(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {

        req.setAttribute("showFooterLinks", settings.getProperty("show_footer_links"));
        req.setAttribute("footerLinksSize", settings.getProperty("footer_links_size"));
        req.setAttribute("adminAction", "settings_links");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req,resp);
    }

    private void processLinkSettings(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {

        String showFooterLinks = req.getParameter("showFooterLinks");
        String footerLinksSize = req.getParameter("footerLinksSize");
        if("true".equalsIgnoreCase(showFooterLinks)){
           settings.setProperty("show_footer_links","true");
        }
        else {
            settings.setProperty("show_footer_links","false");
        }
        settings.setProperty("footer_links_size", footerLinksSize);
        settings.store(new FileOutputStream(settingsFile),"Settings changed:");
        getServletContext().setAttribute("isSettingsChanged",true);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin/admin_index.jsp");
        rd.forward(req,resp);
    }

}
