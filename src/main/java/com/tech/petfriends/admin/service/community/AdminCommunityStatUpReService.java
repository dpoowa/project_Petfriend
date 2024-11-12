package com.tech.petfriends.admin.service.community;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.petfriends.admin.mapper.AdminCommunityDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;

@Service
public class AdminCommunityStatUpReService implements AdminExecuteModel {

	private AdminCommunityDao admincommunityDao;
	
	private ArrayList<Map<String, Object>> selectedReport;
	
	public AdminCommunityStatUpReService(AdminCommunityDao admincommunityDao, ArrayList<Map<String, Object>> selectedReport) {
		this.admincommunityDao = admincommunityDao;
		this.selectedReport = selectedReport;
	}

	 @Override
	 public void execute(Model model) {		  
		  
		  Map<String, Object> reportNoMap;
		  
		  for(int i = 0; i < selectedReport.size(); i++) {
			  
			  reportNoMap = selectedReport.get(i);
			  
			  int reportid = Integer.parseInt((String) reportNoMap.get("reportNo"));
			  
			  admincommunityDao.reportStatusUpdate(reportid);
			  
		  }
		  
	 }
}