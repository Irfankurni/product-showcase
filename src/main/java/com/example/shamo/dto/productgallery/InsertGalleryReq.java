package com.example.shamo.dto.productgallery;

import java.util.List;

public class InsertGalleryReq {

    private String productId;
    private List<InsertGalleryData> galleries;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<InsertGalleryData> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<InsertGalleryData> galleries) {
        this.galleries = galleries;
    }
}
