package ui;

import businessmodel.Model;

public abstract class View {
	
	private Model cmc;
	
	public View(Model cmc ){
		this.cmc = cmc;
	}
	
	protected Model getModel(){
		return this.cmc;
	}
	
	public void helpOverview() {
		System.out.println("> To go back to the previous step enter: CANCEL");
		System.out.println("> To go back to the login prompt enter: QUIT");
		System.out.println("> If you need help enter: HELP");
	}
	
	public abstract void display();
		
	public abstract void displayHelp();
	
	public abstract void cancel();
	
	public abstract void quit();
	
	public abstract void error();

}
