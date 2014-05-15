package ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import businessmodel.Model;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.user.User;

public class AssemblyLineStatusView extends View implements Observer {

	private AssemblyLine subject;

	private Scanner scan = new Scanner(System.in);

    private AssemblyLine selectedAssemblyLine;

	private boolean active = false;

	public AssemblyLineStatusView(Model cmc, User user, AssemblyLine line) {
		super(cmc);
        this.selectedAssemblyLine = line;
        line.subscribeObserver(this);
		setUser(user);
		this.setActive(true);
	}

	private void setActive(boolean b) {
		this.active = b;
	}

	@Override
	public void display() {
		Iterator<WorkPost> postss =  this.getModel().getWorkPosts(this.user, this.selectedAssemblyLine);
		ArrayList<WorkPost> posts = new ArrayList<WorkPost>();
		while(postss.hasNext())
			posts.add(postss.next());
		System.out.println("> This is the assembly line status: ");
		for (WorkPost wp : posts){
			System.out.println("  > " + wp.getName() + "status: " );
			Iterator<AssemblyTask> pendingiter = this.getModel().getPendingTasks(wp);
			ArrayList<AssemblyTask> pending = new ArrayList<AssemblyTask>();
			while (pendingiter.hasNext())
				pending.add(pendingiter.next());
			Iterator<AssemblyTask> finishediter = this.getModel().getFinishedTasks(wp);
			ArrayList<AssemblyTask> finished = new ArrayList<AssemblyTask>();
			while (finishediter.hasNext())
				finished.add(finishediter.next());
			System.out.println("    > pending tasks:");
			for (AssemblyTask task : pending)
				System.out.println("        > " + task.toString());
			System.out.println("    > finished tasks:");
			for (AssemblyTask task: finished)
				System.out.println("        > " + task.toString());	
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
		this.setActive(false);
		new VehicleMechanicView(this.getModel(), this.user, this.selectedAssemblyLine).display();
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
		if (this.active)
			this.display();
	}

}
