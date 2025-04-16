package banking.threeby3;

public class SpecialAccount extends NormalAccount {
	
	private int cnt = 0;
	
	public SpecialAccount(String account, String name, int money, int interest) {
		super(account, name, money, interest);
	}
	
	public int getCnt() {
		return cnt;
	}
	
	@Override
	public void deposit(int amount) {
		cnt++;
		super.deposit(amount);
		
		if(cnt % 2 == 0) {
			setMoney(getMoney() + 500);
		}
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("입금횟수>" + cnt);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", 입금횟수=" + cnt;
	}
	
	private static final long serialVersionUID = 1L;
	
}
