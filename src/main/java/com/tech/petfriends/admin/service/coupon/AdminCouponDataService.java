package com.tech.petfriends.admin.service.coupon;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.petfriends.admin.dto.CouponDto;
import com.tech.petfriends.admin.mapper.CouponDao;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;

public class AdminCouponDataService implements AdminExecuteModelRequest {

	private CouponDao couponDao;
	
	public AdminCouponDataService(CouponDao couponDao) {
		this.couponDao = couponDao;
	}
	
	@Override
	public void execute(Model model, HttpServletRequest request) {
		
		String status = request.getParameter("status");
		String kind = request.getParameter("kind");
		String type = request.getParameter("type");
		String sort = request.getParameter("sort");
		
		List<CouponDto> coupons = couponDao.getAllCoupons(status, kind, type, sort);
		model.addAttribute("coupons", coupons);
	}

}
