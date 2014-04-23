package ui;

import java.util.ArrayList;
import java.util.Scanner;

import businessmodel.AssemblyLine;
import businessmodel.AssemblyTask;
import businessmodel.Model;
import businessmodel.WorkPost;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.user.User;

public class AssemblyLineStatusView extends View implements Observer {
	
	private User user;
	
	private AssemblyLine subject;

	private Scanner scan = new Scanner(System.in);
	
	public AssemblyLineStatusView(Model cmc, User user) {
		super(cmc);
		this.user = user;
		this.subject = cmc.registerAssemblyLineObserver(this);
	}

	@Override
	public void display() {
		ArrayList<WorkPost> posts =  this.subject.getWorkPosts();
		System.out.println("> This is the assembly line status: ");
		for (WorkPost wp : posts){
			System.out.println("  > " + wp.getName() + "status: " );
			ArrayList<AssemblyTask> pending = wp.getPendingTasks();
			ArrayList<AssemblyTask> finished = wp.getFinishedTasks();
			System.out.println("    > pending tasks:");
			for (AssemblyTask task : pending)
				System.out.println("    > " + task.toString());
			System.out.println("    > finished tasks:");
			for (AssemblyTask task: finished)
				System.out.println("    > " + task.toString());	
		}
		System.out.print(">> ");
		String response = this.scan.nextLine();
		this.check(response);
		this.error();
	}

	@Override
	public void displayHelp() {
		this.helpOverview();
	}

	@Override
	public void cancel() {
		new CarMechanicView(this.getModel(), this.user).display();
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

	@Override
	public void update(Subject subject) {
		if (this.subject != subject)
			this.subject = (AssemblyLine) subject;
		this.display();
	}

}
