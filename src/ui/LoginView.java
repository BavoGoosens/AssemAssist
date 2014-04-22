package ui;

import java.util.Scanner;

import businessmodel.Model;
import businessmodel.user.CustomShopManager;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.user.Mechanic;
import businessmodel.user.User;

public class LoginView extends View {

	private Scanner scan = new Scanner(System.in);

	private User user;

	public LoginView(Model model) {
		super(model);
	}

	@Override
	public void display() {
		System.out.println("> Please enter your login information");
		System.out.println(">> Username: ");
		String username = scan.nextLine();
		this.check(username);
		System.out.println(">> Password: ");
		String password = scan.nextLine();
		this.check(password);
		this.user = this.getModel().login(username, password);
		if (this.user.equals(null))
			this.register();
		if (this.user instanceof GarageHolder){
			GarageHolderView view = new GarageHolderView(this.getModel(), this.user);
			view.display();
		} else if (this.user instanceof Mechanic) {
			CarMechanicView view = new CarMechanicView(this.getModel(), this.user);
			view.display();
		} else if (this.user instanceof Manager) {
			ManagerView view = new ManagerView(this.getModel(), this.user);
			view.display();
		} else if (this.user instanceof CustomShopManager){
			CustomShopManagerView view = new CustomShopManagerView(this.getModel(), this.user);
			view.display();
		} else {
			this.error();
		}

	}

	@Override
	public void displayHelp() {
		super.helpOverview();
	}	

	private void check(String str){
		if (str.equalsIgnoreCase("quit"))
			this.quit();
		if (str.equalsIgnoreCase("cancel"))
			this.cancel();
		if (str.equalsIgnoreCase("help"))
			this.displayHelp();
	}

	public void register(){
		System.out.println("> We could not find you in the system ! \nEnter as what kind of user (garageholder"
				+ "/mechanic/manager/customshopmanager) you want to register: ");
		String type = scan.nextLine();
		this.check(type);
		System.out.println(">> Please enter your first name: ");
		String fname = scan.nextLine();
		this.check(fname);
		System.out.println(">> Please enter your last name: ");
		String lname = scan.nextLine();
		this.check(lname);
		System.out.println(">> Please enter your username: ");
		String uname = scan.nextLine();
		this.check(uname);
		System.out.println(">> Please enter your password: ");
		String password = scan.nextLine();
		this.check(password);
		if (type.equalsIgnoreCase("garageholder")){
			User user = new GarageHolder(fname, lname, uname);
			this.getModel().register(user);
			this.display();
		} else if (type.equalsIgnoreCase("mechanic")){
			User user = new Mechanic(fname, lname, uname);
			this.getModel().register(user);
			this.display();
		} else if (type.equalsIgnoreCase("manager")){
			User user = new Manager(fname, lname, uname);
			this.getModel().register(user);			
			this.display();
		} else if (type.equalsIgnoreCase("customshopmanager")){
			User user = new CustomShopManager(fname, lname, uname);
			this.getModel().register(user);
			this.display();
		} else {
			this.error();
		}
	}

	@Override
	public void error() {
		System.out.println("! Something went wrong, please try again");
		this.displayHelp();
		this.display();
	}

	@Override
	public void cancel() {
		this.quit();
	}

	@Override
	public void quit() {
		this.displayHelp();
		this.display();
	}
}
