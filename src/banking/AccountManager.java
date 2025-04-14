package banking;

import java.util.HashSet;

public class AccountManager {
	
	private HashSet<Account> myAccount;
	
	public AccountManager(int num) {
		myAccount = new HashSet<Account>();
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
		}
		
		if(myAccount.contains(acc)) {
			System.out.println("중복계좌가 발견됐습니다. 덮어쓸까요?(Y or N)");
			String same = BankingSystemMain.scan.nextLine();
			
			if(same.toUpperCase().equals("Y")) {
				myAccount.remove(acc);
				myAccount.add(acc);
				System.out.println("계좌정보를 덮어썼습니다.");
			} else {
				System.out.println();
			}
		}
		
		System.out.println("계좌계설이 완료되었습니다.");
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
			if(deAccount.equals(ac.account)) {
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
			if(deAccount.equals(ac.account)) {
				int basicInterestRate = 0;
				int extraInterestRate = 0;
				
				if(ac instanceof HighCreditAccount) {
					HighCreditAccount high = (HighCreditAccount)ac;
					basicInterestRate = high.interest;
					
					switch(high.creditRating.toUpperCase()) {
					case "A": extraInterestRate = 7; break;
					case "B": extraInterestRate = 4; break;
					case "C": extraInterestRate = 2; break;
					}
				} else if(ac instanceof NormalAccount) {
					NormalAccount normal = (NormalAccount)ac;
					basicInterestRate = normal.interest;
				}
				
				int basicInterest = ac.money * basicInterestRate / 100;
				int extraInterest = ac.money * extraInterestRate / 100;
				
				ac.money = ac.money + basicInterest + extraInterest + deMoney;
				
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
			if(wiAccount.equals(ac.account)) {
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
			if(wiAccount.equals(ac.account)) {
				if(wiMoney > ac.money) {
					System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
					System.out.println("Y:금액전체 출금, N:출금요청취소");
					System.out.print("선택:");
					String choice = BankingSystemMain.scan.nextLine();
					if(choice.toUpperCase().equals("Y")) {
						System.out.println("금액 전체를 출금합니다.");
						ac.money = 0;
					} else if(choice.toUpperCase().equals("N")) {
						System.out.println("출금요청을 취소합니다.");
						return;
					}
				} else {
					ac.money -= wiMoney;
					System.out.println("출금이 완료되었습니다.");
					break;
				}
				
			}
		}
		
	}
	
	public void showAccInfo() {
		
		for(Account ac : myAccount) {
			System.out.println("-----------------");
			ac.showAccInfo();
			System.out.println("-----------------");
		}
		
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		
	}
	
	public void deleteAccount() {
		
	}
	
}
