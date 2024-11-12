package com.tech.petfriends.admin.service.coupon;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.petfriends.admin.dto.CouponDto;
import com.tech.petfriends.admin.mapper.CouponDao;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;

@Service
public class AdminCouponMordifyService implements AdminExecuteModelRequest {

	private CouponDao couponDao;
	
	public AdminCouponMordifyService(CouponDao couponDao) {
		this.couponDao = couponDao;
	}
	
	@Override
	public void execute(Model model, HttpServletRequest request) {
		String cp_no = request.getParameter("cpNo");

		CouponDto coupon = couponDao.getCouponById(cp_no);
		model.addAttribute("coupon", coupon);
	}

}
