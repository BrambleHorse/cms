package ru.bramblehorse.cms.model;

import javax.swing.*;


/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 20.09.13
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
public class TableContent extends Content {
    private JTable table;

    @Override
    public ContentType getType() {
        return ContentType.TABLE;
    }


}
