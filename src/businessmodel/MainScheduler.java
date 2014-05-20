package businessmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyLineAFactory;
import businessmodel.assemblyline.AssemblyLineBFactory;
import businessmodel.assemblyline.AssemblyLineCFactory;
import businessmodel.assemblyline.AssemblyLineScheduler;
import businessmodel.category.VehicleOption;
import businessmodel.order.Order;

public class MainScheduler {

	private OrderManager ordermanager;

	private ArrayList<AssemblyLine> assemblylines;

	private String systemWideAlgo;

	private final int nbOrdersSpecificationBatch = 3;

	public MainScheduler(OrderManager ordermanager){
		this.setOrderManager(ordermanager);
		this.generateAssemblyLines();
	}

	public void schedulePendingOrders() {
		this.ordermanager.schedulePendingOrders();
	}

	public void finishedOrder(Order completedorder) {
		this.ordermanager.finishedOrder(completedorder);
	}

	public void placeOrderInFront(Order order) {
		this.ordermanager.placeOrderInFront(order);
	}

	public String currentSystemWideAlgorithmDescription() {
		return this.systemWideAlgo;
	}

	public LinkedList<Order> getPendingOrders() {
		return this.ordermanager.getPendingOrders();
	}

	public ArrayList<AssemblyLineScheduler> getAssemblyLineSchedulers() {
		ArrayList<AssemblyLineScheduler> schedulers = new ArrayList<AssemblyLineScheduler>();
		for (AssemblyLine assemblyLine: this.getAssemblyLines()) {
			schedulers.add(assemblyLine.getAssemblyLineScheduler());
		}
		return schedulers;
	}

	public String getAlgorithm() {
		return systemWideAlgo;
	}

	protected OrderManager getOrderManager() {
		return ordermanager;
	}


	protected void placeOrder(Order order){
		ArrayList<AssemblyLine> possibleAssemblyLines = getPossibleAssemblyLinesToPlaceOrder(order);
		if(possibleAssemblyLines.size() != 0){
			AssemblyLine fastestAssemblyLine = possibleAssemblyLines.get(0);
			for(AssemblyLine assem2 : getPossibleAssemblyLinesToPlaceOrder(order)){
				if(assem2.getEstimatedCompletionTimeOfNewOrder(order).isBefore(fastestAssemblyLine.getEstimatedCompletionTimeOfNewOrder(order)))
					fastestAssemblyLine = assem2;
			}
			fastestAssemblyLine.getAssemblyLineScheduler().addOrder(order);
	    }
    }

	protected ArrayList<AssemblyLine> getAssemblyLines() {
		return this.assemblylines;
	}

	//TODO num parameter
	protected void changeSystemWideAlgorithm(String algo, ArrayList<VehicleOption> options) {
		
		this.systemWideAlgo = algo;
		
//		Iterator<ArrayList<VehicleOption>> it = this.getUnscheduledVehicleOptions();
//		while( it.hasNext()){
//			System.out.println(it.next());
//		}
			
		for (AssemblyLine assemblyLine: this.getAssemblyLines()) {
			assemblyLine.getAssemblyLineScheduler().changeAlgorithm(algo, options);
		}
	}

	private boolean checkOptionsForSpecificationBatch(ArrayList<VehicleOption> options) {

		int orderCount = 0;
		for(AssemblyLine assem: this.getAssemblyLines()){
			for(Order order: assem.getAssemblyLineScheduler().getOrders()){
				int count = 0;
				for(VehicleOption opt: options){
					for(VehicleOption opt2: order.getOptions()){
						if (opt.toString().equals(opt2.toString())) 
							count++;
					}
				}
				if (count == options.size()) orderCount++;
			}
		}
		if (orderCount < this.nbOrdersSpecificationBatch)
			return false;
		return true;
	}

	private ArrayList<AssemblyLine> getPossibleAssemblyLinesToPlaceOrder(Order order){
		ArrayList<AssemblyLine> possiblelines = new ArrayList<AssemblyLine>();
		for(AssemblyLine assem : this.getAssemblyLines()){
			if(assem.canAddOrder(order))
				possiblelines.add(assem);
		}
		return possiblelines;
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

	protected DateTime getTime(){
		DateTime currenttime = this.getAssemblyLines().get(0).getAssemblyLineScheduler().getCurrentTime();
		for(AssemblyLine line: this.getAssemblyLines()){
			if(currenttime.isAfter(line.getAssemblyLineScheduler().getCurrentTime()));
		}
		return currenttime;
	}

    /*
    // TODO: miss maken ni zeker
    public void startNewDay(){
        boolean ready = true;
        for(AssemblyLineScheduler scheduler: this.getAssemblyLineSchedulers()){
            if(!scheduler.checkNewDay())
                ready = false;
        }
    }
    */

	public Iterator<ArrayList<VehicleOption>> getUnscheduledVehicleOptions() {

		ArrayList<ArrayList<VehicleOption>> choices = new ArrayList<ArrayList<VehicleOption>>();
		HashSet<VehicleOption> set = new HashSet<VehicleOption>();

		for (AssemblyLine line : this.getAssemblyLines()){
			for(Order order: line.getAssemblyLineScheduler().getOrders()){

				ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
				for(VehicleOption opt: order.getOptions()){

					ArrayList<VehicleOption> temp = new ArrayList<VehicleOption>();
					temp.add(opt);
//					if (!set.contains(opt)){
						if (this.checkOptionsForSpecificationBatch(temp)){ 
							options.add(opt); 
							set.add(opt);
						}
//					}else{
//						options.add(opt);
//					}
				}

				boolean duplicate = true;
				for(ArrayList<VehicleOption> opts: choices){
					if(opts.size() != options.size())
						break;
					for(int i=0; i < opts.size(); i++){
						if(!options.get(i).toString().equals(opts.get(i).toString())) duplicate = false;
					}
					if(duplicate)
						break;
				}

				if (!duplicate || choices.size() == 0)
					choices.add(options);

			}
		}


		//		for(ArrayList<VehicleOption> list: choices){
		//			System.out.println(list.toString());
		//		}

		return  choices.iterator();
	}

}
