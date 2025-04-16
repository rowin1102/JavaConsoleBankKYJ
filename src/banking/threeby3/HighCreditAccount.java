package banking.threeby3;

import java.io.Serializable;

public class HighCreditAccount extends Account implements Serializable{
	
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
		case "A": extra = 7; break;
		case "B": extra = 4; break;
		case "C": extra = 2; break;
		}
		
		int interestAmount = getMoney() * getInterest() / 100;
		int extraInterest = getMoney() * extra / 100;
		setMoney(getMoney() + interestAmount + extraInterest + amount);
	}
	
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
