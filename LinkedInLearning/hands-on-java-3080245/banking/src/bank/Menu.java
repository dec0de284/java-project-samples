package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import Exceptions.AmountException;

public class Menu {

	private Scanner scanner;

	public static void main(String[] args) {
		System.out.println("Welcome to CLI Bank App!");

		Menu menu = new Menu();
		menu.scanner = new Scanner(System.in);

		Customer customer = menu.authenticateUser();

		if (customer != null) {
			Account account = DataSource.getAccount(customer.getAccountId());
			menu.showMenu(customer, account);
		}

		menu.scanner.close();
	}

	private Customer authenticateUser() {
		System.out.println("Username: ");
		String username = scanner.next();

		System.out.println("Password: ");
		String password = scanner.next();

		Customer customer = null;
		try {
			customer = Authenticator.login(username, password);
		} catch (LoginException e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}

	private void showMenu(Customer customer, Account account) {
		int selection = 0;

		while (selection != 4 && customer.isAuthenticated()) {
			System.out.println("1. Deposit\n" + "2. Withdraw\n" + "3. Check Balance\n" + "4. Exit\n" + "Choice: ");

			selection = scanner.nextInt();
			double amount = 0;

			switch (selection) {
			case 1: {
				System.out.println("Deposit amount: ");
				amount = scanner.nextDouble();
				try {
					account.deposit(amount);
					DataSource.updateAccountBalance(account.getId(), account.getBalance());
				} catch (AmountException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2: {
				System.out.println("Withdraw amount: ");
				amount = scanner.nextDouble();
				try {
					account.withdraw(amount);
					DataSource.updateAccountBalance(account.getId(), account.getBalance());
				} catch (AmountException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 3: {
				System.out.println("Current Balance: " + account.getBalance());
				break;
			}
			case 4: {
				Authenticator.logout(customer);
				System.out.println("Logging out...");
				break;
			}
			default: {
				System.out.println("Invalid choice.");
			}
			}
		}
	}
}
