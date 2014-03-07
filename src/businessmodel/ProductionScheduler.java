package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

public class ProductionScheduler {

	private CarManufacturingCompany cms;

	private int overlaytime = 0;

	private int currenttime = 0;

	private int daytime = 16;

	private AssemblyLine assemblyline = new AssemblyLine();
	
	private LinkedList<Order> dayorders;

	private void setAssemblyline(AssemblyLine assemblyline, CarManufacturingCompany cms) {
		this.assemblyline = assemblyline;
		this.cms = cms;
	}

	private AssemblyLine getAssemblyline() {
		return assemblyline;
	}

	private LinkedList<Order> getDayorders() {
		return dayorders;
	}

	public ProductionScheduler() {		
		dayorders = new LinkedList<Order>();
	}


	public ArrayList<Order> canAdvance(){
		ArrayList<Order> notcompleted = new ArrayList<Order>();
		for(int i = 0; i < 1; i++){
			if(this.getDayorders().get(i).isCompleted() == false)
				notcompleted.add(this.getDayorders().get(i));
		}
		return notcompleted;
	}
	
	private void makeDaySchedule(){
		int temp = daytime;
		for(int i = 0 ; i < temp; i++){
			if(this.cms.getOrderManager().getPendingOrders().size() <= 0)
				break;		
			addDayOrder();
		}
	}

	private void updateDaySchedule(){
		if (overlaytime > 60){
			this.overlaytime -= 60;
			addDayOrder();
			this.cms.getOrderManager().updateEstimatedTime();
		}
		if (overlaytime < -60) {
			this.overlaytime += 60;
			removeLastOrderOfDay();
			this.cms.getOrderManager().updateEstimatedTime();
		}
	}

	private void removeLastOrderOfDay() {
		Order temp = this.getDayorders().get(this.getDayorders().size()-1);
		this.cms.getOrderManager().getOrders().addFirst(temp);
		this.getDayorders().remove(this.getDayorders().size()-1);

	}

	private void addDayOrder() {
		this.getDayorders().add(this.cms.getOrderManager().getPendingOrders().getFirst());
		this.cms.getOrderManager().getPendingOrders().removeFirst();
	}
}
