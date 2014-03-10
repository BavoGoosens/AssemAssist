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

	/**
	 * 
	 */
	public ProductionScheduler() {		
		dayorders = new LinkedList<Order>();
	}
	
	/**
	 * 
	 * @param assemblyline
	 * @param cms
	 */
	private void setAssemblyline(AssemblyLine assemblyline, CarManufacturingCompany cms) {
		this.assemblyline = assemblyline;
		this.cms = cms;
	}
	
	/**
	 * This method returns the assembly line that this production scheduler uses.
	 * 
	 * @return AssemblyLine
	 * 		   The Assembly line that this production scheduler uses.
	 */
	private AssemblyLine getAssemblyline() {
		return assemblyline;
	}

	/**
	 * This method returns the list of orders that will probably be finished today. 
	 * There might be delays or speedups that cause the list to shrink of grow.
	 * 
	 * @return LinkedList<Order>
	 * 		   A LinkedList with all the pending orders for today.
	 * 
	 */
	private LinkedList<Order> getDayorders() {
		return dayorders;
	}
	
	/**
	 * This method advances the assembly line if possible.
	 */
	public void advance(){
		if (this.getAssemblyline().canAdvance()){
			Order p = this.dayorders.getFirst();
			Order finished = this.getAssemblyline().advance(p);
		}
		this.cms.getOrderManager().getOrders().add(this.getDayorders().getFirst());
		this.updateDaySchedule();
	}
	
	/**
	 * 
	 */
	private void makeDaySchedule(){
		int temp = daytime;
		for(int i = 0 ; i < temp; i++){
			if(this.cms.getOrderManager().getPendingOrders().size() <= 0)
				break;		
			addDayOrder();
		}
	}

	/**
	 * 
	 */
	private void updateDaySchedule(){
		if (this.getOverlaytime() > 60 && (this.getDaytime()-this.getCurrenttime()>3)){
			this.setOverlaytime(this.getOverlaytime()-60);
			addDayOrder();
			this.cms.getOrderManager().updateEstimatedTime();
		}
		if (overlaytime < -60 && (this.getDaytime()-this.getCurrenttime() > 2)) {
			this.overlaytime += 60;
			removeLastOrderOfDay();
			this.cms.getOrderManager().updateEstimatedTime();
		}
	}

	/**
	 * 
	 */
	private void removeLastOrderOfDay() {
		Order temp = this.getDayorders().get(this.getDayorders().size()-1);
		this.cms.getOrderManager().getOrders().addFirst(temp);
		this.getDayorders().remove(this.getDayorders().size()-1);
	}

	/**
	 * 
	 */
	private void addDayOrder() {
		this.getDayorders().add(this.cms.getOrderManager().getPendingOrders().getFirst());
		this.cms.getOrderManager().getPendingOrders().removeFirst();
	}
	

	private CarManufacturingCompany getCms() {
		return cms;
	}

	private void setCms(CarManufacturingCompany cms) {
		this.cms = cms;
	}

	private int getOverlaytime() {
		return overlaytime;
	}

	private void setOverlaytime(int overlaytime) {
		this.overlaytime = overlaytime;
	}

	private int getCurrenttime() {
		return currenttime;
	}

	private void setCurrenttime(int currenttime) {
		this.currenttime = currenttime;
	}

	private int getDaytime() {
		return daytime;
	}

	private void setDaytime(int daytime) {
		this.daytime = daytime;
	}

	private void setAssemblyline(AssemblyLine assemblyline) {
		this.assemblyline = assemblyline;
	}

	private void setDayorders(LinkedList<Order> dayorders) {
		this.dayorders = dayorders;
	}
	
	private LinkedList<Order> getAllOrders(){
		return this.cms.getOrderManager().getOrders();
	}
}
