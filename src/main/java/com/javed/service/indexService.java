package com.javed.service;

import java.util.List;

import com.javed.entity.Product;

public interface indexService {
	
	 List<Product> getAllActiveProducts(boolean isAvailable) ;

}
