package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import org.joda.time.DateTime;

import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.order.Order;
import businessmodel.schedulingalgorithms.*;

public class Scheduler {

	private ArrayList<Shift> shifts; 

	private LinkedList<Order> orders;

	private SchedulingAlgorithm algo;

	public Scheduler(){
		this.shifts = new ArrayList<Shift>();
		this.orders = new LinkedList<Order>();
		this.changeAlgorithm("fifo");
		generateShifts();	
	}

	protected void schedule(){
		for (Order order: this.getOrders()){
			reschedule(order);
		}
	}

	private void reschedule(Order order){
		if (CanScheduleOrder(order))
			this.ScheduleOrder(order);
		else 
			this.updateTimeofOrder(order);	
	}
	
	private void updateTimeofOrder(Order order) {
		DateTime date = this.getPrevious(order).getEstimateDate();
		if (date.getHourOfDay() <= 21)
			order.setEstimateDate(date.plusMinutes(60));
		else{
			date.plusHours(8);
			order.setEstimateDate(date.plusMinutes(60));
		}
	}

	private void ScheduleOrder(Order order) {
		for(Shift sh: this.getShifts()){
			sh
		}
	}

	private boolean CanScheduleOrder(Order order) {
		return false;
	}
	
	protected void addOrder(Order order){
		if(order == null) 
			throw new IllegalArgumentException();
		this.orders.add(order);
		this.reschedule(order);
	}

	private void generateShifts(){
		Shift currrentshift = new FreeShift(8);
		Shift endshift = new EndShift(8);
		this.getShifts().add(currrentshift);
		this.getShifts().add(endshift);
	}

	public void changeAlgorithm(String algoname){
		if (algoname == null )
			throw new NullPointerException("No scheduling algorithm supplied");
		if (algoname.equalsIgnoreCase("fifo") || algoname.equalsIgnoreCase("first in first out") )
			this.algo = new FIFO(this);
		if (algoname.equalsIgnoreCase("sb") || algoname.equalsIgnoreCase("specification batch"))
			this.algo = new SpecificationBatch(this);
		throw new IllegalSchedulingAlgorithmException("The scheduling algorithm was not recognised");
	}

	private ArrayList<Shift> getShifts() {
		return shifts;
	}

	private LinkedList<Order> getOrders() {
		return orders;
	}

	private SchedulingAlgorithm getAlgo() {
		return algo;
	}

	private Order getNext(Order order){
		int index = this.getOrders().indexOf(order);
		if(index + 1 >= this.getOrders().size() && this.getOrders().size() < 0)
			return null;
		else
			return this.getOrders().get(index-1);
	}

	private Order getPrevious(Order order){
		int index = this.getOrders().indexOf(order);
		if(index-1 < 0 || this.getOrders().size() < 0)
			return null;
		else
			return this.getOrders().get(index-1);
	}
}
