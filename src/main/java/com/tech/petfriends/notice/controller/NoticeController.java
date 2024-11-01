package com.tech.petfriends.notice.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.petfriends.notice.dao.NoticeDao;
import com.tech.petfriends.notice.dto.NoticeDto;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
    private NoticeDao noticeDao;

	@GetMapping("/noticePage")
	public String NoticePage(HttpServletRequest request, Model model) {
		model.addAttribute("main_navbar_id","noticeMain");
		model.addAttribute("sub_navbar_id","notice_notice");
		
		ArrayList<NoticeDto> noticeList = noticeDao.NoticeList();
        model.addAttribute("noticeList", noticeList);
		
		
		return "/notice/noticePage";
	}
	
	@GetMapping("/noticeView")
	public String NoticeView(HttpServletRequest request, Model model) {
		model.addAttribute("main_navbar_id","noticeMain");
		model.addAttribute("sub_navbar_id","notice_notice");
		
		return "/notice/noticeView";
	}
	
	@GetMapping("/eventPage")
	public String EventPage(HttpServletRequest request, Model model) {
		model.addAttribute("main_navbar_id","noticeMain");
		model.addAttribute("sub_navbar_id","notice_event");
		return "/notice/eventPage";
	}
	
	@GetMapping("/endEventPage")
	public String EndEventPage(HttpServletRequest request, Model model) {
		model.addAttribute("main_navbar_id","noticeMain");
		model.addAttribute("sub_navbar_id","notice_endEvent");
		return "/notice/endEventPage";
	}
}
