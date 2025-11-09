package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{

	@Query(value ="SELECT * FROM category ORDER BY category", nativeQuery = true)
	List<Category> findAll();
	
	@Query(value ="SELECT * FROM category WHERE status = 1 ORDER BY category", nativeQuery = true)
	List<Category> findActive();

	List<Category> findByStatus(Integer status);

	List<Category> findByStatusOrderByCategoryAsc(Integer status);

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO category(category, tag, status) VALUES (:category, :tag, 1);", nativeQuery = true)
	void create(@Param("category") String category, @Param("tag") String tag);

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value ="UPDATE category SET category = :category, tag = :tag WHERE category_id = :category_id",
	nativeQuery = true)
		void update(@Param("category_id") Integer category_id, @Param("category") String category,
	@Param("tag") String tag);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value ="UPDATE category SET status = :status WHERE category_id = :category_id", nativeQuery = true)
	void setStatus(@Param("category_id") Integer category_id, @Param("status") Integer status);
	
	// enable & disable
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value ="UPDATE category SET status = 1 WHERE category_id = :category_id", nativeQuery = true)
	void enable(@Param("category_id") Integer category_id);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value ="UPDATE category SET status = 0 WHERE category_id = :category_id", nativeQuery = true)
	void disable(@Param("category_id") Integer category_id);
	
	
	
}