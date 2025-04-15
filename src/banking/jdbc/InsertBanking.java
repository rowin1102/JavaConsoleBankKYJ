package banking.jdbc;

import java.sql.SQLException;

public class InsertBanking extends MyConnection {

	public InsertBanking() {
		super("education", "1234");
	}
	
	String query;
	int result;
	
	public void insertAccount(Account acc) {
		
		try {
			query = "INSERT INTO banking (a_code, account, name, money, interest) VALUES "
					+ "(seq_total_idx.nextval, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, acc.getAccount());
			psmt.setString(2, acc.getName());
			psmt.setInt(3, acc.getMoney());
			psmt.setInt(4, acc.getInterest());
			
			int result = psmt.executeUpdate();
	        if (result > 0) {
	            System.out.println("DB에 계좌 정보 저장 완료");
	        } else {
	            System.out.println("DB 저장 실패");
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
