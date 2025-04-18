package banking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

/*
 	계좌 정보를 자동으로 텍스트 파일에 저장하는 쓰레드 클래스.
 	5초마다 계좌 정보를 텍스트 파일에 저장, 자동 저장을 관리.
*/

public class AutoSaver extends Thread {
	
	private final HashSet<Account> accountSet;
	private static final String SAVE_TXT = "src/banking/AutoSaveAccount.txt";
	
	public AutoSaver(HashSet<Account> accountSet) {
		this.accountSet = accountSet;
	}
	
	/*
	 	쓰레드 실행 메서드
	 	interrupt 시 자동 저장을 종료
	*/
	@Override
	public void run() {
		
		while(true) {
			
			try {
				saveTextFile(); // 계좌 정보를 파일에 저장
				System.out.println("계좌 정보가 텍스트로 저장됩니다.");
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
	
	/*
	 	계좌 정보를 텍스트 파일에 저장하는 메서드.
	 	각 계좌의 정보를 파일에 출력.
	*/
	private void saveTextFile() throws IOException{
		
		// PrintWriter로 텍스트 파일에 계좌 정보를 저장
		try(PrintWriter writer = new PrintWriter(new FileWriter(SAVE_TXT))) {
			for(Account ac : accountSet) {
				writer.println(ac.toString()); // 계좌 정보를 문자열로 파일에 기록
			}
		}
		catch (IOException e) {
			System.out.println("IO오류");
		}
	}	
	
}
