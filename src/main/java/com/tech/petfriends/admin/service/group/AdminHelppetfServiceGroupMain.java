package com.tech.petfriends.admin.service.group;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.petfriends.helppetf.dto.PethotelInfoDto;
import com.tech.petfriends.helppetf.dto.PethotelIntroDto;
import com.tech.petfriends.helppetf.dto.PethotelMemDataDto;
import com.tech.petfriends.helppetf.dto.PetteacherDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminHelppetfServiceGroupMain {
	private final AdminPethotelReserveGroup adminPethotelReserveGroup;
	private final AdminPetteacherGroup adminPetteacherGroup;
	private final AdminPethotelTextGroup adminPethotelTextGroup;
	
	public ResponseEntity<String> executeAdminPethotelReserveUpdate(Map<String, String> statusMap) {
		return adminPethotelReserveGroup.executeAdminPethotelReserveUpdate(statusMap);
	}

	public ResponseEntity<String> executeAdminPethotelReserveDetailService(HttpServletRequest request) {
		return adminPethotelReserveGroup.executeAdminPethotelReserveDetailService(request);
	}

	public ResponseEntity<ArrayList<PethotelMemDataDto>> executeAdminPethotelDataService(HttpServletRequest request) {
		return adminPethotelReserveGroup.executeAdminPethotelDataService(request);
	}

	public ResponseEntity<ArrayList<PetteacherDto>> executeAdminPetteacherDataService(HttpServletRequest request) {
		return adminPetteacherGroup.executeAdminPetteacherDataService(request);
	}

	public ResponseEntity<PetteacherDto> executeAdminPetteacherDetailService(HttpServletRequest request) {
		return adminPetteacherGroup.executeAdminPetteacherDetailService(request);
	}

	public ResponseEntity<String> executeAdminPetteacherDeleteService(HttpServletRequest request) {
		return adminPetteacherGroup.executeAdminPetteacherDeleteService(request);
	}

	public ResponseEntity<String> executeAdminPetteacherWriteService(PetteacherDto dto) {
		return adminPetteacherGroup.executeAdminPetteacherWriteService(dto);
	}

	public ResponseEntity<String> executeAdminPetteacherEditService(HttpServletRequest request, PetteacherDto dto) {
		return adminPetteacherGroup.executeAdminPetteacherEditService(request, dto);
	}

	public ResponseEntity<PethotelIntroDto> executeAdminPethotelIntroData() {
		return adminPethotelTextGroup.executeAdminPethotelIntroData();
	}

	public ResponseEntity<PethotelInfoDto> executeAdminPethotelInfoData() {
		return adminPethotelTextGroup.executeAdminPethotelInfoData();
	}

	public ResponseEntity<String> executeAdminPethotelIntroEditService(PethotelIntroDto dto) {
		return adminPethotelTextGroup.executeAdminPethotelIntroEditService(dto);
	}

	public ResponseEntity<String> executeAdminPethotelInfoEditService(PethotelInfoDto dto) {
		return adminPethotelTextGroup.executeAdminPethotelInfoEditService(dto);
	}

}
