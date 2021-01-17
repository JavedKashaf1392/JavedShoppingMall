package com.javed.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javed.entity.Product;
import com.javed.service.ProductService;
import com.javed.util.ShipmentTypeUtilChart;
import com.javed.view.ProductExcelView;
import com.javed.view.ProductPdfView;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService prdService;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private ShipmentTypeUtilChart util;

	// 1.register product
	@GetMapping("/register")
	public String formLoad(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "AddProduct";
	}
	
	/*@GetMapping("/")
	public String Index(Model model) {
		return "Index";
	}*/

	// 2.save product
	@PostMapping("/save")
	public String SaveProduct(@ModelAttribute Product product, Model model) {

		Integer id = prdService.saveProduct(product);
		
		MultipartFile ProductImage = product.getImage();

		try {
			byte[] bytes = ProductImage.getBytes();
			String name = product.getProductId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
			new FileOutputStream(new File("src/main/resources/static/image/product/" +name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("msg", "Your Prodcut" + id + "Added Successfully");
		return "AddProduct";
	}

	// 3.view all product
	@GetMapping("/view/{pageNum}")
	public String getAllProducts(HttpServletRequest req, Model model,
			@PathVariable(name = "pageNum")  int pageNum,
			@Param("keyword") String keyword
			)
			
			 {

 		   String productCategory = "";
		   String prodStr = req.getParameter("productCategory");

		if (prodStr == null) {
			Page<Product> page  = prdService.getAllProducts(pageNum,keyword);
			List<Product> listProducts = page.getContent();
			
			if (!page.isEmpty()) {
				model.addAttribute("product", page);
				model.addAttribute("currentPage", pageNum);		
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("listProducts", listProducts);
				model.addAttribute("keyword",keyword);
				
				return "viewProduct";
			}
			return "viewProduct";
			 }
		
				 else {
				
						if (prodStr != null && !prodStr.equals("")) {
						productCategory = prodStr;
						}
						Page<Product> product = prdService.getAllProductsByCategory(productCategory,pageNum);
						System.out.println(product + "this is product method");
				//				model.addAttribute("product", product);
						List<Product> listProducts = product.getContent();
						model.addAttribute("product", product);
						model.addAttribute("currentPage", pageNum);		
						model.addAttribute("totalPages", product.getTotalPages());
						model.addAttribute("totalItems", product.getTotalElements());
						model.addAttribute("listProducts", listProducts);
						return "viewProduct";
				 }
			 }

			
	
	
	
	/// 4.singel delete
	@RequestMapping(value = "deletebatchs/{ids}", method = RequestMethod.GET)

	String deleteProduct1(@PathVariable Integer ids, Model model) {
		boolean isDeleted = prdService.deleteProduct(ids);

		if (isDeleted) {
			return "redirect:/product/view/1";
		}
		return null;
	}

	// 5.multiple delete
	@RequestMapping(value = "deletebatch/{ids}", method = RequestMethod.GET)
	String deleteProduct(@PathVariable Integer[] ids, Model model) {
		boolean isDeleted = prdService.deleteWelcomeByIds(ids);
		if (isDeleted) {
			return "redirect:/product/view/1";
		}
		return null;
	}

	
	//6.edit
	@RequestMapping("/editrecors/{ids}")
	public String showEdit(@PathVariable("ids") Integer id, Model model) {
		// load Model class object from DB by using Id
		String page=null;
		Product prod=null;
		Optional<Product> p = prdService.getOneProduct(id);
		if(p.isPresent()) {
			prod=p.get();
			model.addAttribute("product", p);
			page="EditProduct";
		}else {
			page="viewProduct";
		}
		

		return page;
	}

	
	//7.update the product
	@PostMapping("/update")
	public String UpdateProduct(@ModelAttribute Product product, Model model) {

		Integer id = prdService.saveProduct(product);
		model.addAttribute("msg", "Your Prodcut" + id + "Added Successfully");
		return "AddProduct";
	}
	
	
	//export to excel
	// 8. Export Data to Excel File
		@GetMapping("/excel")
		public ModelAndView exportToExcel() {
			// create MAV object
			ModelAndView m = new ModelAndView();
			// set MAV view
			m.setView(new ProductExcelView());

			// send data to MAV view
			m.addObject("obs", prdService.getAllProducts1());
			return m;
		}
		
		
		// 9. Export One row to Excel File
		/*
		 * @GetMapping("excel/{id}") public ModelAndView exportOneExcel(@PathVariable
		 * Integer[] id) { ModelAndView m = new ModelAndView(); m.setView(new
		 * ProductExcelView()); Optional<Product> opt = prdService.getByMultipleIds(id);
		 * if (opt.isPresent()) { m.addObject("obs", Arrays.asList(opt.get())); } return
		 * m; }
		 */
		
		
		@GetMapping("excel/{id}")
		public ModelAndView exportOneExcel(@PathVariable Integer[] id) {
			ModelAndView m = new ModelAndView(); 
			m.setView(new ProductExcelView());
					 m.addObject("obs",prdService.getByMultipleIds(id)); 
				 return m;
					 }
		
		
		// 10. Export One row to PDF File
		@GetMapping("pdf/{id}")
		public ModelAndView exportOnePdf(@PathVariable Integer[] id) {
			ModelAndView m = new ModelAndView();
			m.setView(new ProductPdfView());
			m.addObject("list",prdService.getByMultipleIds(id));
			return m;
		}
		
		@GetMapping("/pdf")
		public ModelAndView ShowPdf() {
			ModelAndView m=new ModelAndView();
			m.setView(new ProductPdfView());
			List<Product> list = prdService.getAllProducts1();
			m.addObject("list",list);
			return m;
		}
	
		
		//11.Generated Charts
		@GetMapping("/charts")
		public String generateChart() {
			//data to show at chart 
			List<Object[]> list = prdService.getShipmentCodeMode();
			//Dynamic Temp folder locations 
			String location = context.getRealPath("/");
		  //call chart methods
		  util.generateBarChart(location, list);
		  util.generatePieChart(location, list);
		   return "ProductCharts";
		}
		
		        //google chart
				@GetMapping("/gcharts")
				public @ResponseBody List<Object[]> generateGoogleChart() {
					//data to show at chart 
					List<Object[]> list = prdService.getShipmentCodeMode();
			        return list;
				}
		
}
