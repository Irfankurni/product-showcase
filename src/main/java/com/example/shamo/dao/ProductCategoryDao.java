package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.ProductCategories;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCategoryDao extends BaseEntityManager {

	public List<ProductCategories> findAllCategory() throws Exception {
		String sql = "SELECT * FROM product_categories pc";
		List<ProductCategories> categories = em.createNativeQuery(sql, ProductCategories.class).getResultList();
		return categories;
	}

	public ProductCategories findByIdCategory(Long id) throws Exception {
		ProductCategories category = em.find(ProductCategories.class, id);
		return category;
	}

	public ProductCategories insertCategory(ProductCategories categories) throws Exception {
		em.persist(categories);
		return categories;
	}

	public ProductCategories updateCategory(ProductCategories categories) throws Exception {
		ProductCategories updatedCategory = em.merge(categories);
		em.flush();
		return updatedCategory;
	}

	public Boolean deleteCategory(Long id) throws Exception {
		String sql = "DELETE FROM product_categories WHERE id = :id";
		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

}
