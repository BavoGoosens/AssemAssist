package ui;

import java.util.Scanner;

import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.user.User;

public class ManagerView extends View {
	
	public ManagerView(Model model, User user) {
		super(model);
	}

	@Override
	public void display() {
		System.out.println("> To view the statistics enter STATS");
		System.out.println("> To view the available scheduling algorithms enter ALGO ");
		System.out.println(">> ");
		String input = new Scanner(System.in).nextLine();
		this.check(input);
		if (input.equalsIgnoreCase("stats"))
			this.checkStatistics();
		if (input.equalsIgnoreCase("algo"))
			this.changeAlgorithm();
	}

	private void changeAlgorithm() {
		// TODO Auto-generated method stub
		
	}

	private void checkStatistics() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayHelp() {
		super.helpOverview();
	}

	@Override
	public void error() {
		System.out.println("Something went wrong :(");
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}
}
