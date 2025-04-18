package banking;

/*
	프로그램에서 사용할 상수를 정의한 인터페이스.
	메뉴 선택 번호 및 신용등급 계수를 정의
 */

public interface ICustomDefine {
	
	// 메뉴 번호 상수 정의
	int MAKE = 1;
	int DEPOSIT = 2; 
	int WITHDRAW = 3; 
	int INQUIRE = 4;
	int DELETE = 5;
	int SAVE = 6;
	int EXIT = 7;
	
	// 신용등급 계수 상수 정의
	int RATE_A = 7;
    int RATE_B = 4;
    int RATE_C = 2;
    
    String GRADE_A = "A";
    String GRADE_B = "B";
    String GRADE_C = "C";
	
}
