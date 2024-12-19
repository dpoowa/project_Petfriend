package com.tech.petfriends.helppetf.service.group;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.petfriends.helppetf.service.AdoptionGetJson;
import com.tech.petfriends.helppetf.vo.HelpPetfAdoptionItemsVo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class HelppetfAdoptionGroup {
	private final AdoptionGetJson adoptionGetJson;
	

	public Mono<ResponseEntity<HelpPetfAdoptionItemsVo>> executeAdoptionGetJson(HttpServletRequest request) {
		return adoptionGetJson.execute(request);
	}

}
