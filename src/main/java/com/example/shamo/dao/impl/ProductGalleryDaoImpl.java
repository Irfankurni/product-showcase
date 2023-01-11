package com.example.shamo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.shamo.dao.ProductGalleryDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.ProductGalleries;

@Repository
public class ProductGalleryDaoImpl extends BaseEntityManager implements ProductGalleryDao {

	@Override
	public List<ProductGalleries> findAllGalleries() throws Exception {
		String sql = "SELECT * FROM product_galleries pg";
		List<ProductGalleries> galleries = em.createNativeQuery(sql, ProductGalleries.class).getResultList();
		return galleries;
	}

	@Override
	public ProductGalleries findByIdGallery(Long id) throws Exception {
		ProductGalleries gallery = em.find(ProductGalleries.class, id);
		return gallery;
	}

	@Override
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

	@Override
	public List<ProductGalleries> findByProductId(Long productId) throws Exception {
		String sql = "SELECT * from product_galleries pg WHERE pg.product_id = :productId";
		List<ProductGalleries> galleries = em.createNativeQuery(sql, ProductGalleries.class)
				.setParameter("productId", productId)
				.getResultList();
		return galleries;
	}

	@Override
	public ProductGalleries insertGallery(ProductGalleries productGalleries) throws Exception {
		em.persist(productGalleries);
		return productGalleries;
	}

	@Override
	public Boolean deleteGallery(Long id) throws Exception {
		String sql = "DELETE FROM product_galleries WHERE id = :id";
		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

	@Override
	public Boolean deleteByProduct(Long productId) throws Exception {
		String sql = "DELETE FROM product_galleries WHERE product_id = :productId";
		int result = em.createNativeQuery(sql).setParameter("productId", productId).executeUpdate();
		return result > 0;
	}


}
