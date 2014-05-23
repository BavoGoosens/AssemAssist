package businessmodel;

import java.util.ArrayList;
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
	 * @param completedOrder
	 */
	public void finishedOrder(Order completedOrder, int actualDelay) {
		this.ordermanager.finishedOrder(completedOrder, actualDelay);
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
	 * This method tries to schedule the order on one of the assembly line's
	 * pending queues.
	 *
	 * @param   order
	 *                  The order you want to try to schedule in
	 */
	protected void placeOrder(Order order){
		// Get all the assembly lines that are free to accept an order.
		ArrayList<AssemblyLine> possibleAssemblyLines = getPossibleAssemblyLinesToPlaceOrder(order);
		if(possibleAssemblyLines.size() != 0){
			// determine which of the assembly lines will be the fastest to process the order.
			AssemblyLine fastestAssemblyLine = possibleAssemblyLines.get(0);
			for(AssemblyLine assemblyLine : getPossibleAssemblyLinesToPlaceOrder(order)){
				if(assemblyLine.getEstimatedCompletionTimeOfNewOrder(order).isBefore(fastestAssemblyLine.getEstimatedCompletionTimeOfNewOrder(order)))
					fastestAssemblyLine = assemblyLine;
			}
			// add the order to the assembly line (try)
			fastestAssemblyLine.getAssemblyLineScheduler().addOrder(order);
		} else
			this.orderCannotBePlaced(order);
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
	private boolean checkOptionsForSpecificationBatch(ArrayList<VehicleOption> options, AssemblyLine assem) {

		int orderCount = 0;

		for(Order order: assem.getAssemblyLineScheduler().getOrdersClone()){
			int count = 0;
			for(VehicleOption opt: options){
				for(VehicleOption opt2: order.getOptions()){
					if (opt.toString().equals(opt2.toString()))
						count++;
				}
			}
			if (count == options.size()) orderCount++;
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


	/**
	 * Get the sets of VehicleOptions that occur in more than the given amount of orders.
	 * @return iterator of the sets
	 */
	public Iterator<ArrayList<VehicleOption>> getUnscheduledVehicleOptions() {

		ArrayList<ArrayList<VehicleOption>> choices = new ArrayList<ArrayList<VehicleOption>>();
		ArrayList<ArrayList<VehicleOption>> tempChoices = new ArrayList<ArrayList<VehicleOption>>();
		ArrayList<Integer> countList = new ArrayList<Integer>();

		for (AssemblyLine line : this.getAssemblyLines()){

			tempChoices.clear();
			for(Order order: line.getAssemblyLineScheduler().getOrdersClone()){

				ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
				for(VehicleOption opt: order.getOptions()){

					ArrayList<VehicleOption> temp = new ArrayList<VehicleOption>();
					temp.add(opt);
					if (this.checkOptionsForSpecificationBatch(temp, line))	options.add(opt);

				}

				boolean duplicate = false;
				for(ArrayList<VehicleOption> opts: tempChoices)
					for(VehicleOption opt: opts)
						for(VehicleOption opt2: options)
							if (opt.equals(opt2))
								duplicate = true;


				if (!duplicate || tempChoices.size() == 0)
					tempChoices.add(options);

			}

			countList.clear();
			for(ArrayList<VehicleOption> opt: tempChoices)
				for(ArrayList<VehicleOption> listOpts: this.getSubsets(opt))
						if (!countList.contains(listOpts.size()) && countList.add(listOpts.size()))
							if (this.checkOptionsForSpecificationBatch(listOpts, line))
								if (!listOpts.isEmpty())
									choices.add(listOpts);



		}


		//		for(ArrayList<VehicleOption> list: choices){
		//			System.out.println(list.toString());
		//		}

		return  choices.iterator();
	}

	private ArrayList<ArrayList<VehicleOption>> getSubsets(ArrayList<VehicleOption> set) {

		ArrayList<ArrayList<VehicleOption>> subsetCollection = new ArrayList<ArrayList<VehicleOption>>();

		if (set.size() == 0) {
			subsetCollection.add(new ArrayList<VehicleOption>());
		} else {
			ArrayList<VehicleOption> reducedSet = new ArrayList<VehicleOption>();

			reducedSet.addAll(set);

			VehicleOption first = reducedSet.remove(0);
			ArrayList<ArrayList<VehicleOption>> subsets = getSubsets(reducedSet);
			subsetCollection.addAll(subsets);

			subsets = getSubsets(reducedSet);

			for (ArrayList<VehicleOption> subset : subsets) {
				subset.add(0, first);
			}

			subsetCollection.addAll(subsets);
		}

		return subsetCollection;
	}

	public void startNewProductionDay() {
		boolean startForReal = true ;
		for (AssemblyLine assemblyLine : this.getAssemblyLines() ){
			AssemblyLineScheduler scheduler = assemblyLine.getAssemblyLineScheduler();
			startForReal = scheduler.couldStartNewDay();
			if (!startForReal) break;
		}
		if (startForReal){
			for (AssemblyLine assemblyLine: this.getAssemblyLines()){
				AssemblyLineScheduler scheduler = assemblyLine.getAssemblyLineScheduler();
				scheduler.scheduleNewDay();
			}
			this.schedulePendingOrders();
		}
	}
}
