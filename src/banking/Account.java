package banking;

import java.util.Objects;

public abstract class Account {
	
	String account;
	String name;
	int money;
	
	public Account(String account, String name, int money) {
		this.account = account;
		this.name = name;
		this.money = money;
	}
	
	public void showAccInfo() {
		System.out.println("계좌번호>" + account);
		System.out.println("고객이름>" + name);
		System.out.println("잔고>" + money);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.account);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    
	    Account other = (Account) obj;
	    return this.account.equals(other.account);
	}
}
