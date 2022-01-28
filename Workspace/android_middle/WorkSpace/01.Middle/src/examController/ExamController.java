package examController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("*.exam")
public class ExamController extends HttpServlet {
	String resource = "mybatis/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	SqlSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		initMybatis();
		HttpSession ssn = req.getSession();
		ssn.setAttribute("admin", "N");
		if (req.getServletPath().equals("/grading.exam")) {
			List<ExamVO> list = session.selectList("mybatis.test.lsit");
			int total_cnt = Integer.parseInt(req.getParameter("tt_cnt") + "");
			for (int i = 0; i < total_cnt; i++) {
				String dap = req.getParameter((i + 1) + "");
				if (list.get(i).getExam_dap().equals(dap)) {
					list.get(i).setResult("정답");
			
				} else {
					list.get(i).setResult("오답");
				}
				list.get(i).setUser_dap(dap == null ? "--" : dap);
			}
			req.setAttribute("list", list);
			req.setAttribute("test", "보냄");
			
			RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
			rd.forward(req, resp);

		} else {
			List<ExamVO> list = session.selectList("mybatis.test.lsit");
			req.setAttribute("list", list);
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}
	}

	public void initMybatis() {

		try {
			inputStream = Resources.getResourceAsStream(resource);

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
