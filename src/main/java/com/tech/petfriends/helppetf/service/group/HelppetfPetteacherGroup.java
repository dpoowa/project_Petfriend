package com.tech.petfriends.helppetf.service.group;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.petfriends.helppetf.dto.PetteacherDto;
import com.tech.petfriends.helppetf.service.PetteacherDetailService;
import com.tech.petfriends.helppetf.service.PetteacherMainService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HelppetfPetteacherGroup {
	
	private final PetteacherMainService petteacherMainService;
	private final PetteacherDetailService petteacherDetailService;
	
	public ResponseEntity<ArrayList<PetteacherDto>> executePetteacherMainService(HttpServletRequest request) {
		return petteacherMainService.execute(request);
	}
	
	public ResponseEntity<PetteacherDto> executePetteacherDetailService(HttpServletRequest request) {
		return petteacherDetailService.execute(request);
	}
	
	
}
