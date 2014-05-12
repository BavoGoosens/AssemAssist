package businessmodel.util;

import java.util.Comparator;

import org.joda.time.LocalDate;

/**
 * A class representing a comparator for car tuples.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class VehicleTupleComperator implements Comparator<Tuple<LocalDate, Integer>> {

	@Override
	public int compare(Tuple<LocalDate, Integer> o1, Tuple<LocalDate, Integer> o2) {
		int nb_cars_1 = o1.getY();
		int nb_cars_2 = o2.getY();
		if (nb_cars_1 > nb_cars_2)
			return 1;
		else if (nb_cars_1 < nb_cars_2)
			return -1;
		else
			return 0;
	}

}