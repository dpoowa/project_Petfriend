package com.tech.petfriends.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.petfriends.admin.mapper.AdminPageDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;
import com.tech.petfriends.admin.service.AdminExecuteModelRequest;
import com.tech.petfriends.login.dto.MemberLoginDto;
import com.tech.petfriends.login.mapper.MemberMapper;
import com.tech.petfriends.mypage.dao.MypageDao;
import com.tech.petfriends.mypage.dto.MyPetDto;
import com.tech.petfriends.notice.dao.NoticeDao;
import com.tech.petfriends.notice.dto.EventDto;
import com.tech.petfriends.notice.dto.NoticeDto;

@RestController
@RequestMapping("/admin")
public class AdminNoticeEventRestController {

	@Autowired
	AdminPageDao adminDao;

	@Autowired
	NoticeDao noticeDao;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	MypageDao mypageDao;

	AdminExecuteModel adminExecuteM;

	AdminExecuteModelRequest adminExecuteMR;

	@GetMapping("/notice_notice_list")
	public ArrayList<NoticeDto> noticeNoticeList() {
		ArrayList<NoticeDto> noticeList = noticeDao.noticeAdminList();
		return noticeList;
	}

	@GetMapping("/notice_event_list")
	public ArrayList<EventDto> noticeEventList() {
		ArrayList<EventDto> eventList = noticeDao.eventAdminList();
		return eventList;
	}

	// 공지사항 삭제 메서드
	@DeleteMapping("/deleteNotice")
	public ResponseEntity<String> deleteNotice(@RequestParam("id") Long noticeNo) {
		try {
			int isDeleted = noticeDao.deleteNotice(noticeNo);
			if (isDeleted > 0) {
				return ResponseEntity.ok("Notice deleted successfully.");
			} else {
				return ResponseEntity.status(404).body("Notice not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("An error occurred while deleting the notice.");
		}
	}
	
	// 이벤트 삭제 메서드
	@DeleteMapping("/deleteEvent")
	public ResponseEntity<String> deleteEvent(@RequestParam("id") Long eventNo) {
		try {
			int isDeleted = noticeDao.deleteEvent(eventNo);
			if (isDeleted > 0) {
				return ResponseEntity.ok("Event deleted successfully.");
			} else {
				return ResponseEntity.status(404).body("Event not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("An error occurred while deleting the Event.");
		}
	}
	
	@PostMapping("/setVisibilityForNotices")
    public ResponseEntity<?> setVisibilityForNotices(@RequestBody Map<String, Object> request) {
        @SuppressWarnings("unchecked")
		List<Long> ids = (List<Long>) request.get("ids");
        String visibility = (String) request.get("visibility");

        if (ids != null && !ids.isEmpty()) {
            boolean isVisible = "show".equals(visibility);
            noticeDao.updateVisibilityNotice(ids, isVisible); // MyBatis 매퍼에서 공개 여부 업데이트
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("유효하지 않은 요청입니다.");
        }
    }
	
	@PostMapping("/setVisibilityForEvents")
    public ResponseEntity<?> setVisibilityForEvents(@RequestBody Map<String, Object> request) {
        @SuppressWarnings("unchecked")
		List<Long> ids = (List<Long>) request.get("ids");
        String visibility = (String) request.get("visibility");

        if (ids != null && !ids.isEmpty()) {
            boolean isVisible = "show".equals(visibility);
            noticeDao.updateVisibilityEvent(ids, isVisible); // MyBatis 매퍼에서 공개 여부 업데이트
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("유효하지 않은 요청입니다.");
        }
    }
	
	@GetMapping("/customer_list")
	public ArrayList<MemberLoginDto> customerList() {
		ArrayList<MemberLoginDto> memberlist = memberMapper.memberList();	
		return memberlist;
	}
	
	@GetMapping("/pet_list")
	public ArrayList<MyPetDto> petList() {
		ArrayList<MyPetDto> petlist = mypageDao.getPetList();
		return petlist;
	}

	@PostMapping("/updateCustomerType")
	public ResponseEntity<Map<String, String>> updateCustomerType(@RequestBody Map<String, Object> request) {
	    @SuppressWarnings("unchecked")
		List<Long> ids = (List<Long>) request.get("ids");
	    String newType = (String) request.get("newType");

	    if (ids == null || ids.isEmpty() || newType == null || newType.isEmpty()) {
	        return ResponseEntity.badRequest().body(Map.of("message", "유효하지 않은 요청입니다."));
	    }

	    try {
	        memberMapper.updateCustomerType(ids, newType); // MyBatis 매퍼 호출
	        return ResponseEntity.ok(Map.of("message", "회원 유형이 성공적으로 변경되었습니다."));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "회원 유형 변경 중 오류가 발생했습니다."));
	    }
	}
	
	@PostMapping("/deletePetImages")
	public ResponseEntity<String> deletePetImages(@RequestBody Map<String, Object> request) {
		@SuppressWarnings("unchecked")
	    List<String> petCodes = (List<String>) request.get("petCodes");
	    if (petCodes == null || petCodes.isEmpty()) {
	        return ResponseEntity.badRequest().body("유효하지 않은 요청입니다.");
	    }

	    try {
	        petCodes.forEach(petCode -> {
	            String petImg = mypageDao.getPetImgForPetCode(petCode); // petCode로 이미지 파일명 조회
	            if (petImg != null && !petImg.isEmpty()) {
	                // 파일 경로 설정 (서버의 실제 경로를 절대 경로로 사용)
	            	String imagesDir = new File("src/main/resources/static/Images/pet/").getAbsolutePath();
	                File file = new File(imagesDir, petImg);
	                
	                // 파일 존재 여부 확인 및 삭제 처리
	                if (file.exists()) {
	                    boolean deleted = file.delete();
	                    if (!deleted) {
	                        System.err.println("이미지 삭제에 실패했습니다: " + file.getAbsolutePath());
	                    } else {
	                        System.out.println("이미지 삭제 성공: " + file.getAbsolutePath());
	                    }
	                } else {
	                    System.out.println("이미지가 존재하지 않습니다: " + file.getAbsolutePath());
	                }
	            }
	            mypageDao.deletePetImgForPetCode(petCode);
	        });
	        return ResponseEntity.ok("이미지 삭제가 완료되었습니다.");
	    } catch (Exception e) {
	        e.printStackTrace(); // 디버깅을 위한 예외 출력
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 삭제 중 오류가 발생했습니다.");
	    }
	}

}
