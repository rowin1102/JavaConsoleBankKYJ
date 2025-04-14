package banking;

public class HighCreditAccount extends Account {
	
	int interest;
	String creditRating;
	
	public HighCreditAccount(String account, String name, int money, int interest, String creditRating) {
		super(account, name, money);
		this.interest = interest;
		this.creditRating = creditRating;
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("신용등급>" + creditRating.toUpperCase());
	}
	
}
