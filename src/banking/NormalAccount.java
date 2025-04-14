package banking;

public class NormalAccount extends Account {
	
	int interest;
	
	public NormalAccount(String account, String name, int money, int interest) {
		super(account, name, money);
		this.interest = interest;
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자>" + interest + "%");
	}
	
}
