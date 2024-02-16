package controller.trashlog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/trashlog")
public class TrashLog extends HttpServlet{

	public TrashLog() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void doPost(HttpServletRequest rq, HttpServletResponse rs) {
		
		//各種メゾットと変数の宣言
		StringBuffer ERROR = new StringBuffer();
		HttpSession session = rq.getSession();		//セッションの宣言
		String user_id = (String)session.getAttribute("user_id");		//セッションからユーザIDを取り込む
		String userLogPath = getServletContext().getRealPath("./UserLog/");		//Tomcatの仮装パスから絶対パスを所得
		File file = new File(userLogPath + user_id + ".txt");			//ファイル操作
		
		try {
			
			if(file.exists()) {		//ファイルが存在するか確認
				
				file.delete();	//ファイルの削除
				rq.setAttribute("result", "履歴を削除しました。");
				RequestDispatcher dispatcher = rq.getRequestDispatcher("./trashlog.jsp");
				dispatcher.forward(rq, rs);
				
			}	else {
				
				rq.setAttribute("result", "閲覧履歴が存在しません。");
				RequestDispatcher dispatcher = rq.getRequestDispatcher("./trashlog.jsp");
				dispatcher.forward(rq, rs);
				
			}
			
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
