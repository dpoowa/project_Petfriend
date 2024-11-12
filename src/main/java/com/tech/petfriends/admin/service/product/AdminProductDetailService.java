package com.tech.petfriends.admin.service.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.petfriends.admin.dto.ProductDetailImgDto;
import com.tech.petfriends.admin.dto.ProductDetailOptDto;
import com.tech.petfriends.admin.dto.ProductDetailProDto;
import com.tech.petfriends.admin.mapper.AdminProductDao;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;

public class AdminProductDetailService implements AdminExecuteModelRequest {

	private AdminProductDao adminProductDao;

	public AdminProductDetailService(AdminProductDao adminProductDao) {
		this.adminProductDao = adminProductDao;
	}

	@Override
	public void execute(Model model, HttpServletRequest request) {
		String proCode = request.getParameter("proCode");

		//System.out.println("나는 코드가말이야 ~~ "+proCode);
		
		ProductDetailProDto pro = adminProductDao.adminDetatilPro(proCode);
		
		ProductDetailImgDto img = adminProductDao.adminDetailImg(proCode);
		
		List<ProductDetailOptDto> opt = adminProductDao.adminDetailOpt(proCode);
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("pro", pro); 
		data.put("img", img); 
		data.put("opt", opt); 
		
		model.addAttribute("data", data);
	}

}
