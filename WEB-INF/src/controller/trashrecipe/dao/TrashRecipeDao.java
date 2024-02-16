package controller.trashrecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import communal.dao.DatabaseConnectionManager;

public class TrashRecipeDao {

	public TrashRecipeDao() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void Do(String user_id, String recipe_id) {
		//各種変数の宣言
		Connection con = null;
		PreparedStatement pre = null;
		StringBuffer ERROR = new StringBuffer();
		String SQL = "DELETE FROM t_recipe WHERE user_id = ? and recipe_id = ?";
		
		//SQL文の実行
		try {
			
			//実行準備
			con = DatabaseConnectionManager.coonnection();
			pre = con.prepareStatement(SQL);
			
			//SQL文にValueを追加する。
			pre.setString(1,user_id );
			pre.setString(2,recipe_id);
			
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
			
			System.out.print(ERROR.toString());
		}
	}
	
}
