package com.example.shamo.dto.product;

import com.example.shamo.dto.productgallery.ProductGalleryData;

import java.util.List;

public class ProductData {

	private String id;
	private String productName;
	private String categoryId;

	private String categoryName;

	private float price;
	private String description;
	private String tags;

	private List<ProductGalleryData> galleries;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<ProductGalleryData> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<ProductGalleryData> galleries) {
		this.galleries = galleries;
	}

}
