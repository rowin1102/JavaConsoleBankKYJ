package banking;

import java.io.Serializable;

/*
 	계좌에 기본 이자율을 적용하며, 입금 시 이자도 함께 계산되어 잔고에 추가.
*/

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
	
	// 기본 계좌 정보를 출력하고, 기본 이자율을 추가로 출력.
	@Override
	public String toString() {
		return super.toString() + ", 기본이자율=" + getInterest();
	}
	
	private static final long serialVersionUID = 1L;
	
}
