package com.javed.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javed.entity.State;
import com.javed.repo.ProductRepository;
import com.javed.entity.Customer;
import com.javed.entity.Location;
import com.javed.entity.Product;
import com.javed.service.CustomerService;
import com.javed.service.StateAndCityService;
import com.javed.service.indexService;

@Controller
@SessionAttributes("customerOb")
public class StateAndCityController {
	
	@Autowired
	private StateAndCityService service;
	
	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private CustomerService custService;
	
	
	
	/* 1.get all country records */
	@GetMapping("/")
	public String loadForm(Model model,Principal p) {
		Map<Integer,String> countryMap=service.getAllCountries();
		State country=new State();
		model.addAttribute("country", country);
		model.addAttribute("countryMap", countryMap);
		
		/*get all active product*/
		List<Product> list = repo.findByIsAvailable(true);
		model.addAttribute("listActive",list);
		
		
		//for user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "Index";
        }
		
        else {
		String email = p.getName();
		System.out.println("--------->"+email);
		Customer customer= custService.getByCustomerEmail(email).get();
		model.addAttribute("customerOb",customer);
		//session.setMaxInactiveInterval(30);
		System.out.println("Session Data is there"+customer);
		model.addAttribute("title","Javed Shopping Mall");
		return "Index";
        }
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	/* 2.get all States Records*/
	@RequestMapping("/getStates")
	@ResponseBody
	public Map<Integer,String> getCititesById(@RequestParam("cid")Integer countryId){
		return service.getStatesByCountryId(countryId);
		
	}
		
	/* 4.save the location */
	@PostMapping("/save")
	public  String saveLocation(@ModelAttribute Location location,Model model) {
		service.saveLocation(location);
		return "redirect:/";	
	}

}
