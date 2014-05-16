package businessmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import businessmodel.assemblyline.*;
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

	protected AssemblyLine placeOrder(Order order){
		ArrayList<AssemblyLine> possiblelines = new ArrayList<AssemblyLine>();
		for(AssemblyLine assem : this.getAssemblyLines()){
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

	protected ArrayList<AssemblyLine> getAssemblyLines() {
		return this.assemblylines;
	}

	public ArrayList<AssemblyLineScheduler> getAssemblyLineSchedulers() {
		ArrayList<AssemblyLineScheduler> schedulers = new ArrayList<AssemblyLineScheduler>();
		for (AssemblyLine assemblyLine: this.getAssemblyLines()) {
			schedulers.add(assemblyLine.getAssemblyLineScheduler());
		}
		return schedulers;
	}

	public ArrayList<Order> getNbOrders(int size, AssemblyLine assemblyline) {
		return null;
	}

	public LinkedList<Order> getPendingOrders() {
		return null;
	}

	public void finishedOrder(Order completedorder) {

	}

	// TODO orders proberen schedulen op andere line.
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

		this.setAssemblyLines(assemblylines);
	}

	public OrderManager getOrderManager() {
		return ordermanager;
	}

	private void setOrderManager(OrderManager ordermanager) {
		this.ordermanager = ordermanager;
	}

	private void setAssemblyLines(ArrayList<AssemblyLine> assemblylines) {
		this.assemblylines = assemblylines;
	}

	protected void changeSystemWideAlgorithm(String algo, ArrayList<VehicleOption> options) {
		this.systemWideAlgo = algo;
		for (AssemblyLine assemblyLine: this.getAssemblyLines()) {
			assemblyLine.getAssemblyLineScheduler().changeAlgorithm(algo, options);
		}
	}

	public String currentSystemWideAlgorithmDescription() {
		return this.systemWideAlgo;
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

	public String getAlgorithm() {
		return systemWideAlgo;
	}
}