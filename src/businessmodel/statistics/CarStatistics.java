package businessmodel.statistics;

import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import businessmodel.Scheduler;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.util.CarTupleComperator;
import businessmodel.util.Tuple;

public class CarStatistics implements Observer {

	/**
	 * The average number of cars produced.
	 */
	private int avarage;

	/**
	 * The median number of cars produced.
	 */
	private int median;
	
	DateTime beginTime;

	private ArrayList<Tuple<LocalDate, Integer>> number_of_cars;

	public CarStatistics(Subject subject) throws IllegalArgumentException {
		subject.subscribeObserver(this);
		if (subject == null || !(subject instanceof Scheduler)) throw new IllegalArgumentException("Bad subject!");
		this.number_of_cars = new ArrayList<Tuple<LocalDate, Integer>>();
	}

	public int getAverage(){
		return this.avarage;
	}

	public int getMedian(){
		return this.median;
	}

	public ArrayList<Tuple<LocalDate, Integer>> getLastDays(int number_of_days){
		if (this.number_of_cars.size() > number_of_days){
			ArrayList<Tuple<LocalDate, Integer>> result = new ArrayList<Tuple<LocalDate, Integer>>(number_of_days);
			for(int i = this.number_of_cars.size(); i >= this.number_of_cars.size() - number_of_days ; i--){
				result.add(this.number_of_cars.get(i));
			}
			return result;
		} else 
			throw new IllegalArgumentException("The supplied number of days is to large");
	}

	private void updateAverage(){
		int count = 0;
		for (Tuple<LocalDate, Integer> tup : this.number_of_cars){
			count += tup.getY();
		}		
		this.avarage = (int) Math.floor(count / this.number_of_cars.size());
	}

	private void updateMedian(){
		ArrayList<Tuple<LocalDate, Integer>> temp = (ArrayList<Tuple<LocalDate, Integer>>) this.number_of_cars.clone();
		Collections.sort(temp, new CarTupleComperator());
		if ( temp.size() % 2 == 0 ){
			int fml = temp.get((temp.size()/2) -1).getY();
			int fol = temp.get((temp.size()/2)).getY();
			this.median = (fml + fol) / 2;
		} else {
			this.median = temp.get((int) Math.ceil(temp.size()/2)).getY();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void update(Subject subject) {
		if (subject instanceof Scheduler) {
			Scheduler scheduler = (Scheduler) subject;
			LocalDate date = scheduler.getCurrentTime().toLocalDate();
			int dayOrdersCount = scheduler.getDayOrdersCount();
			this.number_of_cars.add(new Tuple(date, dayOrdersCount));
			this.updateAverage();
			this.updateMedian();
		}
	}

}
