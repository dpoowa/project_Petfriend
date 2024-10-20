package com.tech.petfriends.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tech.petfriends.product.dao.ProductDao;
import com.tech.petfriends.product.dto.ProductListViewDto;
import com.tech.petfriends.product.service.ProductDetailService;
import com.tech.petfriends.product.service.ProductListViewService;
import com.tech.petfriends.product.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductDao productDao;
	
	ProductService productService;
	
	//제품리스트 페이지 _ productlist.jsp
	@GetMapping("/productlist")
	public String productlist(Model model) {
		
		
		return "/product/productlist";
	}
	
	//제품리스트 불러오기 ajax용 _ productlist.jsp
	@PostMapping("/productlistview")
	@ResponseBody
	public ResponseEntity<List<ProductListViewDto>> productlistview(@RequestBody Map<String, Object> param, Model model){
		
		model.addAllAttributes(param);
		 
		productService = new ProductListViewService(productDao);
		productService.execute(model);
		
		// model에서 "list" 속성 가져오기
        List<ProductListViewDto> productList = (List<ProductListViewDto>) model.asMap().get("list");
		
		return ResponseEntity.ok(productList);
	}
	
	//제품상세페이지
	@GetMapping("/productDetail")
	public String productDetail(@RequestParam("code") String productCode, Model model ) {
		
		model.addAttribute("productCode",productCode);
		
		productService = new ProductDetailService(productDao);
		productService.execute(model);
		
		
		return "product/productDetail";
	}

}
