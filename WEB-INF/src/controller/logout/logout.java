package controller.logout;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class logout extends HttpServlet {

	public logout() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//Getでデータが送られてきている場合
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) {
		StringBuffer ERROR = new StringBuffer();

		try {
			HttpSession session = rq.getSession();
			session.removeAttribute("user_id");

			RequestDispatcher dispatcher = rq.getRequestDispatcher("/logout.jsp");
			dispatcher.forward(rq, rs);
		} catch (ServletException e) {
			ERROR.append(e);
		} catch (IOException e) {
			ERROR.append(e);
		}
		
	}
	
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) {
		doPost(rq, rs);
	}

}
