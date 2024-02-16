package controller.trashrecipe;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.trashrecipe.dao.TrashRecipeDao;

@WebServlet("/trashrecipe")
public class trashrecipe extends HttpServlet{

	public trashrecipe() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void doPost(HttpServletRequest rq, HttpServletResponse rs) {
		
		//各種メゾットと変数の宣言
		StringBuffer ERROR = new StringBuffer();
		HttpSession session = rq.getSession();		//セッションの宣言
		String user_id = (String)session.getAttribute("user_id");		//セッションからユーザIDを取り込む
		
		try {
			
			String recipe_id = rq.getParameter("recipe_id");
			
			TrashRecipeDao.Do(user_id, recipe_id);
			
			RequestDispatcher dispatcher = rq.getRequestDispatcher("./trashrecipe.jsp");
			dispatcher.forward(rq, rs);
			
		} catch (FileNotFoundException e) {
			ERROR.append(e);
		} catch (ServletException e) {
			ERROR.append(e);
		} catch(IOException e) {
			ERROR.append(e);
		}catch (Exception e) {
			ERROR.append(e);
		}finally {
			//エラーがあれば出力する。
			System.out.print(ERROR.toString());
		}
		
	
	}
	
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) {
		doPost(rq, rs);
	}
	
}
