package businessmodel.util;

import java.util.Comparator;

import org.joda.time.LocalTime;

public class CarTupleComperator implements Comparator<Tuple<LocalTime,Integer>> {

	@Override
	public int compare(Tuple<LocalTime, Integer> o1,
			Tuple<LocalTime, Integer> o2) {
		// TODO Auto-generated method stub
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
