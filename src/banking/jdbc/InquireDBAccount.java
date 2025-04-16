package banking.jdbc;

import java.sql.SQLException;

public class InquireDBAccount extends DBMyConnection {
	
	public InquireDBAccount() {
		super("education", "1234");
	}
	
	String query;
	
	@Override
	public void dbExecute() {
		
		try {
			
			stmt = con.createStatement();
			query = "SELECT * FROM banking ORDER BY a_code asc";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				String account = rs.getString("account");
				String name = rs.getString("name");
				int money = rs.getInt("money");
				int interest = rs.getInt("interest");
				
				System.out.printf("계좌번호:%s, 이름:%s, 잔액:%d원, 이자율:%d%% \n", 
						account, name, money, interest);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbClose();
		}
		
	}
	
}
