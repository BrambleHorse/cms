package ru.bramblehorse.cms.facade;

import ru.bramblehorse.cms.model.commerce.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 16.12.13
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public interface CatalogFilterFacade {

    public List<Item> processItemsList(HttpServletRequest req, HttpServletResponse resp, Integer offset,
                                       Integer numberOfRecords, CatalogCategory category)
            throws ServletException, IOException;
}
