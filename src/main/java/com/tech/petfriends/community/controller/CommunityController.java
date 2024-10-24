package com.tech.petfriends.community.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tech.petfriends.community.dto.CCategoryDto;
import com.tech.petfriends.community.dto.CDto;
import com.tech.petfriends.community.mapper.IDao;
import com.tech.petfriends.community.service.CCategoryService;
import com.tech.petfriends.community.service.CContentVieWService;
import com.tech.petfriends.community.service.CDownloadService;
import com.tech.petfriends.community.service.CModifyService;
import com.tech.petfriends.community.service.CPostListService;
import com.tech.petfriends.community.service.CServiceInterface;
import com.tech.petfriends.community.service.CWriteService;


@Controller
@RequestMapping("/community")
public class CommunityController {
	
	@Autowired
	private IDao iDao;
	
	@Autowired
	private CServiceInterface serviceInterface;
	
	//커뮤니티 페이지로 이동
	@GetMapping("/main")
	public String communityMain(HttpServletRequest request, Model model) {
		System.out.println("community_main() ctr");
		serviceInterface = new CPostListService(iDao);
		serviceInterface.execute(model); 
	
		return "/community/main";
	}
	
	@GetMapping("/writeView")
	public String writeView(HttpServletRequest request,Model model) {
		
		model.addAttribute("request",request);
		serviceInterface = new CCategoryService(iDao);
		serviceInterface.execute(model);
		
		
		return "/community/writeView";
	}
	
	
	@PostMapping("/write")
	public String communityWrite(MultipartHttpServletRequest mtfRequest, Model model) {
		System.out.println("community_write");
		model.addAttribute("request", mtfRequest);
		 
		serviceInterface = new CWriteService(iDao);
		serviceInterface.execute(model);
		
		return "redirect:/community/main";

	}

	@GetMapping("/download")
	public String download(HttpServletRequest request, Model model,
			HttpServletResponse response) throws Exception {
		
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		serviceInterface = new CDownloadService(iDao);
		serviceInterface.execute(model);
		
		String bid = request.getParameter("bid");
	
		return "contentView?bid=" + bid;
	}


//    @PostMapping("/community/upload")
//    public String uploadImage(MultipartHttpServletRequest request, @RequestParam("upload") MultipartFile file, Model model) {
//        String originalFile = file.getOriginalFilename();
//        String workPath = System.getProperty("user.dir");
//        String root = workPath + "\\src\\main\\resources\\static\\images\\community_img";
//
//        // 파일 이름 및 저장 경로 설정
//        long currentTimeMillis = System.currentTimeMillis();
//        String changeFile = currentTimeMillis + "_" + originalFile;
//        String pathFile = root + "\\" + changeFile;
//
//        try {
//            if (!originalFile.isEmpty()) {
//                file.transferTo(new File(pathFile));
//                String imageUrl = "/static/images/community_img/" + changeFile;
//
//                // 업로드된 이미지 URL을 JSON 형식으로 반환
//                return "{\"uploaded\": 1, \"url\": \"" + imageUrl + "\"}";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "{\"uploaded\": 0, \"error\": {\"message\": \"파일 업로드에 실패했습니다.\"}}";
//    }




@GetMapping("/contentView")
public String contentView(HttpServletRequest request, Model model) {
	System.out.println("contentView() ctr");
	model.addAttribute("request",request);
	serviceInterface = new CContentVieWService(iDao);
	serviceInterface.execute(model); 

	return "/community/contentView";
	
	}


@GetMapping("/getPostsByCategory")
public String getPostsByCategory(@RequestParam("b_cate_no") int bCateNo, Model model) {
    System.out.println("getPostsByCategory() ctr");
    // 카테고리 번호로 게시글 리스트를 가져옴
    List<CDto> postList = iDao.getPostsByCategory(bCateNo);
    model.addAttribute("postList", postList); // 모델에 게시글 리스트 추가

    return "community/postList"; // 부분 뷰 리턴
}

@PostMapping("/modify")
public String modify(MultipartHttpServletRequest mtfRequest, Model model) {
	model.addAttribute("request", mtfRequest);
	serviceInterface = new CModifyService(iDao);
	serviceInterface.execute(model);
	
	
	return "redirect:/community/contentView?board_no=" + mtfRequest.getParameter("board_no");
	
}


@GetMapping("/modifyView")
public String modifyView(@RequestParam("board_no") int board_no, Model model) {
    CDto content = iDao.contentView(Integer.toString(board_no)); // 게시글 정보를 가져옴
    model.addAttribute("contentView", content); // 게시글 정보를 모델에 담아서 JSP로 전달

    
    CCategoryService categoryService = new CCategoryService(iDao);
    List<CCategoryDto> categoryList = iDao.getCategoryList();
    model.addAttribute("categoryList", categoryList);
    
	return "/community/modifyView";
	
	}



}