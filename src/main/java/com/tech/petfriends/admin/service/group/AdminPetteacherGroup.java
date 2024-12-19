package com.tech.petfriends.admin.service.group;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.petfriends.admin.service.helppetf.AdminPetteacherDataService;
import com.tech.petfriends.admin.service.helppetf.AdminPetteacherDeleteService;
import com.tech.petfriends.admin.service.helppetf.AdminPetteacherDetailService;
import com.tech.petfriends.admin.service.helppetf.AdminPetteacherEditService;
import com.tech.petfriends.admin.service.helppetf.AdminPetteacherWriteService;
import com.tech.petfriends.helppetf.dto.PetteacherDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminPetteacherGroup {	
	private final AdminPetteacherDataService adminPetteacherDataService;
	private final AdminPetteacherDetailService adminPetteacherDetailService;
	private final AdminPetteacherDeleteService adminPetteacherDeleteService;
	private final AdminPetteacherWriteService adminPetteacherWriteService;
	private final AdminPetteacherEditService adminPetteacherEditService;
	
	public ResponseEntity<ArrayList<PetteacherDto>> executeAdminPetteacherDataService(HttpServletRequest request) {
		return adminPetteacherDataService.execute(request);
	}

	public ResponseEntity<PetteacherDto> executeAdminPetteacherDetailService(HttpServletRequest request) {
		return adminPetteacherDetailService.execute(request);
	}

	public ResponseEntity<String> executeAdminPetteacherDeleteService(HttpServletRequest request) {
		return adminPetteacherDeleteService.execute(request);
	}

	public ResponseEntity<String> executeAdminPetteacherWriteService(PetteacherDto dto) {
		adminPetteacherWriteService.setDto(dto);
		return adminPetteacherWriteService.execute();
	}

	public ResponseEntity<String> executeAdminPetteacherEditService(HttpServletRequest request, PetteacherDto dto) {
		adminPetteacherEditService.setDto(dto);
		return adminPetteacherEditService.execute(request);
	}

}
