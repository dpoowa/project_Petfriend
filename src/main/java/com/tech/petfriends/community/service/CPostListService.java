package com.tech.petfriends.community.service;

import java.util.ArrayList;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.petfriends.community.dto.CDto;
import com.tech.petfriends.community.mapper.IDao;

@Service
public class CPostListService implements CServiceInterface{
	
	private IDao iDao;
	
	@Autowired
	public CPostListService(IDao iDao) {
	this.iDao = iDao;
}

	@Override
	public void execute(Model model) {
		Map<String, Object> m = model.asMap();
		HttpServletRequest request = (HttpServletRequest) m.get("request");
		
		
		ArrayList<CDto> postList = iDao.getPostList(); // DAO 호출
        model.addAttribute("postList", postList); // 모델에 게시글 리스트 추가
	
        
//        ArrayList<CDto> getpetimg = iDao.getPetIMG(mem_code);
//        model.addAttribute("getpetimg", getpetimg);
	
	
	} 
	
}
	

