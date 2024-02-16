package controller.log;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/log")
public class log extends HttpServlet {

	public log() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void doPost(HttpServletRequest rq, HttpServletResponse rs) {

		//各種変数の定義
		StringBuffer ERROR = new StringBuffer();

		try {

			HttpSession session = rq.getSession();
			String user_id = (String) session.getAttribute("user_id");

			if (user_id == null) {
				RequestDispatcher dispatcher = rq.getRequestDispatcher("./login.jsp");
				dispatcher.forward(rq, rs);
			} else {
				RequestDispatcher dispatcher = rq.getRequestDispatcher("./log.jsp");
				dispatcher.forward(rq, rs);
			}

		} catch (ServletException e) { //各種エラーの受け取り
			ERROR.append(e);
		} catch (IOException e) {
			ERROR.append(e);
		} finally {

			//エラーを出力する。
			System.out.print(ERROR);

		}

	}

	public void doGet(HttpServletRequest rq, HttpServletResponse rs) {
		doPost(rq, rs);
	}
}
