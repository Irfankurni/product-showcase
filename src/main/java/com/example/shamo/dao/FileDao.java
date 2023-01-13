package com.example.shamo.dao;

import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.Files;
import org.springframework.stereotype.Repository;

@Repository
public class FileDao extends BaseEntityManager {

	public Files findById(String id) throws Exception {
		Files file = em.find(Files.class, id);
		return file;
	}

	public Files insert(Files files) throws Exception {
		em.persist(files);
		return files;
	}

	public Boolean delete(String id) throws Exception {
		String sql = "DELETE FROM files WHERE id = :id";
		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

}
