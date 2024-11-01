package com.tech.petfriends.community.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tech.petfriends.community.dto.CCategoryDto;
import com.tech.petfriends.community.dto.CDto;
import com.tech.petfriends.community.mapper.IDao;
import com.tech.petfriends.community.service.CCategoryService;
import com.tech.petfriends.community.service.CCommentReplyService;
import com.tech.petfriends.community.service.CCommentService;
import com.tech.petfriends.community.service.CContentVieWService;
import com.tech.petfriends.community.service.CDeleteService;
import com.tech.petfriends.community.service.CDownloadService;
import com.tech.petfriends.community.service.CModifyService;
import com.tech.petfriends.community.service.CPostListService;
import com.tech.petfriends.community.service.CServiceInterface;
import com.tech.petfriends.community.service.CUpdateLikeService;
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

@PostMapping("/delete")
public String delete(HttpServletRequest request, Model model) {
    System.out.println("community_delete");
    model.addAttribute("request", request);

    serviceInterface = new CDeleteService(iDao);
    serviceInterface.execute(model);

    return "redirect:/community/main";
}

@PostMapping("/comment")
public String comment(HttpServletRequest request, Model model) {
    System.out.println("community_comment");
    model.addAttribute("request", request);

    serviceInterface = new CCommentService(iDao);
    serviceInterface.execute(model);

    return "redirect:/community/contentView?board_no=" + request.getParameter("board_no");
}

@PostMapping("/commentReply")
public String commentReply(HttpServletRequest request, Model model) {
    System.out.println("commentReply");
    model.addAttribute("request", request);

    serviceInterface = new CCommentReplyService(iDao);
    serviceInterface.execute(model);

    return "redirect:/community/contentView?board_no=" + request.getParameter("board_no");
}

@PostMapping("/replyDelete")
public String replyDelete(HttpServletRequest request, Model model) {
    System.out.println("replyDelete");
    model.addAttribute("request", request);

    String board_no = request.getParameter("board_no");
	String comment_no = request.getParameter("comment_no");
//	String user_id = request.getParameter("user_id");
//	String comment_content = request.getParameter("comment_content");
	String parent_comment_no = request.getParameter("parent_comment_no");
	String comment_level = request.getParameter("comment_level");
	String comment_order_no = request.getParameter("comment_order_no");
	
	
    // 댓글 삭제 시도
    int rn = iDao.replyDelete(comment_no, parent_comment_no, comment_level, comment_order_no);
    if (rn == 0) {
        // 삭제 실패 (상위 댓글이 존재)
    	System.out.println("댓글 삭제 실패");
    	model.addAttribute("msg", "이 댓글은 상위 댓글을 가지고 있어 삭제할 수 없습니다.");
    	model.addAttribute("url", "/community/contentView?board_no=" + board_no);
    	return "/community/alert";
     
    } else {
        // 삭제 성공
    	System.out.println("댓글 삭제 성공");
        iDao.stepInit(comment_no,parent_comment_no, comment_level);        
        model.addAttribute("msg", "댓글이 삭제됐습니다.");	        
        model.addAttribute("url", "/community/contentView?board_no=" + board_no);
        return "/community/alert";
    }
}

	@PostMapping("/updateLike")
	public String updateLike(HttpServletRequest request, Model model) {
    System.out.println("updateLike");
    model.addAttribute("request", request);

    serviceInterface = new CUpdateLikeService(iDao);
    serviceInterface.execute(model);

    return "redirect:/community/contentView?board_no=" + request.getParameter("board_no");
}

}