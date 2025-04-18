package banking;

import java.io.Serializable;

/*
 	기본 이자율과 신용등급에 따른 추가 이자율을 반영하여 입금 시 잔고를 증가.
*/

public class HighCreditAccount extends Account implements Serializable, ICustomDefine {
	
	private int interest;
	private String creditRating;
	
	public HighCreditAccount(String account, String name, int money, int interest, String creditRating) {
		super(account, name, money);
		this.interest = interest;
		this.creditRating = creditRating;
	}
	
	public int getInterest() {
		return interest;
	}
	
	public String getCreditRating() {
		return creditRating;
	}
	
	@Override
	public void deposit(int amount) {
		
		int extra = 0;
		switch(creditRating.toUpperCase()) {
		case GRADE_A: extra = RATE_A; break;
		case GRADE_B: extra = RATE_B; break;
		case GRADE_C: extra = RATE_C; break;
		}
		
		int interestAmount = getMoney() * getInterest() / 100;
		int extraInterest = getMoney() * extra / 100;
		setMoney(getMoney() + interestAmount + extraInterest + amount);
	}
	
	// 기본 계좌 정보를 출력, 기본 이자율과 신용등급을 추가로 출력
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자>" + interest + "%");
		System.out.println("신용등급>" + creditRating.toUpperCase());
	}
	
	@Override
	public String toString() {
		return super.toString() + ", 기본이자율=" + interest + ", 신용등급=" + creditRating.toUpperCase() ;
	}
	
	private static final long serialVersionUID = 1L;
	
}
