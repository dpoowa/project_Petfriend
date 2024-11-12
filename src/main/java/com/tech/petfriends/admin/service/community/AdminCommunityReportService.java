package com.tech.petfriends.admin.service.community;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.tech.petfriends.admin.dto.ACommunityDto;
import com.tech.petfriends.admin.mapper.AdminCommunityDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;

public class AdminCommunityReportService implements AdminExecuteModel {

	private AdminCommunityDao communityDao;
	private Map<String, String> requestData;
	
	public AdminCommunityReportService(AdminCommunityDao communityDao, Map<String, String> requestData) {
		this.communityDao = communityDao;
		this.requestData = requestData;
	}
	
	
	@Override
	public void execute(Model model) {


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
	    List<ACommunityDto> reportList = communityDao.reportList(
	        reportSearchKeyword, reportSearchFilterType, reportCategory, reportStartDate, reportEndDate);

	    int totalReportItems = communityDao.reportTotalItems(
	        reportSearchKeyword, reportSearchFilterType, reportCategory, reportStartDate, reportEndDate);

	    System.out.println("조회된 신고 내역 수: " + reportList.size()); // 신고 내역 수 확인
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("reportList", reportList);
	    response.put("totalItems", totalReportItems);
	    
	    
	    model.addAttribute("response", response);
	}

}
