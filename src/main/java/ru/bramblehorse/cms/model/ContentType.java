package ru.bramblehorse.cms.model;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 20.09.13
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public enum ContentType {
    TEXT,IMAGE,TABLE,WYSIWYG,NONE;

    public static ContentType getType(String s) {
        if("text".equalsIgnoreCase(s))
            return TEXT;
        if("table".equalsIgnoreCase(s))
            return TABLE;
        if("image".equalsIgnoreCase(s))
            return IMAGE;
        if("wysiwyg".equalsIgnoreCase(s))
            return WYSIWYG;
        return NONE;
    }
}
