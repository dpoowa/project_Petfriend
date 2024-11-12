package com.tech.petfriends.helppetf.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tech.petfriends.configuration.ApikeyConfig;
import com.tech.petfriends.helppetf.dto.PethotelFormDataDto;
import com.tech.petfriends.helppetf.dto.PetteacherDto;
import com.tech.petfriends.helppetf.mapper.HelpPetfDao;
import com.tech.petfriends.helppetf.service.AdoptionGetJson;
import com.tech.petfriends.helppetf.service.FindAddrMapService;
import com.tech.petfriends.helppetf.service.HelppetfExecuteModel;
import com.tech.petfriends.helppetf.service.HelppetfExecuteModelRequest;
import com.tech.petfriends.helppetf.service.HelppetfExecuteModelRequestSession;
import com.tech.petfriends.helppetf.service.HelppetfExecuteModelSession;
import com.tech.petfriends.helppetf.service.PethotelMainService;
import com.tech.petfriends.helppetf.service.PethotelReserveService;
import com.tech.petfriends.helppetf.service.PethotelSelectPetService;
import com.tech.petfriends.helppetf.service.PetteacherDetailService;
import com.tech.petfriends.helppetf.service.PetteacherService;
import com.tech.petfriends.helppetf.vo.HelpPetfAdoptionItemsVo;
import com.tech.petfriends.mypage.dto.MyPetDto;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/helppetf")
public class HelpPetfRestController {
	
	@Autowired
	ApikeyConfig apikeyConfig;
	
	@Autowired
	WebClient webClient;
	
	@Autowired
	HelpPetfDao helpDao;

	HelppetfExecuteModel helppetfM;
	
	HelppetfExecuteModelRequest helppetfMR;

	HelppetfExecuteModelSession helppetfMS;

	HelppetfExecuteModelRequestSession helppetfMRS;

	@SuppressWarnings("unchecked")
	@GetMapping("/pethotel/pethotel_select_pet")
	public ArrayList<MyPetDto> pethotelSelectPet(Model model, HttpSession session) {
		helppetfMS = new PethotelSelectPetService(helpDao);
		helppetfMS.execute(model, session);
		return (ArrayList<MyPetDto>) model.getAttribute("petDto");
	}

	@PostMapping("/pethotel/pethotel_reserve_data")
	public String pethotelReserveData(@RequestBody ArrayList<PethotelFormDataDto> formList, HttpServletRequest request,
			Model model, HttpSession session) {
		
		helppetfMRS = new PethotelReserveService(helpDao, formList);
		helppetfMRS.execute(model, request, session); 
		
		return (String) model.getAttribute("jsonData"); // execute 메서드를 실행하여 모델에 저장한 json 형식의 데이터 반환
	}
	
	@GetMapping("/pethotel/pethotel_post_data")
	public String pethotelPostData(Model model) {
		helppetfM = new PethotelMainService(helpDao);
		helppetfM.execute(model);
				
		return (String) model.getAttribute("json");
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/adoption/getJson")
	public Mono<ResponseEntity<HelpPetfAdoptionItemsVo>> adoptionGetJson(HttpServletRequest request, Model model)
			throws Exception {
		helppetfMR = new AdoptionGetJson(apikeyConfig, webClient);
		helppetfMR.execute(model, request);
		return (Mono<ResponseEntity<HelpPetfAdoptionItemsVo>>) model.getAttribute("jsonData");
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/petteacher/petteacher_data")
	public ArrayList<PetteacherDto> petteacherData(HttpServletRequest request, Model model) {
		helppetfMR = new PetteacherService(helpDao);
		helppetfMR.execute(model, request);
		return (ArrayList<PetteacherDto>) model.getAttribute("ylist");
	}
	
	@GetMapping("/petteacher/petteacher_detail_data")
	public PetteacherDto petteacherDetailData(HttpServletRequest request, Model model) {
		helppetfMR = new PetteacherDetailService(helpDao);
		helppetfMR.execute(model, request);
		return (PetteacherDto) model.getAttribute("petteacherDetailDto");
	}
	
	@GetMapping("/find/adress_data") // 주변 반려동물 시설 찾기 페이지
	public String pet_facilities(Model model, HttpSession session) throws JsonProcessingException {
		helppetfMS = new FindAddrMapService(helpDao);
		helppetfMS.execute(model, session);
		return (String) model.getAttribute("jsonData");
	}
}
