package com.hanul.hello;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
	
	public String member() {
		return "member/info";
	}
	
	
	
	
	@RequestMapping("/joinParam")
	public String member(String name, String gender,  String email, Model model) {
		
		model.addAttribute("method", "RequestParam방식");
		model.addAttribute("name", name);
		model.addAttribute("gender", name);
		model.addAttribute("email", name);
		return "member/info";
	}
	
	@RequestMapping("/joinRequest")
	public String member(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		
		model.addAttribute("name", name );
		model.addAttribute("gender", gender );
		model.addAttribute("email", email );
		return "member/info";
		
	}
	
	
	
	
	
	// 값을 입력받을 페이지... 회원가입 페이지를 생성 및 찾아가기
	
	@RequestMapping("/join")
	public String join() {
		
		
		
		
		return "member/join";	//	forward방식
		//return "redirect"	// redirect방식
	}
	
	
	

}
