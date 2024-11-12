package com.tech.petfriends.admin.controller;

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
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;
import com.tech.petfriends.admin.service.product.AdminProductAddService;
import com.tech.petfriends.admin.service.product.AdminProductDetailService;
import com.tech.petfriends.admin.service.product.AdminProductListService;
import com.tech.petfriends.admin.service.product.AdminProductModifyService;

@RestController
@RequestMapping("/admin")
public class AdminProductRestController {

	AdminExecuteModel adminExcuteM;

	AdminExecuteModelRequest adminExcuteMR;
	
	@Autowired
	AdminProductDao adminProductDao;

	//관리자페이지 상품리스트 조회
	@SuppressWarnings("unchecked")
	@PostMapping("/product/list")
	public List<ProductListDto> productList(@RequestBody Map<String, Object> data, Model model) {
		
		adminExcuteM = new AdminProductListService(adminProductDao, data);
		adminExcuteM.execute(model);
		
		return (List<ProductListDto>) model.getAttribute("productList");
	}
	
	//관리자페이지 상품 등록
	@PostMapping("/product/add")
	 public void productAdd(
			 	@RequestParam Map<String, Object> data,
		        @RequestParam(required = false) MultipartFile[] mainImages,
		        @RequestParam(required = false) MultipartFile[] desImages,
		        @RequestParam String options,
		        Model model) {
		
		adminExcuteM = new AdminProductAddService(adminProductDao, data, mainImages, desImages, options);
		adminExcuteM.execute(model);
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/product/detail")
	public Map<String, Object> productDetail(HttpServletRequest request, Model model) {
		adminExcuteMR = new AdminProductDetailService(adminProductDao);
		adminExcuteMR.execute(model, request);
		return (Map<String, Object>) model.getAttribute("data");
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

			adminExcuteM = new AdminProductModifyService(adminProductDao, data, mainImages,
					desImages, removeImages, mainImagesPath, desImagesPath, options);
			adminExcuteM.execute(model);
			
		}
	
}
