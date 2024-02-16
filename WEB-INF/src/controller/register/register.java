package controller.register;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.register.dao.InsertUser;
import controller.register.dao.SearchMail;

@WebServlet("/register")
public class register extends HttpServlet {

	private String registeredURL = "/registered.jsp"; //転送先のjspファイルを指定
	private String cantRegisterURL = "/register.jsp";

	public register() { //必要に応じて転送先を変更

	}

	public void doPost(HttpServletRequest rq, HttpServletResponse rs) {

		StringBuffer ERROR = new StringBuffer(); //エラーコード格納用

		try {

			//文字コードの設定
			rq.setCharacterEncoding("UTF-8");
			rs.setCharacterEncoding("UTF-8");

			//各種データの受け取り
			String name = rq.getParameter("name");
			String mail = rq.getParameter("mail");
			String pass = rq.getParameter("pass");
			
			//全てのデータを入力しているかどうか
			if((name.isEmpty() || mail.isEmpty()) || pass.isEmpty()) {
				
				rq.setAttribute("ERROR", "全て入力してください");
				RequestDispatcher dispatcher = rq.getRequestDispatcher(cantRegisterURL);
				dispatcher.forward(rq, rs);
				
			}else {

			//メールがすでに登録されているか確認する。
			if (SearchMail.search(mail) == null) {
				//メールが登録されていなかった場合

				/*
				//パスワードのハッシュ化
				try {
					hashValue hash = new hashValue();		//パスワードのハッシュ化用クラス
					hash.setPassWord(passWord);
					hashPassWord = hash.getPassWord();
				} catch (Exception e) {
					System.out.print(e);
				}
				 */

				//アカウントの作成
				InsertUser insertUser = new InsertUser();
				insertUser.setInsertName(name); //データのセット
				insertUser.setInsertPass(pass);
				insertUser.setInsertMail(mail);
				ERROR.append(insertUser.insertData()); //実行

				//実行結果をリスポンスにセットする。
				rq.setAttribute("ERROR", ERROR);
				RequestDispatcher dispatcher = rq.getRequestDispatcher(registeredURL);
				dispatcher.forward(rq, rs);

			} else {
				
				//メールがすでに登録されていた場合
				rq.setAttribute("ERROR",  "メールがすでに登録されています");
				RequestDispatcher dispatcher = rq.getRequestDispatcher(cantRegisterURL);
				dispatcher.forward(rq, rs);
				
			}
			
			}

		} catch (IOException e) {
			ERROR.append(e);
		} catch (ServletException e) {
			ERROR.append(e);
		} finally {
			//受け取ったエラーを確実に出力する。
			System.out.print(ERROR.toString());
		}
	}

	public void doGet(HttpServletRequest rq, HttpServletResponse re) {
		doPost(rq, re);
	}

}
