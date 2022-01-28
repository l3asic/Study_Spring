package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

@WebServlet("*.andtest")
public class Ex01_Controller extends HttpServlet {
// 맵핑뒤에 .and로 끝나면 모든 처리를 여기서 하겟다. ↑
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		//MultiPart => JSP/SERVLET 
	//	=> 이클립스 ( cos.jar API) <= req.casting MultiPart처리가 가능한형태로
	//MultiPart => Common IO , Common upload , MultipartResolver 
	//기본적으로 파일을 제외한 Multipart데이터는 ↑설정만 해두면 자동으로됨.(편리함)
	//MultipartRequest
	// MultipartRequest multi = <=req;(req에 있는 내용은 손실이됨)
	// MultepartRequest를 초기화할때는 파일이 실제로 저장될 경로도 필요함
	
	String path = req.getRealPath("/");
	System.out.println(path);
	MultipartRequest multi = new MultipartRequest(req, path , 300000);	
	String returnString = multi.getParameter("key");
		System.out.println();
		//나중에 Srping으로 바꿀꺼임.
		//ANDROID에서 요청을 함 (url , 필요하다면 파라메터도 보냄)
		//DB에 바로접속이 안되기때문에 미들웨어라는 WAS서버 , Node.js....

		PrintWriter out = res.getWriter();
		out.print("servlet = > " + returnString);
		
		
		
		
	}

}
