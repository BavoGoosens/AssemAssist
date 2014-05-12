package ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

import businessmodel.AssemblyTask;
import businessmodel.Model;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.SingleTaskOrder;
import businessmodel.user.User;
import control.SingleTaskOrderController;
import control.SingleTaskOrderHandler;

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
		Pattern pattern = Pattern.compile("^\\d+$");
		if (pattern.matcher(response).find()){
			int choice = Integer.parseInt(response);
			AssemblyTask chosen = null;
			if (choice >= 1 && choice <= available.size()){
				chosen = available.get(choice - 1);
			} else {
				System.out.println("! wrong input. Please try again");
				this.display();
			}
			DateTime deadline = deadlineDialog();
			ArrayList<VehicleOption> options = chosen.getInstallableOptions();
			VehicleOption opt = null;
			while (opt == null ){
				System.out.println("> Enter the number of the specific option you want to order: ");
				int num = 1;
				for (VehicleOption op : options)
					System.out.println("> " + num ++ + ") " + op);
				System.out.print(">> ");
				response = this.scan.nextLine();
				this.check(response);
				if (pattern.matcher(response).find()){
					choice = Integer.parseInt(response);
					if (choice >= 1 && choice <= options.size())
						opt = options.get(choice - 1);
					else {
						System.out.println("! Bad input. Please try again");
						continue;
					}
				} else {
					this.error();
				}
			}
			ArrayList<VehicleOption> res = new ArrayList<VehicleOption>();
			res.add(opt);
			try {
				this.controller.placeSingleTaskOrder(new SingleTaskOrder(this.user, res , deadline));
				System.out.println("> Your order has been placed :)");
			} catch (IllegalArgumentException | NoClearanceException
					| UnsatisfiedRestrictionException e) {
				System.out.println("! " + e.getMessage());
				this.error();
			}

		} else {
			this.error();
		}
	}

	private DateTime deadlineDialog(){
		DateTime deadline = null;
		DateTime systime = this.getModel().getSystemTime();
		System.out.println("> The System's is: " + systime.toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT));
		System.out.println("> Make sure your entered deadline lies behind this time or your order may not be handled on time");
		while (deadline == null){
			System.out.println("> Enter the deadline in dd/mm/yyyy/ format");
			System.out.print(">> ");
			String response = this.scan.nextLine();
			this.check(response);
			Pattern pat = Pattern.compile("(\\d+)/(\\d+)/(\\d+)");
			Matcher mat = pat.matcher(response);
			if (mat.find()){
				String[] parts = response.split("/");
				int day = Integer.parseInt(parts[0]);
				int month = Integer.parseInt(parts[1]);
				int year = Integer.parseInt(parts[2]);
				deadline = new DateTime(year, month, day, 8, 0);
				break;
			}
		}
		return deadline;
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
