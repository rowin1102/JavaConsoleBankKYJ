package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설");
		System.out.println("2.입 금");
		System.out.println("3.출 금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.계좌정보삭제");
		System.out.println("6.프로그램종료");
		System.out.print("선택:");
	}
	
	public static void main(String[] args) {
		
		AccountManager bankingsystem = new AccountManager(50);
		
		while(true){
			
			try {
				int choice = 0;
				
				while(true) {
					showMenu();
					if(scan.hasNextInt()) {
						choice = scan.nextInt();
						if(choice >= 1 && choice <= 8) {
							break;
						} else {
							throw new MenuSelectException("1~7사이의 정수를 입력하세요.");
						}	
					} else {
					System.out.println("숫자만 입력 가능합니다.");
					scan.nextLine();
					}
				}
				switch(choice) {
				case MAKE:
					int subchoice = 0;
					while(true) {
						System.out.println("1.보통계좌 \n2.신용계좌");
						System.out.print("선택:");
						subchoice = scan.nextInt();
						scan.nextLine();
						if(subchoice == 1 || subchoice == 2) {
							break;
						}
						System.out.println("잘못된 입력입니다.");
					}
					switch(subchoice) {
					case 1:
						bankingsystem.makeAccount(subchoice);
						break;
					case 2:
						bankingsystem.makeAccount(subchoice);
						break;
					}
					break;
				case DEPOSIT:
					bankingsystem.depositMoney();
					break;
				case WITHDRAW:
					bankingsystem.withdrawMoney();
					break;
				case INQUIRE:
					bankingsystem.showAccInfo();
					break;
				case DELETE:
					bankingsystem.deleteAccount();
					break;
				case EXIT:
					System.out.println("프로그램 종료");
					return;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 입력하세요.");
				scan.nextLine();
			}
			catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			catch (Exception e) {
				System.out.println("예상치 못한 오류 발생: " + e.getMessage());
			}
			
		}
		
	}
	
}
