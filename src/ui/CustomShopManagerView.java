package ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import control.SingleTaskOrderController;
import control.SingleTaskOrderHandler;
import businessmodel.AssemblyTask;
import businessmodel.Model;
import businessmodel.category.CarOption;
import businessmodel.user.User;

public class CustomShopManagerView extends View {

	private SingleTaskOrderController controller;

	private Scanner scan = new Scanner(System.in);

	public CustomShopManagerView(Model model, User user) {
		super(model);
		setUser(user);
		this.controller = new SingleTaskOrderHandler(this.getModel());
	}

	@Override
	public void display() {
		Iterator<AssemblyTask> availableiter = this.getModel().getAvailableTasks(this.user);
		ArrayList<AssemblyTask> available = new ArrayList<>();
		System.out.println("> These are the assembly tasks you can order: ");
		int count = 1;
		while(availableiter.hasNext()){
			AssemblyTask t = availableiter.next();
			System.out.println("> " + count ++ + ") " + t );
			available.add(t);
		}

		System.out.println("> Enter the number of the task you wish to order: ");
		System.out.print(">> ");
		String response = this.scan.nextLine();
		this.check(response);
		Pattern pattern = Pattern.compile("^\\d*$");
		if (pattern.matcher(response).find()){
			int choice = Integer.parseInt(response);
			AssemblyTask chosen = available.get(choice - 1);
			DateTime deadline;
			System.out.println("> Enter the deadline in dd/mm/yyyy/ format");
			System.out.print(">> ");
			response = this.scan.nextLine();
			Pattern pat = Pattern.compile("(\\d+)/(\\d+)/(\\d+)");
			Matcher mat = pat.matcher(response);
			if (mat.find()){
				int day = Integer.parseInt(mat.group(0));
				int month = Integer.parseInt(mat.group(1));
				int year = Integer.parseInt(mat.group(2));
				deadline = new DateTime(year, month, day, 8, 0);		
			}
			ArrayList<CarOption> options = chosen.getInstallableOptions();
		} else {
			this.error();
		}
	}

	@Override
	public void displayHelp() {
		this.helpOverview();
	}

	@Override
	public void cancel() {
		this.displayHelp();
		this.display();
	}

	@Override
	public void quit() {
		new LoginView(getModel()).display();
	}

	@Override
	public void error() {
		System.out.println("! Something went wrong. Please try agian");
		this.display();
	}

}
