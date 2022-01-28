package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import dto.GridDTO;
import dto.MemberDTO;

/**
 * Servlet implementation class TestController
 */
@WebServlet("*.test")
public class TestController extends HttpServlet {
	Gson gson = new Gson();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("크롬에서 먼저확인 , 안드어플실행 ");
		System.out.println(req.getServletPath());
		if(req.getServletPath().equals("/join.test")) { //회원가입 로직
			MultipartRequest multi 
			= new MultipartRequest(req, req.getRealPath("/") , 3000000);
			int size =  Integer.parseInt(multi.getParameter("size") );
			MemberDTO dto = null;
			for (int i = 0; i < size; i++) {
				String fromAnd = multi.getParameter("param" + (i+1));
				System.out.println(fromAnd);
				dto = gson.fromJson(fromAnd, MemberDTO.class);
				System.out.println(dto);
				insertMember(req,res,dto);
			}
			
	
		}else if(req.getServletPath().equals("/login.test")) {//로그인 로직
			
		}
	}
	
	
	
	
	public void insertMember(HttpServletRequest req, HttpServletResponse res , MemberDTO dto) {
		try {
			
		String resource = "mybatis/config.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//select.selectone .. 기타등등.. mapper의 네임스페이스 . id
			  int result = session.insert("mybatis.test.insertmem", dto);
			  session.commit();
			  //String data = gson.toJson(list);
			  //PrintWriter out = res.getWriter();
			  //out.print(data);
			  session.close();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
