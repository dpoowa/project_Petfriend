package com.tech.petfriends.helppetf.service.group;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.petfriends.helppetf.dto.PethotelFormDataDto;
import com.tech.petfriends.helppetf.service.PethotelMainService;
import com.tech.petfriends.helppetf.service.PethotelReserveService;
import com.tech.petfriends.helppetf.service.PethotelSelectPetService;
import com.tech.petfriends.mypage.dto.MyPetDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HelppetfPethotelGroup {
	private final PethotelSelectPetService pethotelSelectPetService;
	private final PethotelReserveService pethotelReserveDataService;
	private final PethotelMainService pethotelMainService;
	
	public ResponseEntity<ArrayList<MyPetDto>> executePethotelSelectPetService(HttpSession session) {
		return pethotelSelectPetService.execute(session);
	}
	
	public ResponseEntity<String> executePethotelReserveDataService(HttpServletRequest request, 
			HttpSession session, ArrayList<PethotelFormDataDto> formList) {
		pethotelReserveDataService.setFormList(formList);
		return pethotelReserveDataService.execute(request, session);
	}
	
	public ResponseEntity<String> executePethotelMainService() {
		return pethotelMainService.execute();
	}	
}
