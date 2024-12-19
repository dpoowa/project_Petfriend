package com.tech.petfriends.helppetf.service.group;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.petfriends.helppetf.dto.PethotelFormDataDto;
import com.tech.petfriends.helppetf.dto.PetteacherDto;
import com.tech.petfriends.helppetf.vo.HelpPetfAdoptionItemsVo;
import com.tech.petfriends.mypage.dto.MyPetDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class HelppetfServiceGroupMain {
	private final HelppetfPethotelGroup pethotelGroup;
	private final HelppetfAdoptionGroup adoptionGroup;
	private final HelppetfPetteacherGroup petteacherGroup;
	private final HelppetfFindGroup findGroup;
		
	public ResponseEntity<ArrayList<MyPetDto>> executePethotelSelectPetService(HttpSession session) {
		return pethotelGroup.executePethotelSelectPetService(session);
	}
	
	public ResponseEntity<String> executePethotelReserveDataService(HttpServletRequest request, 
			HttpSession session, ArrayList<PethotelFormDataDto> formList) {
		return pethotelGroup.executePethotelReserveDataService(request, session, formList);
	}
	
	public ResponseEntity<String> executePethotelMainService() {
		return pethotelGroup.executePethotelMainService();
	}

	public Mono<ResponseEntity<HelpPetfAdoptionItemsVo>> executeAdoptionGetJson(HttpServletRequest request) {
		return adoptionGroup.executeAdoptionGetJson(request);
	}

	public ResponseEntity<ArrayList<PetteacherDto>> executePetteacherMainService(HttpServletRequest request) {
		return petteacherGroup.executePetteacherMainService(request);
	}
	
	public ResponseEntity<PetteacherDto> executePetteacherDetailService(HttpServletRequest request) {
		return petteacherGroup.executePetteacherDetailService(request);
	}
	
	public ResponseEntity<String> executeFindAddrMapService(HttpSession session) {
		return findGroup.executeFindAddrMapService(session);
	}
	
}
