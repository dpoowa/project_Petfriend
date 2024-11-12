package com.tech.petfriends.admin.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.petfriends.admin.dto.ACommunityDto;
import com.tech.petfriends.admin.mapper.AdminCommunityDao;
import com.tech.petfriends.admin.mapper.AdminPageDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;
import com.tech.petfriends.admin.service.community.AdminCommunityReportService;

@RestController
@RequestMapping("/admin")
public class AdminCommunityRestController {

	@Autowired
	AdminPageDao adminDao;

	@Autowired
	AdminCommunityDao communtiyDao;
		
	AdminExecuteModel adminExecuteM;

	AdminExecuteModelRequest adminExecuteMR;

	@PostMapping("/community")
	public Map<String, Object> searchCommunityBoard(@RequestBody Map<String, String> requestData,Model model) {
	    System.out.println("searchCommunityBoard");
	    String searchKeyword = requestData.get("searchKeyword");
	    String searchFilterType = requestData.get("searchFilterType");
	    String searchCategory = requestData.get("searchCategory");
	    String searchStartDate = requestData.get("searchStartDate");
	    String searchEndDate = requestData.get("searchEndDate");     
	    
	    
	    // 날짜 형식 변환 (문자열 -> LocalDate)
	    LocalDate startDate = null;
	    LocalDate endDate = null;
	        
	    // 시작 날짜가 있을 경우 LocalDate로 변환
	    if (searchStartDate != null && !searchStartDate.isEmpty()) {
	        startDate = LocalDate.parse(searchStartDate, DateTimeFormatter.ISO_LOCAL_DATE);
	    }

	    // 종료 날짜가 있을 경우 LocalDate로 변환
	    if (searchEndDate != null && !searchEndDate.isEmpty()) {
	        endDate = LocalDate.parse(searchEndDate, DateTimeFormatter.ISO_LOCAL_DATE);
	    }   
	   
	    // 해당 페이지에 맞는 게시물 리스트 조회
	    List<ACommunityDto> communityList = communtiyDao.communityList(
	        searchKeyword, searchFilterType, searchCategory, searchStartDate, searchEndDate );
	 
	    int totalItems = communtiyDao.totalItems(
	            searchKeyword, searchFilterType, searchCategory, searchStartDate, searchEndDate);
	    
	    System.out.println("조회된 게시물 수: " + communityList.size());  // 게시물 수 확인
	    Map<String, Object> response = new HashMap<>();
	    response.put("communityList", communityList);
	    response.put("totalItems", totalItems);
	        
	    return response;
	}

	@PostMapping("/report")
	public Map<String, Object> searchReport(@RequestBody Map<String, String> requestData, Model model) {
	    System.out.println("searchReport");
	    String reportSearchKeyword = requestData.get("searchKeyword");
	    String reportSearchFilterType = requestData.get("searchFilterType");
	    String reportCategory = requestData.get("searchCategory");
	    String reportStartDate = requestData.get("searchStartDate");
	    String reportEndDate = requestData.get("searchEndDate");
	    System.out.println("reportSearchKeyword: "+reportSearchKeyword);
	    System.out.println("reportSearchFilterType: "+reportSearchFilterType);
	    System.out.println("reportCategory: "+reportCategory);
	    System.out.println("reportStartDate: "+reportStartDate);
	    System.out.println("reportEndDate: "+reportEndDate);
	    
	    
	    // 날짜 형식 변환 (문자열 -> LocalDate)
	    LocalDate startDate = null;
	    LocalDate endDate = null;

	    // 시작 날짜가 있을 경우 LocalDate로 변환
	    if (reportStartDate != null && !reportStartDate.isEmpty()) {
	        startDate = LocalDate.parse(reportStartDate, DateTimeFormatter.ISO_LOCAL_DATE);
	    }

	    // 종료 날짜가 있을 경우 LocalDate로 변환
	    if (reportEndDate != null && !reportEndDate.isEmpty()) {
	        endDate = LocalDate.parse(reportEndDate, DateTimeFormatter.ISO_LOCAL_DATE);
	    }

	    // 신고 리스트 조회
	    List<ACommunityDto> reportList = communtiyDao.reportList(
	        reportSearchKeyword, reportSearchFilterType, reportCategory, reportStartDate, reportEndDate);

	    int totalReportItems = communtiyDao.reportTotalItems(
	        reportSearchKeyword, reportSearchFilterType, reportCategory, reportStartDate, reportEndDate);

	    System.out.println("조회된 신고 내역 수: " + reportList.size()); // 신고 내역 수 확인
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("reportList", reportList);
	    response.put("totalItems", totalReportItems);

	    return response;
	}
	
	  @PostMapping("/updateReportStatus")
	public void updateReportStatus(@RequestBody ArrayList<Map<String, Object>> selectedReport, Model model)  {
		  model.addAttribute("selectedReport", selectedReport);
		  
		  adminExecuteM = new AdminCommunityReportService(communtiyDao);
		  adminExecuteM.execute(model);
		  
		  System.out.println("selectedReport:" + selectedReport);
		  System.out.println("model:" + model);
		  
		 
		  
	  }
}
