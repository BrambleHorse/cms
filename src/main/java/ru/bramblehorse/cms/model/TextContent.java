package ru.bramblehorse.cms.model;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 29.08.13
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */
public class TextContent extends Content {
    private StringBuffer text;

    public StringBuffer getText() {
        return text;
    }

    public void setText(StringBuffer text) {
        this.text = text;
    }
}
