### 클래스 부분 (워크북 p47_05번)

```python
public class P47_Exam05 {
	
	private String account;
	private int balance;
	private double interestRate;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public double calculateInterest() {
		return balance*interestRate/100;
	}
	public void deposit(int money) {
		balance=balance+money;
	}
	
	public void withdraw(int money) {
		balance=balance-money;
	}
	
```



### 메인부분

package workbook;

public class P47_Exam05_Test {

	public static void main(String[] args) {
		P47_Exam05 account=new P47_Exam05();
		account.setAccount("441-0290-1203");
		account.setBalance(500000);
		account.setInterestRate(7.3);
		System.out.println("계좌정보: "+account.getAccount()+"계좌잔액"+account.getBalance());
		System.out.println("이자: "+account.calculateInterest());
		
		account.deposit(20000);
		System.out.println("계좌정보: "+account.getAccount()+"계좌잔액"+account.getBalance());
		System.out.println("이자: "+account.calculateInterest());


​		