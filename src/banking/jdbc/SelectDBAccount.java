package banking.jdbc;

import java.sql.SQLException;

public class SelectDBAccount extends DBMyConnection {
	
	public SelectDBAccount() {
		super("education", "1234");
	}
	
	String query;
	
	@Override
	public void dbExecute() {
		
		try {
			
			boolean found = false;
			
			query = "SELECT * FROM banking WHERE account LIKE ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, "%" + inputValue("찾는 계좌") + "%");
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				String account = rs.getString("account");
				String name = rs.getString("name");
				int money = rs.getInt("money");
				int interest = rs.getInt("interest");
				
				System.out.printf("계좌번호:%s, 이름:%s, 잔액:%d원, 이자율:%d%% \n", 
						account, name, money, interest);
				
				found = true;
			}
			
			if(!found) {
				System.out.println("찾는 계좌가 없습니다.");
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
