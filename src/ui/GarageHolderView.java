package ui;

import businessmodel.CarManufacturingCompany;
import businessmodel.observer.Subject;
import control.StandardOrderController;

public class GarageHolderView extends View {
	
	private Subject subject;
	
	private StandardOrderController controller;
	
	public GarageHolderView(StandardOrderController control, CarManufacturingCompany cmc) {
		super(cmc);
		this.controller = control;
		super.cmc.registerObserver(this);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayHelp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error() {
		// TODO Auto-generated method stub
		
	}

}
