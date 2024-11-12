package com.tech.petfriends.admin.service.helppetf;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.petfriends.admin.mapper.AdminPageDao;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;
import com.tech.petfriends.helppetf.dto.PetteacherDto;

@Service
public class AdminPetteacherDetailService implements AdminExecuteModelRequest {
	
	private AdminPageDao adminDao;

	public AdminPetteacherDetailService(AdminPageDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public void execute(Model model, HttpServletRequest request) {

		String hpt_seq = request.getParameter("hpt_seq");
		
		// 파라미터 데이터 첨부해서 DB 호출, 데이터 불러옴
		PetteacherDto dto = adminDao.adminPetteacherDetail(hpt_seq);
		
		// model에 전달
		model.addAttribute("dto", dto);
	}
}
