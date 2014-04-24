package ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import control.SchedulingController;
import control.SchedulingHandler;
import businessmodel.Model;
import businessmodel.user.User;

public class SchedulingView extends View {
	
	private User user;
	
	private Scanner scan = new Scanner(System.in);
	
	private SchedulingController controller;

	public SchedulingView(Model cmc, User user) {
		super(cmc);
		this.user = user;
		this.controller = new SchedulingHandler(this.getModel(), this.user);
	}

	@Override
	public void display() {
		Iterator<String> algoss = this.getModel().getSchedulingAlgorithms(user);
		ArrayList<String> algos = new ArrayList<>();
		while (algoss.hasNext())
			algos.add(algoss.next());
		String currentalgo = this.getModel().getCurrentAlgo();
	}

	@Override
	public void displayHelp() {
		this.helpOverview();
	}

	@Override
	public void error() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel() {
		new ManagerView(this.getModel(), this.user).display();		
	}

	@Override
	public void quit() {
		new LoginView(this.getModel()).display();		
	}

}
