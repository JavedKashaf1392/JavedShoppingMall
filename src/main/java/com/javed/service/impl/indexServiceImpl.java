package com.javed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javed.entity.Product;
import com.javed.repo.ProductRepository;
import com.javed.service.indexService;

@Service
public class indexServiceImpl implements indexService{
	
	@Autowired
	private ProductRepository repo;

	@Override
	public List<Product> getAllActiveProducts(boolean isAvailable) {
		return repo.findByIsAvailable(isAvailable);
	}

}
