package controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.login.dao.LoginUser;

@WebServlet("/login")
public class login extends HttpServlet {

	public String URL = "/logined.jsp"; //転送先のjspファイル
	public String cantLoginURL = "/login.jsp";

	public login() {
		//コンストラクター
	}

	public void doPost(HttpServletRequest rq, HttpServletResponse rs) {

		StringBuffer ERROR = new StringBuffer();

		try {

			//文字コードの設定
			rq.setCharacterEncoding("UTF-8");
			rs.setCharacterEncoding("UTF-8");

			//各種変数の宣言
			String user_id = null;

			//jspからのデータを所得する。
			String mail = rq.getParameter("mail");
			String pass = rq.getParameter("pass");

			if (mail.isEmpty() || pass.isEmpty()) {		//全てのデータが入力されているか確認
				
				rq.setAttribute("result", "全て入力してください");
				RequestDispatcher dispatcher = rq.getRequestDispatcher(cantLoginURL);
				dispatcher.forward(rq, rs);
				
			} else {
				
				//入力データをデータベースから検索後user_idを所得
				user_id = LoginUser.login(mail, pass);
				if (user_id == null) {
					
					//ログイン失敗
					rq.setAttribute("result", "メールまたはパスワードが違います");
					RequestDispatcher dispatcher = rq.getRequestDispatcher(cantLoginURL);
					dispatcher.forward(rq, rs);
					
				} else {
					
					//セッションを生成(120秒)
					HttpSession session = rq.getSession();
					session.setAttribute("user_id", user_id);
					session.setMaxInactiveInterval(12000);	//セッションの有効期限の設定
					
					//ログイン成功
					rq.setAttribute("result", "おかえりなさい、ログインしました");
					RequestDispatcher dispatcher = rq.getRequestDispatcher(URL);
					dispatcher.forward(rq, rs);
					
				}
			}

		} catch (IOException e) {
			ERROR.append(e);
		} catch (ServletException e) {
			ERROR.append(e);
		} finally {
			//受け取ったエラーを出力する。
			System.out.print(ERROR.toString());
		}

	}

	public void doGet(HttpServletRequest rq, HttpServletResponse rs) {
		doPost(rq, rs);
	}
}
