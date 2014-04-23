package ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import control.AssemblyLineController;
import control.AssemblyLineHandler;
import businessmodel.AssemblyLine;
import businessmodel.AssemblyTask;
import businessmodel.Model;
import businessmodel.WorkPost;
import businessmodel.user.User;

public class CarMechanicView extends View {

	private AssemblyLineController controller;

	private User user;

	private Scanner scan = new Scanner(System.in);
	
	private WorkPost selected_workpost;

	public CarMechanicView(Model cmc, User user) {
		super(cmc);
		this.user = user;
		this.controller = new AssemblyLineHandler(this.getModel(), this.user);
	}

	@Override
	public void display() {
		ArrayList<WorkPost> posts = new ArrayList<>();
		Iterator<WorkPost> temp = this.getModel().getWorkPosts(this.user);
		while(temp.hasNext())
			posts.add(temp.next());
		System.out.println("> If you wish to chech the assembly line status enter STATUS");
		System.out.println("> If you want to perform pending assembly tasks enter the number of the workpost you are residing at");
		System.out.println("> Available workposts");
		int num  = 1;
		for (WorkPost wp : posts)
			System.out.println("> " + num++ + ") " + wp.toString());
		System.out.print(">> ");
		String response = this.scan.nextLine();
		Pattern pattern = Pattern.compile("^\\d*$");
		this.check(response);
		if (response.equalsIgnoreCase("status")){
			AssemblyLineStatusView view = new AssemblyLineStatusView(this.getModel(), this.user);
			view.display();
		} else if (pattern.matcher(response).find()){
			int choice = Integer.parseInt(response);
			if (choice < 1 || choice > posts.size())
				this.error();
			this.selected_workpost = posts.get(choice - 1);
			this.performTasks(this.selected_workpost);
		} else {
			this.error();
		}	
	}

	private void performTasks(WorkPost wp) {
		ArrayList<AssemblyTask> tasks = wp.getPendingTasks();
		System.out.println("> Please enter the number of the task you want to perform: ");
		int num  = 1;
		for (AssemblyTask ass : tasks)
			System.out.println("> " + num ++ + ") " + ass.toString());
		System.out.print(">> ");
		String response = this.scan.nextLine();
		this.check(response);
		Pattern pattern = Pattern.compile("^\\d*$");
		if (pattern.matcher(response).find()){
			int choice = Integer.parseInt(response);
			if (choice < 1 || choice > tasks.size()){
				System.out.println("! You entered something wrong. Please try again");
				this.performTasks(wp);
			} 
			this.displayActionOverview(tasks.get(choice - 1));
		} else {
			System.out.println("! You entered something wrong. Please try again");
			this.performTasks(wp);
		}
	}

	private void displayActionOverview(AssemblyTask assemblyTask) {
		System.out.println("This is the sequence of actions you need to perform: ");
		System.out.println(assemblyTask.getDescription());
		System.out.println("> Enter the number of minutes it took you to perform all the actions "
				+ "and hit enter or enter CANCEL to go back to the overview");
		System.out.println(">> ");
		String response = this.scan.nextLine();
		this.check(response);
		Pattern pattern = Pattern.compile("^\\d*$");
		if (pattern.matcher(response).find()){
			int time = Integer.parseInt(response);
			this.controller.finishTask(assemblyTask, time);
			this.performTasks(this.selected_workpost);
		} else {
			System.out.println("! You entered something wrong. Please try again");
			this.displayActionOverview(assemblyTask);
		}
	}

	@Override
	public void displayHelp() {
		super.helpOverview();
	}

	@Override
	public void cancel() {
		this.displayHelp();
		this.display();
	}

	@Override
	public void quit() {
		new LoginView(this.getModel()).display();
	}

	@Override
	public void error() {
		System.out.println("! You entered something wrong. Please try again");
		this.display();		
	}


}
