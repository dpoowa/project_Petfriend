package com.tech.petfriends.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tech.petfriends.admin.dto.ProductListDto;
import com.tech.petfriends.admin.mapper.AdminProductDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.admin.service.product.AdminProductAddService;
import com.tech.petfriends.admin.service.product.AdminProductDetailService;
import com.tech.petfriends.admin.service.product.AdminProductListService;
import com.tech.petfriends.admin.service.product.AdminProductModifyService;

@RestController
@RequestMapping("/admin")
public class AdminProductRestController {

	AdminExecuteModel adminExcuteM;
	
	@Autowired
	AdminProductDao adminProductDao;

	//관리자페이지 상품리스트 조회
	@PostMapping("/product/list")
	public List<ProductListDto> productList(@RequestBody Map<String, Object> data,Model model) {
		model.addAllAttributes(data);
		
		adminExcuteM = new AdminProductListService(adminProductDao);
		adminExcuteM.execute(model);
		
		@SuppressWarnings("unchecked")
		List<ProductListDto> productList = (List<ProductListDto>) model.getAttribute("productList");
		
		return productList;
	}
	
	//관리자페이지 상품 등록
	@PostMapping("/product/add")
	 public void productAdd(
			 	@RequestParam Map<String, Object> data,
		        @RequestParam(required = false) MultipartFile[] mainImages,
		        @RequestParam(required = false) MultipartFile[] desImages,
		        @RequestParam String options,
		        Model model) {
		
		// Model에 데이터 추가
		model.addAllAttributes(data);
	    model.addAttribute("mainImages", mainImages);
	    model.addAttribute("desImages", desImages);
	    model.addAttribute("options", options);
		
		adminExcuteM = new AdminProductAddService(adminProductDao);
		adminExcuteM.execute(model);
		
	}
	
	@GetMapping("/product/detail")
	public Map<String, Object> productDetail(HttpServletRequest request, Model model) {
		
		String proCode = request.getParameter("proCode");
		model.addAttribute("proCode",proCode);
		
		adminExcuteM = new AdminProductDetailService(adminProductDao);
		adminExcuteM.execute(model);
		
		Map<String, Object> data = new HashMap<>();
		data.put("pro", model.getAttribute("pro"));
		data.put("img", model.getAttribute("img"));
		data.put("opt", model.getAttribute("opt"));
		
		return data;
	}
	
	//관리자페이지 상품 등록
		@PostMapping("/product/modify")
		 public void productModify(
				 	@RequestParam Map<String, Object> data,
			        @RequestParam(required = false) MultipartFile[] mainImages,
			        @RequestParam(required = false) MultipartFile[] desImages,
			        @RequestParam(required = false) String[] removeImages,
			        @RequestParam(required = false) List<String> mainImagesPath,
			        @RequestParam(required = false) List<String> desImagesPath,
			        @RequestParam String options,
			        Model model) {
			
			// Model에 데이터 추가
			model.addAllAttributes(data);
		    model.addAttribute("mainImages", mainImages);
		    model.addAttribute("desImages", desImages);
		    model.addAttribute("removeImages", removeImages);
		    model.addAttribute("options", options);
		    model.addAttribute("mainImagesPath", mainImagesPath);
		    model.addAttribute("desImagesPath", desImagesPath);
			
			adminExcuteM = new AdminProductModifyService(adminProductDao);
			adminExcuteM.execute(model);
			
		}
	
}
