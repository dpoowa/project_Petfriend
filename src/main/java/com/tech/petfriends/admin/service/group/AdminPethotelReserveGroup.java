package com.tech.petfriends.admin.service.group;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.petfriends.admin.service.helppetf.AdminPethotelDataService;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelReserveDetailService;
import com.tech.petfriends.admin.service.helppetf.AdminPethotelReserveUpdateService;
import com.tech.petfriends.helppetf.dto.PethotelMemDataDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminPethotelReserveGroup {
	private final AdminPethotelReserveUpdateService adminPethotelReserveUpdateService;
	private final AdminPethotelReserveDetailService adminPethotelReserveDetailService;
	private final AdminPethotelDataService adminPethotelDataService;


	public ResponseEntity<String> executeAdminPethotelReserveUpdate(Map<String, String> statusMap) {
		adminPethotelReserveUpdateService.setStatusMap(statusMap);
		return adminPethotelReserveUpdateService.execute();
	}

	public ResponseEntity<String> executeAdminPethotelReserveDetailService(HttpServletRequest request) {
		return adminPethotelReserveDetailService.execute(request);
	}

	public ResponseEntity<ArrayList<PethotelMemDataDto>> executeAdminPethotelDataService(HttpServletRequest request) {
		return adminPethotelDataService.execute(request);
	}
}
