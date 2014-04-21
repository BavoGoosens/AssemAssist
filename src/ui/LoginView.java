package ui;

import java.util.Scanner;

import businessmodel.CarManufacturingCompany;
import control.LoginController;

public class LoginView extends View {
	
	private Scanner scan = new Scanner(System.in);
	
	private LoginController controller; 
	
	public LoginView(LoginController control, CarManufacturingCompany cmc) {
		super(cmc);
		this.controller = control;
	}
	
	@Override
	public void display() {
		System.out.println("Please enter your login information");
		System.out.print("Username: ");
		String username = scan.nextLine();
		if (username.equalsIgnoreCase("quit"))
			this.controller.quit();
		if (username.equalsIgnoreCase("cancel"))
			this.controller.cancel();
		System.out.print("Password: ");
		String password = scan.nextLine();
		if (password.equalsIgnoreCase("quit"))
			this.controller.quit();
		if (password.equalsIgnoreCase("cancel"))
			this.controller.cancel();
		this.controller.login(username, password);
	}

	@Override
	public void update() {
		System.out.println("We could not find you in the system, please try again");
		this.display();
	}

	@Override
	public void displayHelp() {
		super.helpOverview();
	}	
	
}
