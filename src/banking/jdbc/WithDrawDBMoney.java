package banking.jdbc;

import java.sql.SQLException;

public class WithDrawDBMoney extends DBMyConnection {
	
	public WithDrawDBMoney() {
		super("education", "1234");
	}
	
	String query;
	int result;
	
	@Override
	public void dbExecute() {
		
		try {
			
			String account = inputValue("출금할 계좌");
			int withdrawAmount = Integer.parseInt(inputValue("출금할 금액"));
			
			if(withdrawAmount < 0) {
				System.out.println("음수는 입력할 수 없습니다.");
				return;
			}
			
			query = "SELECT money, interest FROM banking "
					+ "WHERE account=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, account);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				
				int currentMoney = rs.getInt("money");
				int newMoney = currentMoney - withdrawAmount;
				
				if(withdrawAmount > currentMoney) {
					System.out.println("보유하고 있는 금액보다 높은 금액은 출금할 수 없습니다.");
					System.out.println("보유금액: " + currentMoney);
					return;
				}
				
				query = "UPDATE banking SET money=? WHERE account=?";
				psmt = con.prepareStatement(query);
				psmt.setInt(1, newMoney);
				psmt.setString(2, account);
				
				result = psmt.executeUpdate();
				System.out.println(result + "개의 계좌에서 출금되었습니다.");
				System.out.println("기존 잔액: " + currentMoney);
				System.out.println("출금액: " + withdrawAmount);
				System.out.println("최종 잔액: " + newMoney);
			} else {
				System.out.println("해당 계좌가 존재하지 않습니다.");
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
