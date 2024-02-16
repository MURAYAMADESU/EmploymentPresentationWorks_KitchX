package controller.myrecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import communal.dao.DatabaseConnectionManager;

public class MyAllRecipe {

	public MyAllRecipe() {
		// コンストラクター
	}

	/*
	 * 引数に渡されたUserIDとを元に投稿しているレシピを確認する。
	 * @param String ユーザID
	 * @return String 該当ユーザのレシピIDをCSV形式で返す
	 */
	public static String Get(String user_id){

		//各種変数の宣言
		String SQL =  "select recipe_id from t_recipe where user_id = ?"; //実行SQLの格納用
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		StringBuffer MyAllRecipeId = new StringBuffer();		//データを返すための入れ物
		String result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//データベース検索
		try {
			
			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得
			ps = con.prepareStatement(SQL); //SQL文のセット
			ps.setString(1, user_id);

			rs = ps.executeQuery(); //SQLの実行
			
			while (rs.next()) {
				MyAllRecipeId.append(rs.getString("recipe_id") + ",");		//取り出したIDをCSVに出力
			}
			
			//CSVに出力したデータから余分な文字列を取り出す。
			result = MyAllRecipeId.toString();
			if (!(result.length() < 0)) {			//エラー対策にデータがあるか確認する
				//あった場合
				result = result.substring(0, result.length()-1);
			}

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
		
		return result;
	}

}
