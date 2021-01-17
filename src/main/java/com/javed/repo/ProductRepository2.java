package com.javed.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.javed.entity.Product;

public interface ProductRepository2 extends JpaRepository<Product, Integer>{
	
	
	///for delete we well write this query 
	 public List<Product> findByProductIdIn(Integer[] productId);
	
	
	//for gfreem charts
	@Query("SELECT ST.productCategory,COUNT(ST.productCategory) FROM Product ST GROUP BY ST.productCategory")
	public List<Object[]> getShipmentCodeMode();


}
