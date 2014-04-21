package ui;

import java.util.Scanner;

import businessmodel.CarManufacturingCompany;
import control.Controller;

public class LoginView extends View {
	
	private Scanner scan = new Scanner(System.in);
	
	public LoginView(Controller control, CarManufacturingCompany cmc) {
		super(control, cmc);
	}
	
	@Override
	public void display() {
		System.out.println("Please enter your login information");
		System.out.print("Username: ");
		String username = scan.nextLine();
		System.out.print("Password: ");
		String password = scan.nextLine();
		this.getController().login(username, password);
	}

	@Override
	public void update() {
		System.out.println("We could not find you in the system, please try again");
		this.display();
	}	
	
}
