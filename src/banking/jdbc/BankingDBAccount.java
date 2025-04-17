package banking.jdbc;

import java.util.Scanner;

public class BankingDBAccount implements DBICustomDefine {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설");
		System.out.println("2.입 금");
		System.out.println("3.출 금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.지정계좌정보출력");
		System.out.println("6.계좌삭제");
		System.out.println("7.프로그램종료");
		System.out.print("선택:");
	}
	
	public static void main(String[] args) {
		
		while (true) {
	        showMenu();
	        
	        if (scan.hasNextInt()) {
	            int choice = scan.nextInt();
	            scan.nextLine();
	            
	            if (choice >= 1 && choice <= 7) {
	            	
	                switch (choice) {
	                    case MAKE:
	                        new MakeDBAccount().dbExecute();
	                        break;
	                    case DEPOSIT:
	                    	new DepositDBMoney().dbExecute();
	                        break;
	                    case WITHDRAW:
	                    	new WithDrawDBMoney().dbExecute();
	                        break;
	                    case INQUIRE:
	                    	new InquireDBAccount().dbExecute();
	                        break;
	                    case SELECT:
	                    	new SelectDBAccount().dbExecute();
	                    	break;
	                    case DELETE:
	                    	new DeleteDBAccount().dbExecute();
	                        break;
	                    case EXIT:
	                        System.out.println("프로그램 종료");
	                        return;
	                }
	                
	            } else {
	                System.out.println("메뉴는 1~7 사이의 숫자만 입력하세요.");
	            }
	            
	        } else {
	            System.out.println("숫자만 입력 가능합니다.");
	            scan.nextLine();
	        }
	        
	    }
		
	}
	
}
