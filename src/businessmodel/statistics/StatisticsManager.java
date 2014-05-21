package businessmodel.statistics;

import java.util.ArrayList;

import businessmodel.OrderManager;
import businessmodel.assemblyline.AssemblyLineScheduler;
import businessmodel.observer.Subject;

/**
 * Class representing a statistics manager for the system, which holds the statistics objects.
 * 
 * @author SWOP team 10
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
        // deze for moet gedaan worden omdat een ArrayList van AssemblyLineScheduler geen subklasse is van een ArrayList van Subject
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        for (AssemblyLineScheduler scheduler: ordermanager.getMainScheduler().getAssemblyLineSchedulers()) {
            subjects.add(scheduler);
        }
		this.vehicleStatistics = new VehicleStatistics(subjects);
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
