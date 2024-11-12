package com.tech.petfriends.admin.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.petfriends.admin.mapper.AdminCommunityDao;
import com.tech.petfriends.admin.mapper.AdminPageDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;
import com.tech.petfriends.admin.service.community.AdminCommunityReportService;
import com.tech.petfriends.admin.service.community.AdminCommunitySearchService;
import com.tech.petfriends.admin.service.community.AdminCommunityStatUpReService;

@RestController
@RequestMapping("/admin")
public class AdminCommunityRestController {

	@Autowired
	AdminPageDao adminDao;

	@Autowired
	AdminCommunityDao communityDao;
		
	AdminExecuteModel adminExecuteM;

	AdminExecuteModelRequest adminExecuteMR;

	@SuppressWarnings("unchecked")
	@PostMapping("/community")
	public Map<String, Object> searchCommunityBoard(@RequestBody Map<String, String> requestData, Model model) {
		
		adminExecuteM = new AdminCommunitySearchService(communityDao, requestData);
		adminExecuteM.execute(model);

	    return (Map<String, Object>) model.getAttribute("response");
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/report")
	public Map<String, Object> searchReport(@RequestBody Map<String, String> requestData, Model model) {
		
		adminExecuteM = new AdminCommunityReportService(communityDao, requestData);
		adminExecuteM.execute(model);

	    return (Map<String, Object>) model.getAttribute("response");
	}
	
	  @PostMapping("/updateReportStatus")
	public void updateReportStatus(@RequestBody ArrayList<Map<String, Object>> selectedReport, Model model)  {
		  model.addAttribute("selectedReport", selectedReport);
		  
		  adminExecuteM = new AdminCommunityStatUpReService(communityDao, selectedReport);
		  adminExecuteM.execute(model);		  
	  }
}
