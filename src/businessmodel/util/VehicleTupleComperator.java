package businessmodel.util;

import org.joda.time.LocalDate;

import java.util.Comparator;

/**
 * A class representing a comparator for car tuples.
 *
 * @author SWOP team 10
 */
public class VehicleTupleComperator implements Comparator<Tuple<LocalDate, Integer>> {

    @Override
    public int compare(Tuple<LocalDate, Integer> o1, Tuple<LocalDate, Integer> o2) {
        int nb_vehicles_1 = o1.getY();
        int nb_vehicles_2 = o2.getY();
        if (nb_vehicles_1 > nb_vehicles_2)
            return 1;
        else if (nb_vehicles_1 < nb_vehicles_2)
            return -1;
        else
            return 0;
    }

}
