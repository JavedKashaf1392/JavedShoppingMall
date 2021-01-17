package com.javed.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javed.entity.Product;
import com.javed.repo.ProductRepository;
import com.javed.repo.ProductRepository2;
import com.javed.service.ProductService;



@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository pdRepo;
	
	@Autowired
	private ProductRepository2 pdRepo2;

	@Override
	public Integer saveProduct(Product product) {
		return pdRepo.save(product).getProductId();

	}

	@Override
	public Page<Product> getAllProductsByCategory(String productCategory,int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, 10);
			Page<Product> entities =pdRepo.findByProductCategory(productCategory,pageable);
			return  entities;
}
	
	
	@Override
	public Page<Product> getAllProducts(int pageNum,String keyword) {
		Pageable pageable = PageRequest.of(pageNum - 1, 10);
		
		if(keyword != null) {
			return pdRepo.findAll(keyword, pageable);
		}
			return pdRepo.findAll(pageable);
}
	
	
	
	@Override
	public boolean deleteProduct(Integer id) {
		pdRepo.deleteById(id);
		return true;
  
	}

	@Override
	public Optional<Product> getOneProduct(Integer id) {
		return pdRepo.findById(id);
	}

	@Override
	public boolean deleteWelcomeByIds(Integer[] ids) {
		List<Product> list=pdRepo2.findByProductIdIn(ids);
		pdRepo2.deleteInBatch(list);
		return true;
	}
	
	@Override
	public List<Product> getByMultipleIds(Integer[] ids) {
		List<Product> list=pdRepo2.findByProductIdIn(ids);
		return list;
	}
	
	//jfreem charts
	@Override
	public List<Object[]> getShipmentCodeMode() {
		return pdRepo.getShipmentCodeMode();
	}

	@Override
	public List<Product> getAllProducts1() {
		return pdRepo2.findAll();
	}
	

}
