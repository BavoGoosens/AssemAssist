package control;

import businessmodel.category.VehicleOption;
import businessmodel.user.User;

import java.util.ArrayList;

/**
 * The Controller for everything related to Scheduling.
 *
 * @author Team 10
 */
public interface SchedulingController {

    /**
     * This method changes the system wide algorithm according to the supplied values.
     *
     * @param user      The user who wants to change the algorithm.
     * @param algorithm The algorithm the caller wants the system to use.
     *                  Currently only SpecificationBatch and FIFO are supported.
     * @param options   The options for the algorithm.
     *                  Only Specification batch will alter its behaviour according to these options.
     */
    public void selectAlgorithm(User user, String algorithm, ArrayList<VehicleOption> options);

}
