package communal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteRecipeId {

	public DeleteRecipeId() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static String Get(String recipe_id) {

		//各種変数の宣言
		String SQL = "select recipe_id from t_recipe where recipe_id = ? "; //実行SQLの格納用
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		String result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//データベース検索
		try {

			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			ps = con.prepareStatement(SQL); //SQL文のセット
			ps.setString(1, recipe_id);

			rs = ps.executeQuery(); //SQLの実行

			rs.next(); //カーソルを移動させる
			
			result = rs.getString("recipe_id");
			
			System.out.println(result);


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
