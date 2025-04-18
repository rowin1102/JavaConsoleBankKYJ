package banking;

import java.io.Serializable;
import java.util.Objects;

/*
 	은행 계좌의 기본 정보를 담는 추상 클래스.
 	공통 필드와 메서드를 정의, 자식 클래스에서 상속을 받아 사용.
*/

public abstract class Account implements Serializable {
	
	private String account;
	private String name;
	private int money;
	
	public Account(String account, String name, int money) {
		this.account = account;
		this.name = name;
		this.money = money;
	}
	
	public String getAccount() {
		return account;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMoney() {
		return money;
	}
	
	// 잔고 설정
	public void setMoney(int money) {
		this.money = money;
	}
	
	// 입금 처리 메서드
	public void deposit(int amount) {
		this.money += amount;
	}
	
	// 계좌정보 출력
	public void showAccInfo() {
		System.out.println("계좌번호>" + account);
		System.out.println("고객이름>" + name);
		System.out.println("잔고>" + money);
	}
	
	// 계좌번호를 기준으로 해시 값을 생성, 동일한 계좌번호는 동일한 해시 값을 가짐.
	@Override
	public int hashCode() {
		return Objects.hash(this.account);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		// 동일 객체 비교 (같은 메모리 주소면 true)
		if (this == obj) return true;
		// null이거나, Account 타입이 아니면 false
		if (obj == null || !(obj instanceof Account)) return false;
		
		// 형변환 후 계좌번호 기준으로 같은 계좌인지 비교.
	    Account other = (Account) obj;
	    return this.account.equals(other.account);
	}
	
	// 객체 정보를 문자열로 표현
	@Override
	public String toString() {
		return String.format("[계좌]계좌번호=%s, 이름=%s, 잔고=%d", account, name, money);
	}
	
	// 직렬화 UID (객체 저장/불러오기 위한 설정)
	private static final long serialVersionUID = 1L;
	
}
