package communal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIdTranslation{
	
	//各種変数を宣言する
	private static String name = null;
	private static String mail = null;
	private static String phoneNumber = null;
	
	/*
	 * 引数に渡されたUserIDを元に様々なデータを得る
	 * @param UserID
	 */
	public static void Translation(String user_ID){

		//各種変数の宣言
		String SQL =  "select user_name, mail, phone_number from t_user where user_id = ?"; //実行SQLの格納用
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//データベース検索
		try {
			
			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			ps = con.prepareStatement(SQL); //SQL文のセット
			ps.setString(1, user_ID);

			rs = ps.executeQuery(); //SQLの実行

			rs.next(); //カーソルを移動させる
			
			name = rs.getString("user_name");
			mail = rs.getString("mail");
			phoneNumber = rs.getString("phone_number");

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
		
	}

	/*
	 * UserIDを元に得たユーザ名を返すメゾット
	 * @return String ユーザ名を返す
	 */
	public static String GetUserName(){
		return name;
	}
	
	/*
	 * UserIDを元に得たメールを返すメゾット
	 * @return String メールを返す
	 */
	public static String GetUserMail() {
		return mail;
	}
	
	/*
	 * UserIDを元に得た電話番号を返すメゾット
	 * @return String 電話番号を返す
	 */
	public static String GetPhoneNumber() {
		return phoneNumber;
	}
	
}
