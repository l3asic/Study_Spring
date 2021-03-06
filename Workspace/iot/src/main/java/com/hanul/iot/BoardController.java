package com.hanul.iot;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import board.BoardPage;
import board.BoardServiceImpl;
import board.BoardVO;
import common.CommonService;
import member.MemberVO;

@Controller
public class BoardController {
	
	@Autowired private BoardServiceImpl service;
	@Autowired private BoardPage page;
	@Autowired private CommonService common;
	
	@RequestMapping("/insert.bo")
	public String insert(BoardVO vo, MultipartFile file, HttpSession session) {
		
		
		// 파일 정보가 있다면
		if(! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( common.fileupload("board", file, session)  );
			
		}
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setWriter( member.getId() );
		
		service.board_insert(vo);
		return "redirect:list.bo";
	}
	
	
	// 방명록 신규 글 작성 화면 요청
	@RequestMapping("/new.bo")
	public String board() {
		return "board/new";
	}
	
	
	
	
	
	// 방명록 목록화면 요청
	@RequestMapping ("/list.bo")
	public String list(HttpSession session, @RequestParam (defaultValue = "1") int curPage
			, Model model, String search, String keyword
			, @RequestParam (defaultValue = "10" ) int pageList) {
		session.setAttribute("category", "bo");
		
		// DB에서 방명록 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지를 담음
		page.setSearch(search);		// 검색 조건 값을 page에 담음
		page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
		page.setPageList(pageList);
		model.addAttribute("page", service.board_list(page));		
		return "board/list";
	}
}
