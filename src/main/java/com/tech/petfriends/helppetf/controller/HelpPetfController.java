package com.tech.petfriends.helppetf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.petfriends.configuration.ApikeyConfig;
import com.tech.petfriends.helppetf.mapper.HelpPetfDao;
import com.tech.petfriends.helppetf.service.AdoptionViewService;
import com.tech.petfriends.helppetf.service.FindFacilitiesService;
import com.tech.petfriends.helppetf.service.FindHospitalService;
import com.tech.petfriends.helppetf.service.HelppetfServiceInter;
import com.tech.petfriends.helppetf.service.PetteacherDetailService;


@Controller
@RequestMapping("/helppetf")
public class HelpPetfController {
	
	@Autowired
	ApikeyConfig apikeyConfig;
	
	@Autowired
	HelpPetfDao helpDao;
	
	HelppetfServiceInter helpServiceInterface;
	
	@GetMapping("/pethotel/pethotel_main") // 펫호텔 메인
	public String pethotelMain(Model model) {
		model.addAttribute("main_navbar_id", "helppetf");
		model.addAttribute("sub_navbar_id", "pethotel");
		return "/helppetf/pethotel/pethotel_main";
	}

	@GetMapping("/pethotel/pethotel_reserve") // 펫호텔 예약화면
	public String pethotelReserve(Model model) {
		model.addAttribute("main_navbar_id", "helppetf");
		model.addAttribute("sub_navbar_id", "pethotel");
		return "/helppetf/pethotel/pethotel_reserve";
	}

	@GetMapping("/adoption/adoption_main") // 입양 센터 메인
	public String adoptionMain(Model model) {
		model.addAttribute("main_navbar_id", "helppetf");
		model.addAttribute("sub_navbar_id", "adoption");
		return "/helppetf/adoption/adoption_main";
	}
	
	@GetMapping("/adoption/adoption_detail") // 입양 센터 상세페이지
	public String adoptionContentSend(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("main_navbar_id", "helppetf");
		model.addAttribute("sub_navbar_id", "adoption");
		helpServiceInterface = new AdoptionViewService();
		helpServiceInterface.execute(model); // <- 리다이렉트 이후 데이터 처리
		return "/helppetf/adoption/adoption_detail"; // <- view단 jsp 매핑 호출
	}
		
	@GetMapping("/petteacher/petteacher_main") // 펫티쳐 메인
	public String petteacherList(Model model) {
		model.addAttribute("main_navbar_id", "helppetf");
		model.addAttribute("sub_navbar_id", "petteacher");
		return "/helppetf/petteacher/petteacher_main";
	}

	@GetMapping("/petteacher/petteacher_detail") // 펫티쳐 상세 페이지
	public String petteacherDetails(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("main_navbar_id", "helppetf");		
		model.addAttribute("sub_navbar_id", "petteacher");
		helpServiceInterface = new PetteacherDetailService(helpDao);
		helpServiceInterface.execute(model);
		return "/helppetf/petteacher/petteacher_detail";
	}

	@GetMapping("/find/pet_hospital") // 주변 동물병원 찾기 페이지
	public String find_hospital(Model model) {
		model.addAttribute("apiKey", apikeyConfig.getKakaoApikey());
		model.addAttribute("main_navbar_id", "helppetf");
		model.addAttribute("sub_navbar_id", "pet_hospital");
		helpServiceInterface = new FindHospitalService(helpDao);
		helpServiceInterface.execute(model);
		
		return "/helppetf/find/pet_hospital";
	}

	@GetMapping("/find/pet_facilities") // 주변 반려동물 시설 찾기 페이지
	public String pet_facilities(Model model) {
		model.addAttribute("apiKey", apikeyConfig.getKakaoApikey());
		model.addAttribute("main_navbar_id", "helppetf");
		model.addAttribute("sub_navbar_id", "pet_facilities");
		helpServiceInterface = new FindFacilitiesService(helpDao);
		helpServiceInterface.execute(model);
		
		return "/helppetf/find/pet_facilities";
	}
}
