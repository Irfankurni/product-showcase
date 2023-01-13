package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.ProductGalleries;
import org.springframework.stereotype.Repository;

@Repository
public class ProductGalleryDao extends BaseEntityManager {

	public List<ProductGalleries> findAllGalleries() throws Exception {
		String sql = "SELECT * FROM product_galleries pg";
		List<ProductGalleries> galleries = em.createNativeQuery(sql, ProductGalleries.class).getResultList();
		return galleries;
	}

	public ProductGalleries findByIdGallery(String id) throws Exception {
		ProductGalleries gallery = em.find(ProductGalleries.class, id);
		return gallery;
	}

	public ProductGalleries findByProduct(String productId) throws Exception {
		String sql = "SELECT pg from ProductGalleries pg WHERE pg.product.id = :productId";
		ProductGalleries gallery = null;

		try {
			gallery = em.createQuery(sql, ProductGalleries.class).setParameter("productId", productId)
					.setMaxResults(1)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gallery;
	}

	public List<ProductGalleries> findByProductId(String productId) throws Exception {
		String sql = "SELECT * from product_galleries pg WHERE pg.product_id = :productId";
		List<ProductGalleries> galleries = em.createNativeQuery(sql, ProductGalleries.class)
				.setParameter("productId", productId)
				.getResultList();
		return galleries;
	}

	public ProductGalleries insertGallery(ProductGalleries productGalleries) throws Exception {
		em.persist(productGalleries);
		return productGalleries;
	}

	public Boolean deleteGallery(String id) throws Exception {
		String sql = "DELETE FROM product_galleries WHERE id = :id";
		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

	public Boolean deleteByProduct(String productId) throws Exception {
		String sql = "DELETE FROM product_galleries WHERE product_id = :productId";
		int result = em.createNativeQuery(sql).setParameter("productId", productId).executeUpdate();
		return result > 0;
	}


}
