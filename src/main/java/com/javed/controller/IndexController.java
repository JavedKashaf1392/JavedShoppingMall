package com.javed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javed.entity.Product;
import com.javed.repo.ProductRepository;
import com.javed.service.indexService;

import lombok.Getter;

@Controller
@RequestMapping("index")
public class IndexController {
	
	@Autowired
	private indexService service;
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping("/view")
	public String getAllActive(Model model) {
		List<Product> list = repo.findByIsAvailable(true);
		model.addAttribute("listActive",list);
		System.out.println(list);
		return "Index";
		
	}
	
	

}
