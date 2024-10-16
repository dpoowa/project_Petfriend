package com.tech.petfriends.mypage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.petfriends.mypage.dao.MypageDao;
import com.tech.petfriends.mypage.dto.MyPetDto;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@Autowired
	private MypageDao mypageDao;
	
	@GetMapping("/mypet")
	public String mypet(Model model, HttpSession session) {
		String memCode = (String) session.getAttribute("mem_code");
		
        ArrayList<MyPetDto> pets = mypageDao.getPetsByMemberCode(memCode);
		model.addAttribute("pets",pets);
		
		return "mypage/mypet";
	}
	
	@GetMapping("/grade")
	public String grade() {
		return "mypage/grade";
	}
	
	@GetMapping("/point")
	public String point() {
		return "mypage/point";
	}
	
	@GetMapping("/coupon")
	public String coupon() {
		return "mypage/coupon";
	}
	
	@GetMapping("/setting")
	public String setting() {
		return "mypage/setting";
	}
	
	@GetMapping("/cart")
	public String cart() {
		return "mypage/cart";
	}
	
	@GetMapping("/order")
	public String order() {
		return "mypage/order";
	}
	
	@GetMapping("/review")
	public String review() {
		return "mypage/review";
	}
	
	@GetMapping("/wish")
	public String wish() {
		return "mypage/wish";
	}
	
	@GetMapping("/mypet/register")
	public String mypetRegister() {
		return "mypage/mypet/register";
	}
	
}
