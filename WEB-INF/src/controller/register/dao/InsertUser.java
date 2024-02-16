package controller.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import communal.dao.DatabaseConnectionManager;

public class InsertUser{
	
	String insertName;
	String insertPass;
	String insertMail;
	String ERROR;
	
	//データのセット
	public void setInsertName(String user) {
		this.insertName = user;
	}
	
	public void setInsertPass(String pass) {
		this.insertPass = pass;
	}
	
	public void setInsertMail(String mail) {
		this.insertMail = mail;
	}
	
	/**
	 * セットされたデータを元にSQL分を実行するためのメゾット
	 * @return 全て処理を完了させた場合 null そうでない場合エラーコードを返す。
	 */
	public String insertData() {
		//各種変数の宣言
		Connection con = null;
		PreparedStatement pre = null;
		StringBuffer ERROR = new StringBuffer();
		String SQL = "INSERT INTO t_user (user_name, user_pass, mail) VALUES (?, ?, ?)";
		
		//全ての変数に値が入っているかチェックする。
		try {
			if(insertName == null || insertPass == null || insertMail == null) {
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
			pre.setString(1,insertName );
			pre.setString(2,insertPass);
			pre.setString(3,insertMail);
			
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
		}
		
		//実行結果の送信
		return ERROR.toString();
		
	}

}
