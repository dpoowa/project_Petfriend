package com.tech.petfriends.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.petfriends.admin.dto.CouponDto;
import com.tech.petfriends.admin.dto.MemberCouponDto;
import com.tech.petfriends.admin.mapper.CouponDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;
import com.tech.petfriends.admin.service.coupon.AdminCouponDataService;
import com.tech.petfriends.admin.service.coupon.AdminCouponMordifyService;
import com.tech.petfriends.admin.service.coupon.AdminMemberCouponDataService;

@RestController
@RequestMapping("/admin")
public class AdminCouponRestController {
	
	@Autowired
	CouponDao couponDao;
	
	AdminExecuteModel adminExecuteM;

	AdminExecuteModelRequest adminExecuteMR;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/coupon/data")
	public List<CouponDto> getCouponData(HttpServletRequest request, Model model) {
		adminExecuteMR = new AdminCouponDataService(couponDao);
		adminExecuteMR.execute(model, request);
		return (List<CouponDto>) model.getAttribute("coupons");
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/memberCoupon/data")
	public List<MemberCouponDto> getMemberCouponData(HttpServletRequest request, Model model) {
		adminExecuteMR = new AdminMemberCouponDataService(couponDao);
		adminExecuteMR.execute(model, request);
		return (List<MemberCouponDto>) model.getAttribute("coupons");
	}
	
	@GetMapping("/coupon/modify")
	public CouponDto getCoupon(HttpServletRequest request, Model model) {
		adminExecuteMR = new AdminCouponMordifyService(couponDao);
		adminExecuteMR.execute(model, request);
		return (CouponDto) model.getAttribute("coupon");
	}
	
}
