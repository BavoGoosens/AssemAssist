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
		System.out.println(">> ");
		String response = this.scan.nextLine();
		Pattern pattern = Pattern.compile("^\\d*$");
		this.check(response);
		if (response.equalsIgnoreCase("status")){
			AssemblyLineStatusView view = new AssemblyLineStatusView(this.getModel(), this.user);
			view.display();
		} else if (pattern.matcher(response).find()){
			int choice = Integer.parseInt(response)s;
			if (choice < 1 || choice > posts.size()){
				System.out.println("! You entered something wrong. Please try again");
				this.display();
			}
			WorkPost wp = posts.get(choice - 1);
			this.performTasks(wp);
		} else {
			
		}
		
	}

	private void performTasks(WorkPost wp) {
		ArrayList<AssemblyTask> tasks = wp.getPendingTasks();
		System.out.println("> Please enter the number of the task you want to perform: ");
		int num  = 1;
		for (AssemblyTask ass : tasks)
			System.out.println("> " + num ++ + ") " + ass.toString());
		System.out.println(">> ");
		String response = this.scan.nextLine();
		this.check(response);
		Pattern pattern = Pattern.compile("^\\d*$");
		
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
		// TODO Auto-generated method stub
		
	}

	private void check(String str){
		if (str.equalsIgnoreCase("quit"))
			this.quit();
		if (str.equalsIgnoreCase("cancel"))
			this.cancel();
		if (str.equalsIgnoreCase("help"))
			this.displayHelp();
	}

	
}
