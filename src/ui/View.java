package ui;

import control.Controller;
import businessmodel.CarManufacturingCompany;

public abstract class View {
	
	private Controller controller;
	
	private CarManufacturingCompany cmc;
	
	public View( Controller control, CarManufacturingCompany cmc ){
		this.controller = control;
		this.cmc = cmc;
	}
	
	protected void setController (Controller controller){
		this.controller = controller;
	}
	
	protected Controller getController(){
		return this.getController();
	}
	
	protected CarManufacturingCompany getCarManufacturingCompany(){
		return this.cmc;
	}
	
	public void helpOverview() {
		System.out.println("To go back to the previous step enter: CANCEL");
		System.out.println("To go back to the login prompt enter: QUIT");
	}
	
	public abstract void display();
	
	public abstract void update();

}
