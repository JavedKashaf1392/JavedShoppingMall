package com.javed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.javed.entity.Product;

public interface ProductService {
	
	public Integer saveProduct(Product product);
	
	public Page<Product> getAllProductsByCategory(String productCategory,int pageNum);
	
	/*public Page<Product> getAllProducts(String keyword,int pageNum);*/
	public Page<Product> getAllProducts(int pageNum,String keyword);
	
	//for charts
	public List<Product> getAllProducts1();
	
	boolean deleteProduct(Integer cid);

	public Optional<Product> getOneProduct(Integer id);
	
	public boolean deleteWelcomeByIds(Integer[] ids);
	
	
	//for delete multiple records by checks
	public List<Product> getByMultipleIds(Integer[] ids);


	/*boolean deleteProduct(Integer id);*/
	//jfree charts
	public List<Object[]> getShipmentCodeMode();

}
