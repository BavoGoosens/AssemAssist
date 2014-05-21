package businessmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.joda.time.DateTime;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyLineAFactory;
import businessmodel.assemblyline.AssemblyLineBFactory;
import businessmodel.assemblyline.AssemblyLineCFactory;
import businessmodel.assemblyline.AssemblyLineScheduler;
import businessmodel.category.VehicleOption;
import businessmodel.order.Order;

/**
 * The Main Scheduler who's in charge for the individual AssemblyLineSchedulers.
 *
 * @author Team 10
 *
 */
public class MainScheduler {

	private OrderManager ordermanager;
	private ArrayList<AssemblyLine> assemblylines;
	private String systemWideAlgo;
	private final int nbOrdersSpecificationBatch = 3;

	/**
	 * Constructor to create MainScheduler and set the OrderManager.
	 * @param ordermanager
	 */
	public MainScheduler(OrderManager ordermanager){
		this.setOrderManager(ordermanager);
		this.generateAssemblyLines();
		changeSystemWideAlgorithm("FIFO", null);
	}

	/**
	 * Generate the AssemblyLines.
	 */
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

	/**
	 * Schedule pending orders.
	 */
	public void schedulePendingOrders() {
		this.ordermanager.schedulePendingOrders();
	}

	/**
	 * Finish given order.
	 * @param completedorder
	 */
	public void finishedOrder(Order completedorder) {
		this.ordermanager.finishedOrder(completedorder);
	}

	/**
	 * Place order in front of pending orders queue.
	 * @param order
	 */
	public void placeOrderInFront(Order order) {
		this.ordermanager.placeOrderInFront(order);
	}

    public void orderCannotBePlaced(Order order){
        this.ordermanager.orderCannotBePlaced(order);
    }

	/**
	 * Get the pending orders of the system.
	 * @return
	 */
	public LinkedList<Order> getPendingOrders() {
		return this.ordermanager.getPendingOrders();
	}

	/**
	 * Get AssemblyLineSchedulers.
	 * @return AssemblyLineSchedulers
	 */
	public ArrayList<AssemblyLineScheduler> getAssemblyLineSchedulers() {
		ArrayList<AssemblyLineScheduler> schedulers = new ArrayList<AssemblyLineScheduler>();
		for (AssemblyLine assemblyLine: this.getAssemblyLines()) {
			schedulers.add(assemblyLine.getAssemblyLineScheduler());
		}
		return schedulers;
	}

	/**
	 * Get the system wide algorithm.
	 * @return
	 */
	public String getAlgorithm() {
		return systemWideAlgo;
	}

	/**
	 * Get the OrderManager.
	 * @return ordermanager
	 */
	protected OrderManager getOrderManager() {
		return ordermanager;
	}


	/**
	 * Place the given order.
	 * @param order
	 */
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

	/**
	 * Get all the AssemblyLines.
	 * @return assemblylines
	 */
	protected ArrayList<AssemblyLine> getAssemblyLines() {
		return this.assemblylines;
	}

	/**
	 * Change the algorithm.
	 * @param algo
	 * @param options
	 */
	protected void changeSystemWideAlgorithm(String algo, ArrayList<VehicleOption> options) {

		this.systemWideAlgo = algo;

		for (AssemblyLine assemblyLine: this.getAssemblyLines()) {
			assemblyLine.getAssemblyLineScheduler().changeAlgorithm(algo, options);
		}
	}

	/**
	 * Check if the given options satisfy the restraint of occurrence of the given amount of orders.
	 * @param options
	 * @return
	 */
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

    // returns the lines which are ready to receive a additional order.
	/**
	 * Get the possible AssemblyLines to place the given order.
	 * @param order
	 * @return
	 */
	private ArrayList<AssemblyLine> getPossibleAssemblyLinesToPlaceOrder(Order order){
		ArrayList<AssemblyLine> possibleLines = new ArrayList<AssemblyLine>();
		for(AssemblyLine assemblyLine : this.getAssemblyLines()){
			if(assemblyLine.canAddOrder(order))
				possibleLines.add(assemblyLine);
		}
		return possibleLines;
	}

	/**
	 * Set the OrderManager.
	 * @param ordermanager
	 */
	private void setOrderManager(OrderManager ordermanager) {
		this.ordermanager = ordermanager;
	}

	/**
	 * Set the AssemblyLines with the given AssemblyLines.
	 * @param assemblylines
	 */
	private void setAssemblyLines(ArrayList<AssemblyLine> assemblylines) {
		this.assemblylines = assemblylines;
	}

	/**
	 * Get the latest time of the AssemblyLines.
	 * @return
	 */
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

	/**
	 * Get the sets of VehicleOptions that occur in more than the given amount of orders.
	 * @return iterator of the sets
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
