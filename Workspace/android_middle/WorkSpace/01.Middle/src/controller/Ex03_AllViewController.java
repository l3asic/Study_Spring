package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
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

import dto.GridDTO;

/**
 * Servlet implementation class Ex03_AllViewController
 */
@WebServlet("*.vw")
public class Ex03_AllViewController extends HttpServlet {
	Gson gson = new Gson();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("Ex03_AllViewController까지 접근을 했음.");
		System.out.println(req.getServletPath());
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		
		if(req.getServletPath().equals("/list.vw")) {
			System.out.println("체크 ");
			selectList(req , res);
		}
		System.out.println("if문 밖");
		//initMybatis();
	}
	
	
	
	public void selectList(HttpServletRequest req, HttpServletResponse res) {
		try {
			
		String resource = "mybatis/config.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//select.selectone .. 기타등등.. mapper의 네임스페이스 . id
			  List<GridDTO> list = session.selectList("mybatis.test.list");
			  String data = gson.toJson(list);
			  PrintWriter out = res.getWriter();
			  out.print(data);
			  session.close();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initMybatis() {
		try {
		String resource = "mybatis/config.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//select.selectone .. 기타등등.. mapper의 네임스페이스 . id
			  int data = session.selectOne("mybatis.test.test");
			  System.out.println(data);
			  session.close();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
