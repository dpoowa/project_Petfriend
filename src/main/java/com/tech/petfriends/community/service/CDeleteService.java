package com.tech.petfriends.community.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.petfriends.community.mapper.IDao;

public class CDeleteService implements CServiceInterface {
	
	private IDao iDao;
	
	public CDeleteService(IDao iDao) {
		this.iDao = iDao;
	}
	
	@Override
	public void execute(Model model) {
		Map<String, Object> m = model.asMap();
		HttpServletRequest request = (HttpServletRequest) m.get("request");
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		iDao.delete(board_no);
		
	}

	
	
	
}
