package communal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMaxAutoIncrement {

	public GetMaxAutoIncrement() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static Integer GetRecipe() {

		//各種変数の宣言
		String SQL = "SELECT MAX(recipe_id) as max FROM t_recipe"; //実行SQLの格納用
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer result = null;

		//データベース検索
		try {

			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			ps = con.prepareStatement(SQL); //SQL文のセット

			rs = ps.executeQuery(); //SQLの実行

			rs.next(); //カーソルを移動させる
			result = rs.getInt("max");

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
