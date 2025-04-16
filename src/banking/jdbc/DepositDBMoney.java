package banking.jdbc;

import java.sql.SQLException;

public class DepositDBMoney extends DBMyConnection {
	
	public DepositDBMoney() {
		super("education", "1234");
	}
	
	String query;
	int result;
	
	@Override
	public void dbExecute() {
		
		try {
			
			String account = inputValue("입금할 계좌");
			int depositAmount = Integer.parseInt(inputValue("입금할 금액"));
			
			if(depositAmount < 0) {
				System.out.println("음수는 입력할 수 없습니다.");
				return;
			}
			
			query = "SELECT money, interest FROM banking "
					+ "WHERE account=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, account);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				int currentMoney = rs.getInt("money");
				int interest = rs.getInt("interest");
				
				int bonus = (currentMoney * interest) / 100;
				int newMoney = currentMoney + bonus + depositAmount;
				
				query = "UPDATE banking SET money=? WHERE account=?";
				psmt = con.prepareStatement(query);
				psmt.setInt(1, newMoney);
				psmt.setString(2, account);
				
				result = psmt.executeUpdate();
	            System.out.println(result + "개의 계좌에 입금되었습니다.");
	            System.out.println("기존 잔액: " + currentMoney);
	            System.out.println("기본 이자(" + interest + "%): " + bonus);
	            System.out.println("입금액: " + depositAmount);
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
