package com.tech.petfriends.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tech.petfriends.admin.mapper.AdminPageDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelDataService;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelInfoData;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelInfoEditService;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelIntroData;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelIntroEditService;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelReserveDetailService;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelReserveUpdateService;
import com.tech.petfriends.admin.service.helppetf.AdminPetteacherDataService;
import com.tech.petfriends.admin.service.helppetf.AdminPetteacherDeleteService;
import com.tech.petfriends.admin.service.helppetf.AdminPetteacherDetailService;
import com.tech.petfriends.admin.service.helppetf.AdminPetteacherEditService;
import com.tech.petfriends.admin.service.helppetf.AdminPetteacherWriteService;
import com.tech.petfriends.helppetf.dto.PethotelInfoDto;
import com.tech.petfriends.helppetf.dto.PethotelIntroDto;
import com.tech.petfriends.helppetf.dto.PethotelMemDataDto;
import com.tech.petfriends.helppetf.dto.PetteacherDto;

@RestController
@RequestMapping("/admin")
public class AdminHelppetfRestController {

	@Autowired
	AdminPageDao adminDao;

	AdminExecuteModel adminExecuteM;

	AdminExecuteModelRequest adminExecuteMR;

	@PutMapping("/pethotel_reserve_update")
	public String pethotelReserveUpdate(@RequestBody Map<String, String> statusMap, HttpServletRequest request,
			Model model) {
		adminExecuteM = new AdminPethotelReserveUpdateService(adminDao, statusMap);
		adminExecuteM.execute(model);
		
		return "{\"status\": \"success\"}";
	}

	@GetMapping("/pethotel_admin_reserve_detail")
	public String pethotelReserveDetail(HttpServletRequest request, Model model) throws JsonProcessingException {
		adminExecuteMR = new AdminPethotelReserveDetailService(adminDao);
		adminExecuteMR.execute(model, request);
		
		return (String) model.getAttribute("jsonData");
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/pethotel_admin_reserve")
	public ArrayList<PethotelMemDataDto> pethotelReserveData(HttpServletRequest request, Model model) {
		adminExecuteMR = new AdminPethotelDataService(adminDao);
		adminExecuteMR.execute(model, request);
		
		return (ArrayList<PethotelMemDataDto>) model.getAttribute("memSelectDto");
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/petteacher_admin_data")
	public List<PetteacherDto> getPetteacherData(HttpServletRequest request, Model model) {
		adminExecuteMR = new AdminPetteacherDataService(adminDao);
		adminExecuteMR.execute(model, request);
		
		return (List<PetteacherDto>) model.getAttribute("petteacherList");
	}

	@GetMapping("/petteacher_admin_data_forEdit")
	public PetteacherDto petteacherDataForEdit(HttpServletRequest request, Model model) {
		adminExecuteMR = new AdminPetteacherDetailService(adminDao);
		adminExecuteMR.execute(model, request);

		return (PetteacherDto) model.getAttribute("dto");
	}

	@DeleteMapping("/petteacher_admin_data_forDelete")
	public String petteacherDataForDelete(HttpServletRequest request, Model model) {
		adminExecuteMR = new AdminPetteacherDeleteService(adminDao);
		adminExecuteMR.execute(model, request);

		return "{\"status\": \"success\"}";
	}

	@PostMapping("/petteacher_admin_data_forWrite")
	public String petteacherDataForWrite(@RequestBody PetteacherDto dto, Model model) {
		adminExecuteM = new AdminPetteacherWriteService(adminDao, dto);
		adminExecuteM.execute(model);
		
		return "{\"status\": \"success\"}";
	}

	@PutMapping("/petteacher_admin_data_forEdit")
	public String petteacherDataForEdit(@RequestBody PetteacherDto dto, HttpServletRequest request, Model model) {
		adminExecuteMR = new AdminPetteacherEditService(adminDao, dto);
		adminExecuteMR.execute(model, request);

		return "{\"status\": \"success\"}";
	}

	@GetMapping("/pethotel_intro_data")
	public PethotelIntroDto pethotelIntroData(Model model) {
		adminExecuteM = new AdminPethotelIntroData(adminDao);
		adminExecuteM.execute(model);
		return (PethotelIntroDto) model.getAttribute("dto");
	}

	@GetMapping("/pethotel_info_data")
	public PethotelInfoDto pethotelInfoData(Model model) {
		adminExecuteM = new AdminPethotelInfoData(adminDao);
		adminExecuteM.execute(model);
		return (PethotelInfoDto) model.getAttribute("dto");
	}

	@PutMapping("/pethotel_admin_intro_dataForEdit")
	public String pethotelIntroForEdit(@RequestBody PethotelIntroDto dto, HttpServletRequest request, Model model) {
		adminExecuteM = new AdminPethotelIntroEditService(adminDao, dto);
		adminExecuteM.execute(model);

		return "{\"status\": \"success\"}";
	}

	@PutMapping("/pethotel_admin_info_dataForEdit")
	public String pethotelInfoForEdit(@RequestBody PethotelInfoDto dto, HttpServletRequest request, Model model) {
		adminExecuteM = new AdminPethotelInfoEditService(adminDao, dto);
		adminExecuteM.execute(model);

		return "{\"status\": \"success\"}";
	}
}
