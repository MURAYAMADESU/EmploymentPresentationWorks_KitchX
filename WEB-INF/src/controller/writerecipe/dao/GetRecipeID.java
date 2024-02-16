package controller.writerecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import communal.dao.DatabaseConnectionManager;

public class GetRecipeID{
	
	/*
	 * 引数に渡されたUserIDとレシピ名をを元に様々なデータを得る
	 * @param String ユーザID
	 * @param String レシピ名
	 */
	public static String Get(String user_id, String recipe_title){

		//各種変数の宣言
		String SQL =  "select recipe_id from t_recipe where user_id = ? and recipe_title = ?"; //実行SQLの格納用
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		String recipe_id = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//データベース検索
		try {
			
			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得
			ps = con.prepareStatement(SQL); //SQL文のセット
			ps.setString(1, user_id);
			ps.setString(2, recipe_title);

			rs = ps.executeQuery(); //SQLの実行

			rs.next(); //カーソルを移動させる
			
			recipe_id = rs.getString("recipe_id");

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
			
			System.out.print(ERROR.toString());
		}
		
		return recipe_id;
		
	}
	
}
