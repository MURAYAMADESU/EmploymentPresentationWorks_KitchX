package controller.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import communal.dao.DatabaseConnectionManager;

public class SearchMail{
	/*
	 * 引数に渡されたメールを検索しすでに同一のものが登録されていないか確認する。
	 * @param mail メール
	 * @return 同一のものがあれば"userID"なければ"null"
	 */
	public static String search(String mail){

		//各種変数の宣言
		String SQL = "select user_id from t_user where mail = ?"; //実行SQLの格納用
		String result = null;
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//引数にデータが入っているかの確認
		try {
			if (mail == null) {
				throw new IllegalAccessException();
			}
		} catch (IllegalAccessException e) {
			ERROR.append(e);
		}

		//データベース検索
		try {
			
			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			ps = con.prepareStatement(SQL); //SQL文のセット
			ps.setString(1, mail);

			rs = ps.executeQuery(); //SQLの実行

			rs.next(); //カーソルを移動させる
			if(rs.getString("user_id") != null) result = rs.getString("user_id");

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
		}

		//データの送信、必要であれエラーをコンソールに出力する。
		//System.out.print(ERROR);
		
		return result;
		
	}

}
