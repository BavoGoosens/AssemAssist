package ui;

import java.util.Scanner;

import businessmodel.CarManufacturingCompany;
import control.Controller;
import control.ManagerController;

public class ManagerView extends View {
	
	private ManagerController control;

	public ManagerView(ManagerController control, CarManufacturingCompany cmc) {
		super(cmc);
		this.control = control;
	}

	@Override
	public void display() {
		System.out.println("> To view the statistics enter STATS");
		System.out.println("> To view the available scheduling algorithms enter ALGO ");
		System.out.println(">> ");
		String input = new Scanner(System.in).nextLine();
		this.check(input);
		if (input.equalsIgnoreCase("stats"))
			this.control.checkStatistics();
		if (input.equalsIgnoreCase("algo"))
			this.control.changeAlgorithm();
	}

	@Override
	public void update() {
		// NOP
	}

	@Override
	public void displayHelp() {
		super.helpOverview();
	}

	@Override
	public void error() {
		System.out.println("Something went wrong :(");
	}

	private void check(String str){
		if (str.equalsIgnoreCase("quit"))
			this.control.quit();
		if (str.equalsIgnoreCase("cancel"))
			this.control.cancel();
		if (str.equalsIgnoreCase("help"))
			this.control.help();
	}
}
