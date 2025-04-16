package banking.jdbc;

import java.sql.SQLException;

public class MakeDBAccount extends DBMyConnection {
	
	public MakeDBAccount() {
		super("education", "1234");
	}
	
	String query;
	int result;
	
	@Override
	public void dbExecute() {
		
		try {
			
			query = "insert into banking (a_code, account, name, money, interest) values "
					+ "(seq_banking_idx.nextval, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, inputValue("계좌번호"));
			psmt.setString(2, inputValue("이름"));
			psmt.setInt(3, Integer.parseInt(inputValue("잔액")));
			psmt.setInt(4, Integer.parseInt(inputValue("이자율")));
			
			result = psmt.executeUpdate();
			System.out.println(result + "개의 계좌가 입력");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbClose();
		}
		
	}
	
}
