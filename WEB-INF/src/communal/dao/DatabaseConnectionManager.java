package communal.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

	//各種接続用データ
	static final String USER= "root";
	static final String PASSWD = "root";
	static final String URL = "jdbc:mysql://localhost/EmploymentWorks_db";
	static final String DRIVER = "com.mysql.jdbc.Driver";
	
	//JDBCの読み込み
	static  {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("DatabaseConnectionManager" + e);
		}
	}
	
	//接続データに値が入ってない場合にエラーを投げるプログラム
	public DatabaseConnectionManager() throws IllegalAccessException {
		if(USER == null || PASSWD == null || URL == null || DRIVER == null) {
			throw new IllegalAccessException();
		}
	}
	
	/*
	 * 当ファイル内に記載されたデータを元にデータベースのコネクションえします。
	 * @return データベースのコネクションを開始ます。
	 */
	public static Connection coonnection() throws SQLException{
		return DriverManager.getConnection(URL, USER, PASSWD);
	}
	
}
