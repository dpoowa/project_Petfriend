package com.tech.petfriends.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.petfriends.admin.dto.CouponDto;
import com.tech.petfriends.admin.dto.MemberCouponDto;
import com.tech.petfriends.admin.mapper.CouponDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;
import com.tech.petfriends.admin.service.coupon.AdminCouponDataService;

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

	@GetMapping("/memberCoupon/data")
	public List<MemberCouponDto> getMemberCouponData(HttpServletRequest request) {

		String status = request.getParameter("status");
		String searchOrder = request.getParameter("searchOrder");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String memberCode = request.getParameter("memberCode");
		String couponCode = request.getParameter("couponCode");
		String orderCode = request.getParameter("orderCode");

		List<MemberCouponDto> coupons = couponDao.getMemberCoupons(status, searchOrder, startDate, endDate, memberCode,
				couponCode, orderCode);
		return coupons;
	}
	
	@GetMapping("/coupon/modify")
	public CouponDto getCoupon(HttpServletRequest request) {

		String cp_no = request.getParameter("cpNo");

		CouponDto coupon = couponDao.getCouponById(cp_no);

		return coupon;
	}
	
	@PutMapping("/coupon/update")
	public String updateCoupon(HttpServletRequest request, @RequestBody CouponDto couponDto) {

		String cp_no = request.getParameter("cpNo");
		couponDto.setCp_no(Integer.parseInt(cp_no));
		
		couponDao.updateCoupon(couponDto);
		
	    return "redirect:/admin/coupon";
	}
	
	@DeleteMapping("/coupon/delete")
	public String deleteCoupon(HttpServletRequest request) {

		String cp_no = request.getParameter("cpNo");
		
		couponDao.deleteCoupon(cp_no);
	    
	    return "redirect:/admin/coupon";
	}
}
