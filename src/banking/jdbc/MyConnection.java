package banking.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyConnection implements IConnect {
	
	public Connection con;
	public ResultSet rs;
	public Statement stmt;
	public PreparedStatement psmt;
	public CallableStatement csmt;
	
	public MyConnection(String user, String pass) {
		try {
			Class.forName(ORACLE_DRIVER);
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
		}
		catch (Exception e) {
			System.out.println("DB 커넥션 예외발생");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void dbClose() {
		try {
			if(con != null) con.close();
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(csmt != null) csmt.close();
			System.out.println("DB 자원 반납");
		}
		catch (Exception e) {
			System.out.println("DB 자원 반납시 예외발생");
			e.printStackTrace();
		}
	}
	
	
}
