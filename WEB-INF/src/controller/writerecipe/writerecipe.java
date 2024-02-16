package controller.writerecipe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import communal.DirOperation;
import communal.ImageFileUpload;
import communal.WriteFile;
import controller.writerecipe.dao.GetRecipeID;
import controller.writerecipe.dao.InsertRecipe;
import controller.writerecipe.dao.SerchRecipe;
import controller.writerecipe.dao.UpDateRecipeImageNum;

@WebServlet("/writerecipe")
@MultipartConfig
public class writerecipe extends HttpServlet {

	public void doPost(HttpServletRequest rq, HttpServletResponse rs) {

		//各種変数の宣言
		String saveImagePath = getServletContext().getRealPath("./RecipeFile/"); //写真の保存パスを所得
		String URL = "/writedrecipe.jsp";
		String recipe_id = null;
		String discriptionData = null;
		StringBuffer ERROR = new StringBuffer();
		Integer count = 0;

		try { //各種エラーの処理とソースの解放

			//文字コードの設定
			rq.setCharacterEncoding("UTF-8");
			rs.setCharacterEncoding("UTF-8");

			//sesssionからデータを取り出す。
			HttpSession session = rq.getSession();
			String user_id = (String) session.getAttribute("user_id");

			//フォームから各種データを受け取る
			String recipe_title = rq.getParameter("recipe_title"); //レシピタイトル
			String recipe_material = rq.getParameter("recipe_material"); //素材
			String recipe_introductory_essay = rq.getParameter("recipe_introductory_essay"); //レシピ紹介文
			String recipe_point = rq.getParameter("recipe_point");
			String recipe_why = rq.getParameter("recipe_why");

			//データを全て入力しているかを判別する。
			if (recipe_title.isEmpty() || recipe_material.isEmpty() || recipe_introductory_essay.isEmpty() || recipe_why.isEmpty() || recipe_point.isEmpty()) {
				//全てのデータを入力していなかった場合
				rq.setAttribute("ERROR", "フォームを全て入力してください");
				RequestDispatcher dispatcher = rq.getRequestDispatcher("./writerecipe.jsp");
				dispatcher.forward(rq, rs);
			} else { //全てのデータを入力していた場合

				//すでに同一の名前のレシピを登録していないかを判別する。
				if (SerchRecipe.Serch(user_id, recipe_title) != null) {
					//同一名のレシピが存在した場合

					//投稿完了ページにフォワードする
					rq.setAttribute("ERROR", "同名のレシピは投稿できません");
					RequestDispatcher dispatcher = rq.getRequestDispatcher("./writerecipe.jsp");
					dispatcher.forward(rq, rs);

				} else { //同一名のレシピが存在しない場合

					Integer imageNum = SerchUpLoadImageNum.ReturnNum(rq); //説明用画像のアップロード数を調べる
					
					//レシピをデータベースに登録する。
					InsertRecipe.SetIntroductory(recipe_introductory_essay); //説明文
					InsertRecipe.Setmaterial(recipe_material); //素材
					InsertRecipe.SetRecipeTitle(recipe_title); //登録レシピタイトル
					InsertRecipe.SetUserId(user_id); //登録ユーザID
					InsertRecipe.SetPoint(recipe_point);	//レシピのポイント
					InsertRecipe.SetWhy(recipe_why);		//なぜこのレシピをつくったか
					InsertRecipe.SetImage(imageNum);	//登録する写真数
					InsertRecipe.insertData(); //実行

					//RecipeIDの取得
					recipe_id = GetRecipeID.Get(user_id, recipe_title);

					//レシピIDを元に写真保存用のファイルを作成する。
					DirOperation.CreateDir(saveImagePath + recipe_id);
					
					//写真と説明文をファイルとして保存する
					ImageFileUpload.UpLoadTop(rq, saveImagePath + recipe_id); //トップ画像
						for(Integer i = 1; i <= 15; i++) {		//説明用の写真があれば全て所定のファイルに保存する。
							discriptionData = rq.getParameter("inputRecipeDescriptionText_" + i);
							if(!discriptionData.isEmpty()) {
								
								count = count+1;
								
								if(imageNum >= 15) {
									ImageFileUpload.UpLoad(rq, "inputRecipeDescriptionImage_" + i.toString() , saveImagePath + recipe_id);
								}else {
									Files.copy( Paths.get( getServletContext().getRealPath("./allSource/image/")  + "NoImage.jpg") , Paths.get(saveImagePath + recipe_id + "/inputRecipeDescriptionImage_" + i + ".jpg") );
								}
								
								WriteFile.Write(discriptionData,  saveImagePath + recipe_id + "/" + "inputRecipeDescriptionText_" + i.toString() + ".txt");
								
							}
						}
						
						UpDateRecipeImageNum.upDateImageNum(count , recipe_id);
					
					/*
					//説明文作成
					if (!discriptionData.isEmpty()) {	//万が一写真だけあって説明がない場合
						//入力されていた場合
						MakeRecipeFile.Write(discriptionData,  saveImagePath + recipe_id + "/" + "inputRecipeDescriptionText_" + i.toString() + ".txt");
					} else {
						//全てのデータを入力していなかった場合
						DleteRecipe.Dlete(recipe_id);		//一度レシピを削除
						rq.setAttribute("ERROR", "フォームを全て入力してください");
						RequestDispatcher dispatcher = rq.getRequestDispatcher("./writerecipe.jsp");
						dispatcher.forward(rq, rs);
					}
					*/

					//投稿完了ページにフォワードする
					RequestDispatcher dispatcher = rq.getRequestDispatcher(URL);
					dispatcher.forward(rq, rs);
				}
			}

		} catch (

		ServletException e) {
			ERROR.append(e);
		} catch (IOException e) {
			ERROR.append(e);
		} finally { //エラーをコンソールに排出する。
			System.out.print(ERROR.toString());
		}

	}

	public void doGet(HttpServletRequest rq, HttpServletResponse rs) {
		doPost(rq, rs);
	}
}