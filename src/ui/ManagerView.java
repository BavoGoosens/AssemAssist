package ui;

import java.util.Scanner;

import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.user.User;

public class ManagerView extends View {
	
	private User user;
	
	private Scanner scan = new Scanner(System.in);
	
	public ManagerView(Model model, User user) {
		super(model);
		this.user = user;
	}

	@Override
	public void display() {
		System.out.println("> To view the statistics enter STATS");
		System.out.println("> To view the available scheduling algorithms enter ALGO ");
		System.out.println(">> ");
		String input = this.scan.nextLine();
		this.check(input);
		if (input.equalsIgnoreCase("stats"))
			this.checkStatistics();
		if (input.equalsIgnoreCase("algo"))
			this.changeAlgorithm();
		this.error();
	}

	private void changeAlgorithm() {
		new SchedulingView(this.getModel(), this.user).display();
	}

	private void checkStatistics() {
		new StatisticsView(this.getModel(), this.user).display();
	}

	@Override
	public void displayHelp() {
		super.helpOverview();
	}

	@Override
	public void error() {
		System.out.println("! Something went wrong. Please try agian");
		this.display();
	}

	@Override
	public void cancel() {
		this.quit();
	}

	@Override
	public void quit() {
		new LoginView(this.getModel());
	}
}
