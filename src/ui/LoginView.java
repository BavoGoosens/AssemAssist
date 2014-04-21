package ui;

import java.util.Scanner;

import businessmodel.CarManufacturingCompany;
import control.LoginController;

public class LoginView extends View {
	
	private Scanner scan = new Scanner(System.in);
	
	private LoginController controller; 
	
	private String username;
	
	public LoginView(LoginController control, CarManufacturingCompany cmc) {
		super(cmc);
		this.controller = control;
	}
	
	@Override
	public void display() {
		System.out.println("Please enter your login information");
		System.out.println("Username: ");
		this.username = scan.nextLine();
		this.check(username);
		System.out.println("Password: ");
		String password = scan.nextLine();
		this.check(password);
		this.controller.login(username, password);
	}

	@Override
	public void update() {
		this.displayHelp();
		this.display();
	}

	@Override
	public void displayHelp() {
		super.helpOverview();
	}	
	
	private void check(String str){
		if (str.equalsIgnoreCase("quit"))
			this.controller.quit();
		if (str.equalsIgnoreCase("cancel"))
			this.controller.cancel();
		if (str.equalsIgnoreCase("help"))
			this.controller.help();
	}
	
	public void register(){
		System.out.println("We could not find you in the system ! \nEnter as what kind of user (garageholder"
				+ "/mechanic/manager/customshopmanager) you want to register: ");
		String type = scan.nextLine();
		this.check(type);
		System.out.println("Please enter your first name: ");
		String fname = scan.nextLine();
		this.check(fname);
		System.out.println("Please enter your last name: ");
		String lname = scan.nextLine();
		this.check(lname);
		this.controller.register(fname, lname, this.username, type);
	}

	@Override
	public void error() {
		System.out.println("Something went wrong, please try again");
		this.update();
	}
}
