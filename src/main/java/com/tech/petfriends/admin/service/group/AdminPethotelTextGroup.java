package com.tech.petfriends.admin.service.group;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.petfriends.admin.service.helppetf.AdminPethotelInfoData;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelInfoEditService;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelIntroData;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelIntroEditService;
import com.tech.petfriends.helppetf.dto.PethotelInfoDto;
import com.tech.petfriends.helppetf.dto.PethotelIntroDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminPethotelTextGroup {
	private final AdminPethotelIntroData adminPethotelIntroData;
	private final AdminPethotelInfoData adminPethotelInfoData;
	private final AdminPethotelIntroEditService adminPethotelIntroEditService;
	private final AdminPethotelInfoEditService adminPethotelInfoEditService;

	public ResponseEntity<PethotelIntroDto> executeAdminPethotelIntroData() {
		return adminPethotelIntroData.execute();
	}

	public ResponseEntity<PethotelInfoDto> executeAdminPethotelInfoData() {
		return adminPethotelInfoData.execute();
	}

	public ResponseEntity<String> executeAdminPethotelIntroEditService(PethotelIntroDto dto) {
		adminPethotelIntroEditService.setIntroDto(dto);
		return adminPethotelIntroEditService.execute();
	}

	public ResponseEntity<String> executeAdminPethotelInfoEditService(PethotelInfoDto dto) {
		adminPethotelInfoEditService.setInfoDto(dto);
		return adminPethotelInfoEditService.execute();
	}

}
