package week15;

public class BankAccount {

    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }

    // Method to deposit an amount
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        balance += amount;
    }

    // Method to withdraw an amount
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }
    public void transfer(BankAccount B, double amount) {
	if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Transfer amount cannot be negative");
        }
        this.withdraw(amount);
        B.deposit(amount);
	
	
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }
}
