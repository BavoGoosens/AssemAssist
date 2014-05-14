package businessmodel.statistics;

import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.LocalDate;

import businessmodel.assemblyline.AssemblyLineScheduler;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.util.Tuple;
import businessmodel.util.VehicleTupleComperator;

/**
 * A class representing the car statistics of the system.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class VehicleStatistics implements Observer {

	/**
	 * The average number of vehicles produced.
	 */
	private int avarage;

	/**
	 * The median number of vehicles produced.
	 */
	private int median;

	/**
	 * The number of vehicles produced for each day.
	 */
	private ArrayList<Tuple<LocalDate, Integer>> numberOfVehicles;

	/**
	 * Creates a new car statistics object with a given subject it needs to observe.
	 * 
	 * @param 	subjects
	 * 			The list of subjects the statistics need to observe.
	 * @throws	IllegalArgumentException
	 * 			| If the subject is equal to 'null' or if the subject isn't an order assemblyline.
	 * 			| subject == null || !(subject instanceof AssemblyLineScheduler)
	 */
	public VehicleStatistics(ArrayList<Subject> subjects) throws IllegalArgumentException {
		if (subjects == null || subjects.size() == 0) throw new IllegalArgumentException("Bad list of subjects!");
        for (Subject subject: subjects) {
            if (subject == null || !(subject instanceof AssemblyLineScheduler)) throw new IllegalArgumentException("Bad subject!");
            subject.subscribeObserver(this);
        }
		this.numberOfVehicles = new ArrayList<Tuple<LocalDate, Integer>>();
	}

	/**
	 * Returns the average number of vehicles produced.
	 * 
	 * @return The average number of vehicles produced.
	 */
	public int getAverage(){
		return this.avarage;
	}
	
	/**
	 * Returns the median number of vehicles produced.
	 * 
	 * @return	The median number of vehicles produced.
	 */
	public int getMedian(){
		return this.median;
	}

	/**
	 * Returns the number of orders finished for the last 'numberOfDays' days.
	 * 
	 * @param 	numberOfDays
	 * 			The number of days.
	 * @return	The number of orders that are finished every day for the last 'numberOfDays' days.
	 * 
	 */
	public ArrayList<Tuple<LocalDate, Integer>> getLastDays(int numberOfDays){
		if (this.numberOfVehicles.size() > numberOfDays){
			ArrayList<Tuple<LocalDate, Integer>> result = new ArrayList<Tuple<LocalDate, Integer>>(numberOfDays);
			for(int i = this.numberOfVehicles.size(); i >= this.numberOfVehicles.size() - numberOfDays ; i--){
				result.add(this.numberOfVehicles.get(i));
			}
			return result;
		} else 
			throw new IllegalArgumentException("The supplied number of days is to large");
	}

	/**
	 * Calculates and updates the average number of vehicles produced per day.
	 */
	private void updateAverage(){
		int count = 0;
		for (Tuple<LocalDate, Integer> tup : this.numberOfVehicles){
			count += tup.getY();
		}		
		this.avarage = (int) Math.floor(count / this.numberOfVehicles.size());
	}

	/**
	 * Calculates and updates the median number of vehicles produced per day.
	 */
	@SuppressWarnings("unchecked")
	private void updateMedian(){
		ArrayList<Tuple<LocalDate, Integer>> temp = (ArrayList<Tuple<LocalDate, Integer>>) this.numberOfVehicles.clone();
		Collections.sort(temp, new VehicleTupleComperator());
		if ( temp.size() % 2 == 0 ){
			int fml = temp.get((temp.size()/2) -1).getY();
			int fol = temp.get((temp.size()/2)).getY();
			this.median = (fml + fol) / 2;
		} else {
			this.median = temp.get((int) Math.ceil(temp.size()/2)).getY();
		}
	}

	/**
	 * @throws	IllegalArgumentException
	 * 			| If the subject is equal to 'null' or if the subject isn't a assemblyline
	 * 			| subject == null or !(subject instanceof AssemblyLineScheduler)
	 */
	@Override
	public void update(Subject subject) throws IllegalArgumentException {
		if (subject != null && subject instanceof AssemblyLineScheduler) {
			AssemblyLineScheduler scheduler = (AssemblyLineScheduler) subject;
			LocalDate date = scheduler.getCurrentTime().toLocalDate();
			int dayOrdersCount = scheduler.getDayOrdersCount();
			this.numberOfVehicles.add(new Tuple<LocalDate, Integer>(date, dayOrdersCount));
			this.updateAverage();
			this.updateMedian();
		} else {
			throw new IllegalArgumentException("Bad subject!");
		}
	}

}
