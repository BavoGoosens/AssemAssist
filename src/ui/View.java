package ui;

import control.Controller;
import businessmodel.CarManufacturingCompany;

public abstract class View {
	
	private CarManufacturingCompany cmc;
	
	public View(CarManufacturingCompany cmc ){
		this.cmc = cmc;
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
	
	public abstract void displayHelp();

}
