package banking.jdbc;

public interface DBIConnect {
	
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	
	void dbExecute();
	void dbClose();
	String inputValue(String title);
}
