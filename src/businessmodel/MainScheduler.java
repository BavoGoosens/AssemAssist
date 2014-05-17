package businessmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import businessmodel.assemblyline.*;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.category.VehicleOption;
import businessmodel.order.Order;

public class MainScheduler {

	private OrderManager ordermanager;

	private ArrayList<AssemblyLine> assemblylines;

	private String systemWideAlgo;

	public MainScheduler(OrderManager ordermanager){
		this.setOrderManager(ordermanager);
		this.generateAssemblyLines();
	}

	public ArrayList<AssemblyLineScheduler> getAssemblyLineSchedulers() {
		ArrayList<AssemblyLineScheduler> schedulers = new ArrayList<AssemblyLineScheduler>();
		for (AssemblyLine assemblyLine: this.getAssemblyLines()) {
			schedulers.add(assemblyLine.getAssemblyLineScheduler());
		}
		return schedulers;
	}

	public LinkedList<Order> getNewOrders(int size, AssemblyLine assemblyline) {
		return this.ordermanager.getNbOrders(size, assemblyline);
	}

	public LinkedList<Order> getPendingOrders() {
		return this.ordermanager.getPendingOrders();
	}

	public void finishedOrder(Order completedorder) {
		this.ordermanager.finishedOrder(completedorder);
	}

	// TODO orders proberen schedulen op andere line.
	public void placeOrderInFront(Order order) {
		this.ordermanager.placeOrderInFront(order);
	}

	protected OrderManager getOrderManager() {
		return ordermanager;
	}

	public String getAlgorithm() {
		return systemWideAlgo;
	}

	//TODO moet nog getest worden, ookal hebben meerdere assembly lines dezelfde sets, alleen maar unieke teruggeven voor de UI
	public Iterator<ArrayList<VehicleOption>> getUnscheduledVehicleOptions(int num) {
	
		ArrayList<ArrayList<VehicleOption>> choices = new ArrayList<ArrayList<VehicleOption>>();
	
		for (AssemblyLine line : this.assemblylines){
	
			ArrayList<VehicleOption> assOptions = line.getAssemblyLineScheduler().getUnscheduledVehicleOptions(num);
			for(ArrayList<VehicleOption> opt: choices){
				int count=0;
				for (VehicleOption option : assOptions){
					for(VehicleOption opt2: opt){
						if (option.toString().equals(opt2.toString()))	count++;
					}
				}
				if (count!=opt.size()) choices.add(assOptions);
			}
	
		}
	
		return  choices.iterator();
	}

	public String currentSystemWideAlgorithmDescription() {
		return this.systemWideAlgo;
	}

	protected AssemblyLine placeOrder(Order order){
		ArrayList<AssemblyLine> possiblelines = new ArrayList<AssemblyLine>();
		for(AssemblyLine assem : this.getAssemblyLines()){
			if(assem.getAssemblyLineScheduler().canAddOrder(order))
				possiblelines.add(assem);
		}
		if(possiblelines.size() == 0)
			return null;
		AssemblyLine fastestassem = possiblelines.get(0);
		for(AssemblyLine assem2 : possiblelines){
			if(assem2.getEstimatedCompletionTimeOfNewOrder(order).isBefore(fastestassem.getEstimatedCompletionTimeOfNewOrder(order)))
				if(assem2.getAssemblyLineScheduler().canAddOrder(order))
					fastestassem = assem2;
		}
		fastestassem.getAssemblyLineScheduler().addOrder(order);
		return fastestassem;
	}

	protected ArrayList<AssemblyLine> getAssemblyLines() {
		return this.assemblylines;
	}

	protected void changeSystemWideAlgorithm(String algo, ArrayList<VehicleOption> options) {
		this.systemWideAlgo = algo;
		for (AssemblyLine assemblyLine: this.getAssemblyLines()) {
			assemblyLine.getAssemblyLineScheduler().changeAlgorithm(algo, options);
		}
	}

	private void generateAssemblyLines() {
		ArrayList<AssemblyLine> assemblylines = new ArrayList<AssemblyLine>();
		AssemblyLineAFactory factoryA = new AssemblyLineAFactory();
		AssemblyLineBFactory factoryB = new AssemblyLineBFactory();
		AssemblyLineCFactory factoryC = new AssemblyLineCFactory();
	
		AssemblyLine line1 = factoryA.createAssemblyLine(this);
		AssemblyLine line2 = factoryB.createAssemblyLine(this);
		AssemblyLine line3 = factoryC.createAssemblyLine(this);
	
		assemblylines.add(line1);
		assemblylines.add(line2);
		assemblylines.add(line3);
	
		this.setAssemblyLines(assemblylines);
	}

	private void setOrderManager(OrderManager ordermanager) {
		this.ordermanager = ordermanager;
	}

	private void setAssemblyLines(ArrayList<AssemblyLine> assemblylines) {
		this.assemblylines = assemblylines;
	}
}
