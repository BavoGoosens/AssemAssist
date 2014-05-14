package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.assemblyline.AssemblyLine;
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
			if(assem2.getEstimatedCompletionTimeOfNewOrder().isBefore(fastestassem.getEstimatedCompletionTimeOfNewOrder()))
				if(assem2.getAssemblyLineScheduler().canAddOrder())
					fastestassem = assem2;
		}
		fastestassem.getAssemblyLineScheduler().addOrderToSchedule(order);
		return fastestassem;
	}

	private OrderManager getOrdermanager() {
		return ordermanager;
	}

	private void setOrdermanager(OrderManager ordermanager) {
		this.ordermanager = ordermanager;
	}

	protected ArrayList<AssemblyLine> getAssemblylines() {
		return assemblylines;
	}
	
	private void generateAssemblyLines() {
		
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
}
