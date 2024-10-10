package com.tech.petfriends.helppetf.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.petfriends.helppetf.mapper.HelpPetfDao;

@Service
public class FindFacilitiesService implements HelppetfServiceInter {
		
	private HelpPetfDao helpDao;
//	@@ TODO :: DB 나오면 Mapper(HelpPetfDao) 연동해서
//	유저 주소정보 불러와서 model -> view에서 주소란에 넣기
	public FindFacilitiesService(HelpPetfDao helpDao) {
		this.helpDao = helpDao;
	}

	@Override
	public void execute(Model model) {
		helpDao.blankMethod();
		String apiKey = (String) model.getAttribute("apiKey");
		model.addAttribute("apiKey", apiKey);
 
	}
	
}
