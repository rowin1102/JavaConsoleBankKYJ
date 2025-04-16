package banking.threeby3;

import java.io.Serializable;

public class NormalAccount extends Account implements Serializable {
	
	private int interest;
	
	public NormalAccount(String account, String name, int money, int interest) {
		super(account, name, money);
		this.interest = interest;
	}
	
	public int getInterest() {
		return interest;
	}
	
	@Override
	public void deposit(int amount) {
		int interestAmount = getMoney() * getInterest() / 100;
		setMoney(getMoney() + interestAmount + amount);
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자>" + getInterest() + "%");
	}
	
	@Override
	public String toString() {
		return super.toString() + ", 기본이자율=" + getInterest();
	}
	
	private static final long serialVersionUID = 1L;
	
}
