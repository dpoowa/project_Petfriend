package com.tech.petfriends.helppetf.service.group;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.petfriends.helppetf.service.FindAddrMapService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HelppetfFindGroup {	
	
	private final FindAddrMapService findAddrMapService;
		
	public ResponseEntity<String> executeFindAddrMapService(HttpSession session) {
		return findAddrMapService.execute(session);
	}
	
}
