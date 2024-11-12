package com.tech.petfriends.admin.service.community;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.petfriends.admin.dto.ACommunityDto;
import com.tech.petfriends.admin.mapper.AdminCommunityDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;

@Service
public class AdminCommunitySearchService implements AdminExecuteModel {

	private Map<String, String> requestData;
	
	private AdminCommunityDao communtiyDao;
	
	public AdminCommunitySearchService(AdminCommunityDao communtiyDao, Map<String, String> requestData) {
		this.requestData = requestData;
		this.communtiyDao = communtiyDao;
	}
	
	@Override
	public void execute(Model model) {

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
	    
	    model.addAttribute("response", response);

	}

}
