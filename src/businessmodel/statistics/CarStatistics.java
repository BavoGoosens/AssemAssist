package businessmodel.statistics;

import java.util.ArrayList;

import org.joda.time.LocalDate;

import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.util.Tuple;

public class CarStatistics implements Observer{

	private Subject s;
	
	/**
	 * The average number of cars produced.
	 */
	private int avarage;

	/**
	 * The median number of cars produced.
	 */
	private int median;

	private ArrayList<Tuple<LocalDate, Integer>> number_of_cars;

	public CarStatistics(Subject s){
		this.s = s;
		s.subscribeObserver(this);
		this.number_of_cars = new ArrayList<>();
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

	@Override
	public void update(Subject s, Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Subject s) {
		// TODO Auto-generated method stub

	}

}
