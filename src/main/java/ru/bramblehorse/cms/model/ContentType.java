package ru.bramblehorse.cms.model;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 20.09.13
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public enum ContentType {
    TEXT,IMAGE,TABLE,NONE;

    public static ContentType getType(String s) {
        if("text".equals(s) || "TEXT".equals(s))
            return TEXT;
        if("table".equals(s) || "TABLE".equals(s))
            return TABLE;
        if("image".equals(s) || "IMAGE".equals(s))
            return IMAGE;
        return NONE;
    }
}
