package banking.jdbc;

import java.sql.SQLException;
import java.sql.Types;

public class DeleteDBAccount extends DBMyConnection {
	
	public DeleteDBAccount() {
		super("education", "1234");
	}	
	
	@Override
	public void dbExecute() {
		
		try {
			
			csmt = con.prepareCall("{ call  DeleteAccount(?, ?)}");
			csmt.setString(1, inputValue("삭제할 계좌번호"));
			csmt.registerOutParameter(2, Types.NUMERIC);
			csmt.execute();
			
			int result = csmt.getInt(2);
			if(result == 1) {
				System.out.println("계좌를 삭제했습니다.");
			} else {
				System.out.println("삭제할 계좌가 없습니다.");
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
