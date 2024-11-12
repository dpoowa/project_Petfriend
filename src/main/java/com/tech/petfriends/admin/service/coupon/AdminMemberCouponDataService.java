package com.tech.petfriends.admin.service.coupon;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.petfriends.admin.dto.MemberCouponDto;
import com.tech.petfriends.admin.mapper.CouponDao;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;

@Service
public class AdminMemberCouponDataService implements AdminExecuteModelRequest {
	
	private CouponDao couponDao;
	
	public AdminMemberCouponDataService(CouponDao couponDao) {
		this.couponDao = couponDao;
	}
	
	@Override
	public void execute(Model model, HttpServletRequest request) {
		String status = request.getParameter("status");
		String searchOrder = request.getParameter("searchOrder");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String memberCode = request.getParameter("memberCode");
		String couponCode = request.getParameter("couponCode");
		String orderCode = request.getParameter("orderCode");

		List<MemberCouponDto> coupons = couponDao.getMemberCoupons(status, searchOrder, startDate, endDate, memberCode,
				couponCode, orderCode);
		
		model.addAttribute("coupons", coupons);
	}

}
