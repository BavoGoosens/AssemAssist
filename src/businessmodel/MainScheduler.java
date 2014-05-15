package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyLineAFactory;
import businessmodel.assemblyline.AssemblyLineBFactory;
import businessmodel.assemblyline.AssemblyLineCFactory;
import businessmodel.assemblyline.BodyWorkPostFactory;
import businessmodel.order.Order;

public class MainScheduler {

	private OrderManager ordermanager;
	
	private ArrayList<AssemblyLine> assemblylines;
	
	public MainScheduler(OrderManager ordermanager){
		this.setOrdermanager(ordermanager);
		this.generateAssemblyLines();
	}
	
	protected AssemblyLine placeOrder(Order order){
		ArrayList<AssemblyLine> possiblelines = new ArrayList<AssemblyLine>();
		for(AssemblyLine assem : this.getAssemblylines()){
			if(assem.getAssemblyLineScheduler().canAddOrder())
				possiblelines.add(assem);
		}
		if(possiblelines.size() == 0)
			return null;
		AssemblyLine fastestassem = possiblelines.get(0);
		for(AssemblyLine assem2 : possiblelines){
			if(assem2.getEstimatedCompletionTimeOfNewOrder(order).isBefore(fastestassem.getEstimatedCompletionTimeOfNewOrder(order)))
				if(assem2.getAssemblyLineScheduler().canAddOrder())
					fastestassem = assem2;
		}
		fastestassem.getAssemblyLineScheduler().addOrderToSchedule(order);
		return fastestassem;
	}

	protected ArrayList<AssemblyLine> getAssemblylines() {
		return this.assemblylines;
	}
	
	public ArrayList<Order> getNbOrders(int size, AssemblyLine assemblyline) {
		return null;
	}

	public LinkedList<Order> getPendingOrders() {
		return null;
	}

	public void finishedOrder(Order completedorder) {
		
	}

	public void placeOrderInFront(Order order) {		
		
	}

	private void generateAssemblyLines() {
		ArrayList<AssemblyLine> assemblylines = new ArrayList<AssemblyLine>();
	    AssemblyLineAFactory factoryA = new AssemblyLineAFactory();
	    AssemblyLineBFactory factoryB = new AssemblyLineBFactory();
	    AssemblyLineCFactory factoryC = new AssemblyLineCFactory();
	
	    AssemblyLine line1 = factoryA.createAssemblyLine();
	    AssemblyLine line2 = factoryB.createAssemblyLine();
	    AssemblyLine line3 = factoryC.createAssemblyLine();
	    
	    assemblylines.add(line1);
	    assemblylines.add(line2);
	    assemblylines.add(line3);
	    
	    this.setAssemblylines(assemblylines);
	}

	private OrderManager getOrdermanager() {
		return ordermanager;
	}

	private void setOrdermanager(OrderManager ordermanager) {
		this.ordermanager = ordermanager;
	}

	private void setAssemblylines(ArrayList<AssemblyLine> assemblylines) {
		this.assemblylines = assemblylines;
	}
}
