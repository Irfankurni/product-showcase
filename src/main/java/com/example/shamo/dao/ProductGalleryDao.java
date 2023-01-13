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

	public ProductGalleries findByIdGallery(Long id) throws Exception {
		ProductGalleries gallery = em.find(ProductGalleries.class, id);
		return gallery;
	}

	public ProductGalleries findByProduct(Long productId) throws Exception {
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

	public List<ProductGalleries> findByProductId(Long productId) throws Exception {
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

	public Boolean deleteGallery(Long id) throws Exception {
		String sql = "DELETE FROM product_galleries WHERE id = :id";
		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

	public Boolean deleteByProduct(Long productId) throws Exception {
		String sql = "DELETE FROM product_galleries WHERE product_id = :productId";
		int result = em.createNativeQuery(sql).setParameter("productId", productId).executeUpdate();
		return result > 0;
	}


}
