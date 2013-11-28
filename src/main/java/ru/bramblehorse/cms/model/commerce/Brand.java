package ru.bramblehorse.cms.model.commerce;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 28.11.13
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "itemBrand")
    private List<Item> relatedItems;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Item> getRelatedItems() {
        return relatedItems;
    }

    public void setRelatedItems(List<Item> relatedItems) {
        this.relatedItems = relatedItems;
    }
}
