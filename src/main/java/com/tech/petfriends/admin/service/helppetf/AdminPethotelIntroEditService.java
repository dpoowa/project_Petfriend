package com.tech.petfriends.admin.service.helppetf;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.petfriends.admin.mapper.AdminPageDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.helppetf.dto.PethotelIntroDto;

@Service
public class AdminPethotelIntroEditService implements AdminExecuteModel {
	
	private AdminPageDao adminDao;
	
	private PethotelIntroDto introDto;
	
	public AdminPethotelIntroEditService(AdminPageDao adminDao, PethotelIntroDto introDto) {
		this.adminDao = adminDao;
		this.introDto = introDto;
	}
	
	@Override
	public void execute(Model model) {

		String intro_line1 = introDto.getIntro_line1();
		String intro_line2 = introDto.getIntro_line2();
		String intro_line3 = introDto.getIntro_line3();
		String intro_line4 = introDto.getIntro_line4();
		String intro_line5 = introDto.getIntro_line5();
		String intro_line6 = introDto.getIntro_line6();
		String intro_line7 = introDto.getIntro_line7();
		String intro_line8 = introDto.getIntro_line8();
		String intro_line9 = introDto.getIntro_line9();
		
		// 파라미터에 첨부하여 DB요청
		adminDao.adminPethotelIntroEdit(intro_line1, intro_line2, intro_line3, intro_line4, intro_line5,
				intro_line6, intro_line7, intro_line8, intro_line9);
	}

}
