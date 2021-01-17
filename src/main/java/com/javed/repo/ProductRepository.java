package com.javed.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.javed.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
	
	
	@Query("SELECT p FROM Product p WHERE " +"CONCAT(p.productId,p.productName,p.productPrice,"
	+ "p.productQyt,p.discount,p.isAvailable,p.productCategory,p.finalPrice,p.size,p.tax)" + " LIKE %?1%")
	public Page<Product> findAll(String keyword,Pageable pageable);
	
	
	
	
	///for delete we well write this query 
//	 public Page<Product> findByProductIdIn(Integer[] productId);
	 
	 public Page<Product> findByProductCategory(String productCategory, Pageable pageable);
	
	
	//for gfreem charts
	@Query("SELECT ST.productCategory,COUNT(ST.productCategory) FROM Product ST GROUP BY ST.productCategory")
	public List<Object[]> getShipmentCodeMode();
	
	//get records to Index page by Actice
	@Query("FROM Product WHERE isAvailable=:isAvailable")
	public List<Product> findByIsAvailable(boolean isAvailable);
	


}
