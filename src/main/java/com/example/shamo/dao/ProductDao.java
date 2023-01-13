package com.example.shamo.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.Products;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class ProductDao extends BaseEntityManager {

	public List<LinkedHashMap<String, Object>> findAllProduct(String category) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.id, p.product_name, pc.category, p.price, p.description, p.tags ," +
				"(SELECT file_id FROM product_galleries pg WHERE product_id = p.id LIMIT 1) AS file_id " +
				"FROM products p\n" +
				"LEFT JOIN product_categories pc ON pc.id = p.category_id ");

		if(category != null && !category.isEmpty()) {
			sql.append("WHERE LOWER(pc.category) = LOWER(:category)");
		}

		sql.append("ORDER BY p.created_at DESC");

		Query query = em.createNativeQuery(sql.toString());

		if(category != null && !category.isEmpty()) {
			query.setParameter("category", category);
		}

		List<Object[]> list = query.getResultList();
		List<LinkedHashMap<String, Object>> res = new ArrayList<>();
		list.forEach(o -> {
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			map.put("id", o[0]);
			map.put("productName", o[1]);
			map.put("category", o[2]);
			map.put("price", o[3]);
			map.put("description", o[4]);
			map.put("tags", o[5]);
			map.put("fileId", o[6]);
			res.add(map);
		});

		return res;
	}

	public Products findByIdProduct(Long id) throws Exception {
		Products product = em.find(Products.class, id);
		return product;
	}

	public Products insertProduct(Products products) throws Exception {
		em.persist(products);
		return products;
	}

	public Products updateProduct(Products products) throws Exception {
		Products updatedProduct = em.merge(products);
		em.flush();
		return updatedProduct;
	}

	public Boolean deleteProduct(Long id) throws Exception {
		String sql = "DELETE FROM products WHERE id = :id";
		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}


}
