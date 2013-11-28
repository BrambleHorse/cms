package ru.bramblehorse.cms.model.commerce;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 24.11.13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name="item_price")
    private Integer itemPrice;

    @Column(name = "item_image_path")
    private String itemImagePath;

    @Column(name = "item_thumb_image_path")
    private String itemThumbImagePath;

    @Column(name = "item_image_file_path")
    private String itemImageFilePath;

    @Column(name = "item_thumb_image_file_path")
    private String itemThumbImageFilePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand itemBrand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_category_id")
    private CatalogCategory itemCategory;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemImagePath() {
        return itemImagePath;
    }

    public void setItemImagePath(String itemImagePath) {
        this.itemImagePath = itemImagePath;
    }

    public String getItemThumbImagePath() {
        return itemThumbImagePath;
    }

    public void setItemThumbImagePath(String itemThumbImagePath) {
        this.itemThumbImagePath = itemThumbImagePath;
    }

    public String getItemImageFilePath() {
        return itemImageFilePath;
    }

    public void setItemImageFilePath(String itemImageFilePath) {
        this.itemImageFilePath = itemImageFilePath;
    }

    public String getItemThumbImageFilePath() {
        return itemThumbImageFilePath;
    }

    public void setItemThumbImageFilePath(String itemThumbImageFilePath) {
        this.itemThumbImageFilePath = itemThumbImageFilePath;
    }

    public Brand getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(Brand itemBrand) {
        this.itemBrand = itemBrand;
    }

    public CatalogCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(CatalogCategory itemCategory) {
        this.itemCategory = itemCategory;
    }
}
