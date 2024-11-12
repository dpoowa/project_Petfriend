package com.tech.petfriends.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tech.petfriends.admin.dto.CouponDto;
import com.tech.petfriends.admin.mapper.AdminPageDao;
import com.tech.petfriends.admin.mapper.AdminSalesDao;
import com.tech.petfriends.admin.mapper.CouponDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;
import com.tech.petfriends.admin.service.AdminSalesService;
import com.tech.petfriends.admin.service.notice.AdminEventEditService;
import com.tech.petfriends.admin.service.notice.AdminNoticeEditService;
import com.tech.petfriends.admin.service.notice.AdminNoticeWriteService;
import com.tech.petfriends.login.dto.MemberLoginDto;
import com.tech.petfriends.login.mapper.MemberMapper;
import com.tech.petfriends.notice.dao.NoticeDao;
import com.tech.petfriends.notice.dto.EventDto;
import com.tech.petfriends.notice.dto.NoticeDto;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

	@Autowired
	AdminPageDao adminDao;
	
	@Autowired
	NoticeDao noticeDao;
	
	@Autowired
	CouponDao couponDao;
		
	@Autowired
	AdminSalesDao adminSalesDao;
  
	@Autowired
	MemberMapper memberDao;

	AdminExecuteModel adminExcuteM;

	AdminExecuteModelRequest adminExcuteMR;
	

	// 어드민 페이지 내부에서의 펫티쳐페이지로 이동
	@GetMapping("/petteacher")
	public String petteacherAdminPage(Model model) {
		return "admin/petteacher";
	}

	@GetMapping("/home")
	public String home() {
		return "admin/home";
	}

	@GetMapping("/order")
	public String order() {
		return "admin/order";
	}

	@GetMapping("/coupon")
	public String couponPage() {
		return "admin/coupon";
	}

	@PostMapping("/coupon/register")
	public String registerCoupon(@RequestBody CouponDto couponDto) {

		couponDao.registerCoupon(couponDto);

		return "redirect:/admin/coupon";
	}

	@GetMapping("/product")
	public String product() {
		return "admin/product";
	}
	
	@GetMapping("/customer_status")
	public String customer_status(Model model) {
		System.out.println("고객");
		int newMember = memberDao.newMemberForWeek();
		int visitMember = memberDao.visitMemberForWeek();
		int withdrawMember = memberDao.withdrawMemberForWeek();
		int total = memberDao.totalMember();
		ArrayList<MemberLoginDto> newMemberList = memberDao.newMemberList();
		ArrayList<MemberLoginDto> withdrawMemberList = memberDao.withdrawMemberList();

		model.addAttribute("newMember",newMember);
		model.addAttribute("visitMember",visitMember);
		model.addAttribute("withdrawMember",withdrawMember);
		model.addAttribute("total",total);
		model.addAttribute("newMemberList",newMemberList);
		model.addAttribute("withdrawMemberList",withdrawMemberList);
		return "admin/customer_status";
	}

	@GetMapping("/customer_info")
	public String customer_info() {
		return "admin/customer_info";
	}
	
	@GetMapping("/pet_info")
	public String pet_info() {
		return "admin/pet_info";
	}

	@GetMapping("/community")
	public String community() {
		return "admin/community";
	}

	@GetMapping("/pethotel")
	public String pethotel() {
		return "admin/pethotel";
	}

	@GetMapping("/pethotel_reserve")
	public String pethotelReserve() {
		return "admin/pethotel_reserve";
	}

	@GetMapping("/notice")
	public String Notice(Model model) {
//		ArrayList<NoticeDto> noticeAdminList = noticeDao.noticeAdminList();
//        model.addAttribute("noticeAdminList", noticeAdminList);
//        ArrayList<EventDto> eventAdminList = noticeDao.eventAdminList();
//        model.addAttribute("eventAdminList", eventAdminList);
		
		return "admin/notice";
	}
	
	@GetMapping("/notice_write")
	public String Notice_write() {
		System.out.println("글작성페이지");
		return "admin/notice_write";
	}
	
	@PostMapping("/notice_write_service")
	public String Notice_write_service(HttpServletRequest request, 
	                                   @RequestParam MultipartFile thumbnail,
	                                   @RequestParam MultipartFile slideImg, 
	                                   Model model) {
	    model.addAttribute("request", request);
	    model.addAttribute("thumbnail", thumbnail);
	    model.addAttribute("slideImg", slideImg);

	    AdminNoticeWriteService adminNoticeWriteService = new AdminNoticeWriteService(noticeDao);
	    adminNoticeWriteService.execute(model);

	    return "redirect:/admin/notice";
	}
	
	@GetMapping("/notice_edit")
	public String Notice_edit(@RequestParam("id") Long noticeId, Model model) {
	    // 공지사항 데이터를 ID로 조회
	    NoticeDto noticeDto = noticeDao.findNoticeById(noticeId);

	    // 조회한 데이터를 모델에 추가하여 JSP에서 사용할 수 있게 함
	    model.addAttribute("notice", noticeDto);
	    
	    // 수정 화면으로 이동
	    return "admin/notice_edit";
	}
	
	@GetMapping("/event_edit")
	public String Event_edit(@RequestParam("id") Long eventId, Model model) {
	    // 공지사항 데이터를 ID로 조회
	    EventDto eventDto = noticeDao.findEventById(eventId);
	    
	    // 날짜 형식 지정
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
	    // 시작일과 종료일을 포맷팅하여 문자열로 변환
	    String formattedStartDate = dateFormat.format(eventDto.getEvent_startdate());
	    String formattedEndDate = dateFormat.format(eventDto.getEvent_enddate());

	    // 변환된 날짜 문자열을 모델에 추가
	    model.addAttribute("event", eventDto);
	    model.addAttribute("formattedStartDate", formattedStartDate);
	    model.addAttribute("formattedEndDate", formattedEndDate);

	    // 수정 화면으로 이동
	    return "admin/event_edit";
	}
	
	@PostMapping("/notice_edit_service")
	public String Notice_edit_service(HttpServletRequest request, 
	                                   Model model) {
	    model.addAttribute("request", request);

	    AdminNoticeEditService adminNoticeEditService = new AdminNoticeEditService(noticeDao);
	    adminNoticeEditService.execute(model);

	    return "redirect:/admin/notice";
	}
	
	@PostMapping("/event_edit_service")
	public String EventEditService(
		HttpServletRequest request,
	    @RequestParam("thumbnail") MultipartFile thumbnail,
	    @RequestParam("slideImg") MultipartFile slideImg,
	    Model model) {
	    
		model.addAttribute("request",request);
		model.addAttribute("thumbnail",thumbnail);
		model.addAttribute("slideImg",slideImg);
		
		AdminEventEditService adminEventEditService = new AdminEventEditService(noticeDao);
		adminEventEditService.execute(model);

	    return "redirect:/admin/notice";
	}
	
	@GetMapping("/searchNotices")
	public String searchNotices(@RequestParam("title") String title, Model model) {
	    List<NoticeDto> noticeAdminList = noticeDao.searchNoticesByTitle(title);
	    model.addAttribute("noticeAdminList", noticeAdminList);
	    return "admin/notice"; // 검색 결과를 표시할 JSP 경로
	}
	

	@GetMapping("/sales")
	public String sales(Model model) {
		
		adminExcuteM = new AdminSalesService(adminSalesDao);
		adminExcuteM.execute(model);
		
		return "admin/sales";
	}
	
	@GetMapping("/customer")
	public String customer() {
		return "admin/customer";
	}

}
