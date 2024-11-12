package com.tech.petfriends.admin.service.helppetf;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.petfriends.admin.mapper.AdminPageDao;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;

@Service
public class AdminPetteacherDeleteService implements AdminExecuteModelRequest {

	private AdminPageDao adminDao;
	
	public AdminPetteacherDeleteService(AdminPageDao adminDao) {
		this.adminDao = adminDao; 
	}

	@Override
	public void execute(Model model, HttpServletRequest request) {
		String hpt_seq = request.getParameter("hpt_seq");
		
		// 파라미터 첨부하여 DB 호출, 데이터 삭제
		adminDao.adminPetteacherDelete(hpt_seq);
	}

}
