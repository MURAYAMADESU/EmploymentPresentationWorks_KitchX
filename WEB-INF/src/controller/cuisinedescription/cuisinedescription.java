package controller.cuisinedescription;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cuisinedescription")
public class cuisinedescription extends HttpServlet{

	public cuisinedescription() {
		// コンストラクター
	}
	
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) {
		
		//各種変数の定義
		StringBuffer ERROR = new StringBuffer();
		
		try {
			
			String recipe_id = rq.getParameter("recipe_id");
			
			//フォワード
			rq.setAttribute("recipe_id", recipe_id); 		//レシピIDを送信する。
			RequestDispatcher dispatcher  = rq.getRequestDispatcher("/cuisinedescription.jsp");
			dispatcher.forward(rq, rs);
			
		} catch (ServletException e) {	//各種エラーの受け取り
			ERROR.append(e);
		} catch (IOException e) {
			ERROR.append(e);
		}finally {
			
			//エラーを出力する。
			System.out.print(ERROR);
			
		}
		
	}
	
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) {
		doPost(rq, rs);
	}

}
