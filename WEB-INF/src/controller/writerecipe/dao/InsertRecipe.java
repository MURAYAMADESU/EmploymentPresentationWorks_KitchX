package controller.writerecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import communal.dao.DatabaseConnectionManager;

public class InsertRecipe {

	public InsertRecipe() {
		// コンストラクター
	}
	
	static Integer recipe_image;
	static String recipe_title;
	static String recipe_introductory;
	static String recipe_material;
	static String recipe_why;
	static String recipe_point;
	static String user_id;
	
	//データのセット
	static public void SetRecipeTitle(String title) {
		recipe_title = title;
	}
	
	static public void SetIntroductory(String introductory) {
		recipe_introductory = introductory;
	}
	
	static public void Setmaterial(String material) {
		recipe_material = material;
	}
	
	static public void SetWhy(String why) {
		recipe_why = why;
	}
	
	static public void SetPoint(String point) {
		recipe_point = point;
	}
	
	static public void SetImage(Integer image) {
		recipe_image = image;
	}
	
	static public void SetUserId(String id) {
		user_id = id;
	}
	
	/**
	 * セットされたデータを元にSQL分を実行するためのメゾット
	 * @return 全て処理を完了させた場合 null そうでない場合エラーコードを返す。
	 */
	public static void insertData() {
		//各種変数の宣言
		Connection con = null;
		PreparedStatement pre = null;
		StringBuffer ERROR = new StringBuffer();
		String SQL = "INSERT INTO t_recipe (user_id, recipe_title, recipe_introductory_essay, recipe_material, recipe_point, recipe_why, recipe_image, recipe_trash_key) VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
		
		//全ての変数に値が入っているかチェックする。
		try {
			if(recipe_title == null || user_id == null || recipe_introductory == null || recipe_material == null) {
				throw new IllegalAccessException("No value entered");
			}
		} catch (IllegalAccessException e) {
			ERROR.append(e);
		}
		
		//SQL文の実行
		try {
			
			//実行準備
			con = DatabaseConnectionManager.coonnection();
			pre = con.prepareStatement(SQL);
			
			//SQL文にValueを追加する。
			pre.setString(1, user_id);
			pre.setString(2, recipe_title);
			pre.setString(3, recipe_introductory);
			pre.setString(4, recipe_material);
			pre.setString(5, recipe_point);
			pre.setString(6, recipe_why);
			pre.setInt(7, recipe_image);
			
			//SQLの実行
			pre.executeUpdate();
			
			//エラーの受け取りと実行
		} catch (SQLException e) {
			ERROR.append(e);
		} catch (Exception e) {
			ERROR.append(e);
		}finally {
			//各種オブジェクトのクローズ(片付け)
			try {
				if (pre != null) pre.close();
				if (con != null) con.close();
			}catch (SQLException e) {
				ERROR.append(e);
			}
			
			//実行結果の出力
			if(ERROR != null) {
				System.out.print(ERROR.toString());
			}
			
		}
		
	}

}
