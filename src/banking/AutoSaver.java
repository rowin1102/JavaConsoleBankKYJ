package banking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class AutoSaver extends Thread {
	
	private final HashSet<Account> accountSet;
	private static final String SAVE_TXT = "src/banking/AutoSaveAccount.txt";
	
	public AutoSaver(HashSet<Account> accountSet) {
		this.accountSet = accountSet;
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			try {
				saveTextFile();
//				System.out.println("계좌 정보가 텍스트로 저장됩니다.");
				Thread.sleep(5000);
			}
			catch (InterruptedException e) {
				System.out.println("자동저장이 종료되었습니다.");
				return;
			} catch (IOException e) {
				System.out.println("자동저장 중 오류 발생: " + e.getMessage());
			}
		}
	}
	
	private void saveTextFile() throws IOException{
		try(PrintWriter writer = new PrintWriter(new FileWriter(SAVE_TXT))) {
			for(Account ac : accountSet) {
				writer.println(ac.toString());
			}
			writer.flush();
		}
		catch (IOException e) {
			System.out.println("IO오류");
		}
	}	
	
}
