package businessmodel.statistics;

import businessmodel.OrderManager;

/**
 * Class representing a statistics manager for the system, which holds the statistics objects.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class StatisticsManager {

	/**
	 * The car statistics.
	 */
	private VehicleStatistics vehicleStatistics;
	
	/**
	 * The order statistics.
	 */
	private OrderStatistics orderStatistics;
	
	/**
	 * Creates a new statistics manager with a given order manager.
	 * 
	 * @param 	ordermanager
	 * 			The order manager.
	 * @throws	IllegalArgumentException
	 * 			| If the order manager is equal to 'null'
	 * 			| ordermanager == null
	 */
	// TODO Rekening houden met meerdere AssemblyLines
	public StatisticsManager(OrderManager ordermanager) throws IllegalArgumentException {
		if (ordermanager == null) throw new IllegalArgumentException("Bad order manager!");
		this.vehicleStatistics = new VehicleStatistics(ordermanager.getScheduler());
		this.orderStatistics = new OrderStatistics(ordermanager);
	}
	
	/**
	 * Returns the car statistics of the order manager.
	 * 
	 * @return	The car statistics.
	 */
	public VehicleStatistics getVehicleStatistics() {
		return vehicleStatistics;
	}

	/**
	 * Returns the order statistics of the order manager.
	 * 
	 * @return	The order statistics.
	 */
	public OrderStatistics getOrderStatistics() {
		return orderStatistics;
	}

}
