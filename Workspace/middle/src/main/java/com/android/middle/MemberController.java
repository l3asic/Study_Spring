package com.android.middle;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import common.CommonService;
import customer.CustomerDAO;
import customer.CustomerVO;
import member.MemberDAO;
import member.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	Gson gson = new Gson();
	
	@Autowired CommonService service;
	//@Autowired MemberDAO dao;
	@Autowired @Qualifier("hanul") private SqlSession sql;
	@ResponseBody
	@RequestMapping("/login")
	public void login(HttpServletRequest req , HttpServletResponse res) {
		MemberVO vo = new MemberVO();
		vo.setId("aaa");
		vo.setPw("aaa");
		vo = sql.selectOne("member.mapper.select", vo);
		System.out.println(vo.getGender());
	}
	@ResponseBody
	@RequestMapping("/join")
	public void test(HttpServletRequest req , HttpServletResponse res ,HttpSession session) throws IOException {
		//insert 처리 , 로그인 Mapping만들고 로그인처리도 DB에서 되게끔 처리.
		//MemberMapper 만들기 . insert  , selectone
		String tempVo = req.getParameter("vo");
		MemberVO vo = gson.fromJson(tempVo, MemberVO.class);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		int result = 0;
		MultipartRequest mulReq = (MultipartRequest) req;
		MultipartFile file = mulReq.getFile("file");
		if(file != null) {
			System.out.println("Null 아님 파일 들어옴");
			String path = service.fileupload("and", file, session);
			String server_path = "http://" + req.getLocalAddr()
			+ ":" + req.getLocalPort() + req.getContextPath()+"/resources/";
			System.out.println(server_path + path);
			vo.setFilepath(server_path + path); 
			result = sql.insert("member.mapper.insert", vo);
		}else {
			System.out.println("Null임 파일 안들어옴..");
		
		}
		writer.print(result);
	}
	
	
}
