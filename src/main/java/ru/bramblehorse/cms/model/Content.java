package ru.bramblehorse.cms.model;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 30.08.13
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
public class Content {
    private Integer id;
    private String ownerPage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerPage() {
        return ownerPage;
    }

    public void setOwnerPage(String ownerPage) {
        this.ownerPage = ownerPage;
    }

}
