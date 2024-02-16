package controller.writerecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import communal.dao.DatabaseConnectionManager;

public class SerchRecipe {

	public SerchRecipe() {
		//コンストラクター
	}

	/*
	 * 引数に渡されたユーザIDとレシピ名を検索しすでに同一のものが登録されていないか確認する。
	 * @param String user_id
	 * @param Strign recipe_name
	 * @return 同一のものがあれば"recipe_id"なければ"null"
	 */
	public static String Serch(String user_id, String recipe_name) {
		//各種変数の宣言
		String SQL = "select recipe_id from t_recipe where user_id = ? and recipe_title = ?"; //実行SQLの格納用
		String result = null;
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//引数にデータが入っているかの確認
		try {
			if (user_id == null || recipe_name == null) {
				throw new IllegalAccessException("データが入力されていません");
			}
		} catch (IllegalAccessException e) {
			ERROR.append(e);
		}

		//データベース検索
		try {

			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			ps = con.prepareStatement(SQL); //SQL文のセット
			ps.setString(1, user_id);
			ps.setString(2, recipe_name);

			rs = ps.executeQuery(); //SQLの実行

			rs.next(); //カーソルを移動させる
			
			if (rs.getString("recipe_id") != null) result = rs.getString("recipe_id");

		} catch (SQLException e) {
			ERROR.append(e);
		} catch (Exception e) {
			ERROR.append(e);
		} finally {
			//リソースの解放
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					ps.close();
			} catch (SQLException e) {
				ERROR.append(e);
			}
			//エラーを出力する。
			//System.out.println(ERROR.toString())
		}
		//該当するものがあれば該当recipe_idを返す
		return result;
	}
}
