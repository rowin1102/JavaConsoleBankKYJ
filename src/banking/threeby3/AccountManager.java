package banking.threeby3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class AccountManager {
	
	private HashSet<Account> myAccount;
	private static final String SAVE_PATH = "src/banking/AccountInfo.obj";
	private AutoSaver autoSaver;
	
	public AccountManager(int num) {
		myAccount = new HashSet<Account>();
		loadAccountData();
	}
	
	public void makeAccount(int choice) {
		String mkAccount, mkName, mkRating;
		int mkMoney, mkInterest;
		
		System.out.print("계좌번호:"); mkAccount = BankingSystemMain.scan.nextLine();
		System.out.print("고객이름:"); mkName = BankingSystemMain.scan.nextLine();
		System.out.print("잔고:"); mkMoney = BankingSystemMain.scan.nextInt();
		System.out.print("기본이자%(정수형태로입력):"); mkInterest = BankingSystemMain.scan.nextInt();
		BankingSystemMain.scan.nextLine();
		
		Account acc = null;
		
		if(choice == 1) {
			acc = new NormalAccount(mkAccount, mkName, mkMoney, mkInterest);
		} else if(choice == 2) {
			System.out.print("신용등급(A,B,C):"); 
			mkRating = BankingSystemMain.scan.nextLine();
			acc = new HighCreditAccount(mkAccount, mkName, mkMoney, mkInterest, mkRating);
		} else if(choice == 3) {
			acc = new SpecialAccount(mkAccount, mkName, mkMoney, mkInterest);
		}
		
		if(myAccount.contains(acc)) {
			System.out.println("중복계좌가 발견됐습니다. 덮어쓸까요?(Y or N)");
			String same = BankingSystemMain.scan.nextLine();
			
			if(same.toUpperCase().equals("Y")) {
				myAccount.remove(acc);
				myAccount.add(acc);
				System.out.println("계좌정보를 덮어썼습니다.");
			} else {
				System.out.println("기존 계좌정보를 유지합니다.");
			}
		} else {
			myAccount.add(acc);
			System.out.println("계좌계설이 완료되었습니다.");
			saveAcountData();
		}
		
	}
	
	public void depositMoney() {
		BankingSystemMain.scan.nextLine();
		
		String deAccount; 
		int deMoney = 0;
		
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:"); 
		deAccount = BankingSystemMain.scan.nextLine();
		
		boolean found = false;
		for(Account ac : myAccount) {
			if(deAccount.equals(ac.getAccount())) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			System.out.println("해당 계좌를 찾을 수 없습니다.");
			return;
		}
		
		System.out.print("입금액:"); 
		String tempMoney = BankingSystemMain.scan.nextLine();
		
		try {
			deMoney = Integer.parseInt(tempMoney);
			
			if(deMoney < 0) {
				System.out.println("음수는 입금할 수 없습니다.");
				return;
			}
			if(deMoney % 500 != 0) {
				System.out.println("입금액은 500원단위로 가능합니다.");
				return;
			}
		}
		catch (NumberFormatException e) {
			System.out.println("정수만 입력할 수 있습니다.");
			return;
		}
		
		for(Account ac : myAccount) {
			if(deAccount.equals(ac.getAccount())) {	
				ac.deposit(deMoney);
				System.out.println("입금이 완료되었습니다.");
				break;
			}
		}
		
	}
	
	public void withdrawMoney() {
		BankingSystemMain.scan.nextLine();
		
		String wiAccount;
		int wiMoney;
		
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		wiAccount = BankingSystemMain.scan.nextLine();
		
		boolean found = false;
		for(Account ac : myAccount) {
			if(wiAccount.equals(ac.getAccount())) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			System.out.println("해당 계좌를 찾을 수 없습니다.");
			return;
		}
		
		System.out.print("출금액:");
		String tempMoney = BankingSystemMain.scan.nextLine();
		
		try {
			wiMoney = Integer.parseInt(tempMoney);
			
			if(wiMoney < 0) {
				System.out.println("음수는 입금할 수 없습니다.");
				return;
			}
			if(wiMoney % 1000 != 0) {
				System.out.println("출금액은 1000원단위로 가능합니다.");
				return;
			}
		}
		catch (NumberFormatException e) {
			System.out.println("정수만 입력할 수 있습니다.");
			return;
		}
		
		for(Account ac : myAccount) {
			if(wiAccount.equals(ac.getAccount())) {
				if(wiMoney > ac.getMoney()) {
					System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
					System.out.println("Y:금액전체 출금, N:출금요청취소");
					System.out.print("선택:");
					String choice = BankingSystemMain.scan.nextLine();
					
					if(choice.toUpperCase().equals("Y")) {
						System.out.println("금액 전체를 출금합니다.");
						ac.setMoney(0);
					} else if(choice.toUpperCase().equals("N")) {
						System.out.println("출금요청을 취소합니다.");
						return;
					}
					
				} else {
					ac.setMoney(ac.getMoney() - wiMoney);
					System.out.println("출금이 완료되었습니다.");
					break;
				}
				
			}
		}
		
	}
	
	public void showAccInfo() {
		
		if(myAccount.isEmpty()) {
			System.out.println("출력할 계좌가 없습니다.");
			return;
		}
		
		for(Account ac : myAccount) {
			System.out.println("-----------------");
			ac.showAccInfo();
			System.out.println("-----------------");
		}
		
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		
	}
	
	public void deleteAccount() {
		BankingSystemMain.scan.nextLine();
		
		System.out.print("삭제할 계좌번호: ");
		String delete = BankingSystemMain.scan.nextLine();
		
		Account dummy = new NormalAccount(delete, "", 0, 0);
		
		if(myAccount.remove(dummy)) {
			System.out.println("계좌가 삭제되었습니다.");
		} else {
			System.out.println("해당계좌를 찾을 수 없습니다.");
		}
		
	}
	
	public void saveAcountData() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))){
			out.writeObject(myAccount);
			System.out.println("계좌 정보가 저장되었습니다.");
		} catch (IOException e) {
			System.out.println("계좌 저장 중 오류 발생: " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadAccountData() {
		File file = new File(SAVE_PATH);
		if(!file.exists()) {
			System.out.println("AccountInfo.obj파일이 없습니다.");
			return;
		}
		
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
			myAccount = (HashSet<Account>) in.readObject();
			System.out.println("계좌 정보를 불러왔습니다.");
		} catch (Exception e) {
			System.out.println("계좌 불러오기 중 오류 발생: " + e.getMessage());
		}
	}
	
	public void saveOption() {
		
		System.out.println("1.자동저장On, 2.자동저장off");
		System.out.print("선택:");
		
		while(true) {
			
			BankingSystemMain.scan.nextLine();
			int choice = Integer.parseInt(BankingSystemMain.scan.nextLine());
			
			switch(choice) {
			case 1:
				if(autoSaver != null && autoSaver.isAlive()) {
					System.out.println("이미 자동저장이 실행중입니다.");
				} else {
					autoSaver = new AutoSaver(myAccount);
					autoSaver.setDaemon(true);
					autoSaver.start();
					System.out.println("자동저장이 시작되었습니다.");
					return;
				}
				break;
			case 2:
				if(autoSaver != null && autoSaver.isAlive()) {
					autoSaver.interrupt();
					System.out.println("자동저장이 종료됩니다.");
					return;
				} else {
					System.out.println("자동저장이 실행중이 아닙니다.");
					return;
				}
			default:
					System.out.println("잘못된 선택입니다.");
			}
		}
		
	}
	
}
