package controller.myrecipe;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.myrecipe.dao.MyAllRecipe;

@WebServlet("/myrecipe")
public class myrecipe extends HttpServlet {

	public myrecipe() {
		// コンストラクター
	}

	public void doPost(HttpServletRequest rq, HttpServletResponse rs) {
		
		//各種変数の宣言
		StringBuffer ERROR = new StringBuffer();
		String allRecipeId = null;
		
		try {
			
			//セッションの所得
			HttpSession session = rq.getSession();
			String user_id = (String) session.getAttribute("user_id");
			if (user_id == null) { //ログイン済みのユーザかどうか
				//ログインされていない場合
				RequestDispatcher dispatcher = rq.getRequestDispatcher("/login.jsp");
				dispatcher.forward(rq, rs);		//ログインページにフォワードする。
			} else {
				//ログインしていた場合
				allRecipeId = MyAllRecipe.Get(user_id);
				
				if(allRecipeId.isEmpty()) {
					//作品を投稿したことがない場合
					rq.setAttribute("ERROR", "投稿済みの作品がありません");
					RequestDispatcher dispatcher = rq.getRequestDispatcher("/error.jsp");
					dispatcher.forward(rq, rs);
				}else {
					//レシピ一覧サイトにフォワードする
					rq.setAttribute("allRecipeId", allRecipeId);
					RequestDispatcher dispatcher = rq.getRequestDispatcher("/myrecipe.jsp");
					dispatcher.forward(rq, rs);
				}
			}

		} catch (ServletException e) {
			ERROR.append(e);
		} catch (IOException e) {
			ERROR.append(e);
		} finally {
			//エラーがあれば出力する。
			System.out.print(ERROR.toString());
		}
	}

	//Getでデータが送られてきている場合
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) {
		doPost(rq, rs);
	}

}
