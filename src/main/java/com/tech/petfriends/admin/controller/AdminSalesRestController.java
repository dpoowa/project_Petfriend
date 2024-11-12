package com.tech.petfriends.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tech.petfriends.admin.mapper.AdminSalesDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.admin.service.AdminSalesDetailService;

@RestController
public class AdminSalesRestController {
	
	@Autowired
	AdminSalesDao adminSalesDao;
	
	AdminExecuteModel adminExcuteM;
	
	@PostMapping("/salesDetail")
	@ResponseBody
	public void salesDetail(@RequestBody Map<String, Object> data, Model model) {
		
		model.addAllAttributes(data);
		adminExcuteM = new AdminSalesDetailService(adminSalesDao);
		adminExcuteM.execute(model);
		
	}
}
