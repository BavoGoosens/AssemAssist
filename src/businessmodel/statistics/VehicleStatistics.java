package businessmodel.statistics;

import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.LocalDate;

import businessmodel.Scheduler;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.util.CarTupleComperator;
import businessmodel.util.Tuple;

/**
 * A class representing the car statistics of the system.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class CarStatistics implements Observer {

	/**
	 * The average number of cars produced.
	 */
	private int avarage;

	/**
	 * The median number of cars produced.
	 */
	private int median;

	/**
	 * The number of cars produced for each day.
	 */
	private ArrayList<Tuple<LocalDate, Integer>> numberOfCars;

	/**
	 * Creates a new car statistics object with a given subject it needs to observe.
	 * 
	 * @param 	subject
	 * 			The subject the statistics need to observe.
	 * @throws	IllegalArgumentException
	 * 			| If the subject is equal to 'null' or if the subject isn't an order scheduler.
	 * 			| subject == null || !(subject instanceof Scheduler)
	 */
	public CarStatistics(Subject subject) throws IllegalArgumentException {
		if (subject == null || !(subject instanceof Scheduler)) throw new IllegalArgumentException("Bad subject!");
		subject.subscribeObserver(this);
		this.numberOfCars = new ArrayList<Tuple<LocalDate, Integer>>();
	}

	/**
	 * Returns the average number of cars produced.
	 * 
	 * @return The average number of cars produced.
	 */
	public int getAverage(){
		return this.avarage;
	}
	
	/**
	 * Returns the median number of cars produced.
	 * 
	 * @return	The median number of cars produced.
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
		if (this.numberOfCars.size() > numberOfDays){
			ArrayList<Tuple<LocalDate, Integer>> result = new ArrayList<Tuple<LocalDate, Integer>>(numberOfDays);
			for(int i = this.numberOfCars.size(); i >= this.numberOfCars.size() - numberOfDays ; i--){
				result.add(this.numberOfCars.get(i));
			}
			return result;
		} else 
			throw new IllegalArgumentException("The supplied number of days is to large");
	}

	/**
	 * Calculates and updates the average number of cars produced per day.
	 */
	private void updateAverage(){
		int count = 0;
		for (Tuple<LocalDate, Integer> tup : this.numberOfCars){
			count += tup.getY();
		}		
		this.avarage = (int) Math.floor(count / this.numberOfCars.size());
	}

	/**
	 * Calculates and updates the median number of cars produced per day.
	 */
	@SuppressWarnings("unchecked")
	private void updateMedian(){
		ArrayList<Tuple<LocalDate, Integer>> temp = (ArrayList<Tuple<LocalDate, Integer>>) this.numberOfCars.clone();
		Collections.sort(temp, new CarTupleComperator());
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
	 * 			| If the subject is equal to 'null' or if the subject isn't a scheduler
	 * 			| subject == null or !(subject instanceof Scheduler)
	 */
	@Override
	public void update(Subject subject) throws IllegalArgumentException {
		if (subject != null && subject instanceof Scheduler) {
			Scheduler scheduler = (Scheduler) subject;
			LocalDate date = scheduler.getCurrentTime().toLocalDate();
			int dayOrdersCount = scheduler.getDayOrdersCount();
			this.numberOfCars.add(new Tuple<LocalDate, Integer>(date, dayOrdersCount));
			this.updateAverage();
			this.updateMedian();
		} else {
			throw new IllegalArgumentException("Bad subject!");
		}
	}

}
