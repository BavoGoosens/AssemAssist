package businessmodel.order;

import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;
import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Class representing a single task order.
 * This class extends an abstract order.
 *
 * @author SWOP team 10
 */
public class SingleTaskOrder extends Order {

    /**
     * The date the user wants the order finished.
     */
    private DateTime userEndDate;
    /**
     * The options that are ordered.
     */
    private ArrayList<VehicleOption> options;

    /**
     * Creates a new single task order with a given user, set of options and user end date.
     *
     * @param user        The user that placed the order.
     * @param options     The options that are ordered.
     * @param userEndDate The date the user wants the order to be finished.
     * @throws IllegalArgumentException
     * @throws NoClearanceException
     * @throws UnsatisfiedRestrictionException
     */
    public SingleTaskOrder(User user, ArrayList<VehicleOption> options, DateTime userEndDate) throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
        super(user, null);
        this.setOptions(options);
        this.setUserEndDate(userEndDate);
    }

    /**
     * Returns a cloned list of options that are ordered.
     *
     * @return The options (clone) that are ordered.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<VehicleOption> getOptions() {
        return (ArrayList<VehicleOption>) this.options.clone();
    }

    /**
     * Sets the options of the order to the given options.
     *
     * @param options The options that are ordered.
     * @throws IllegalArgumentException | If the list of options is equal to 'null'
     * | options == null
     */
    @SuppressWarnings("unchecked")
    private void setOptions(ArrayList<VehicleOption> options) throws IllegalArgumentException {
        if (options == null) throw new IllegalArgumentException("Bad list of options!");
        this.options = (ArrayList<VehicleOption>) options.clone();
    }

    /**
     * Returns the date to finish the order.
     *
     * @return The requested date to finish the order.
     */
    public DateTime getUserEndDate() {
        return this.userEndDate;
    }

    /**
     * Sets the requested end date of the order to the given end date.
     *
     * @param userEndDate The new end date of the order.
     * @throws IllegalArgumentException | If the userEndDate is equal to 'null'
     *                                  | userEndDate == null
     */
    private void setUserEndDate(DateTime userEndDate) throws IllegalArgumentException {
        if (userEndDate == null) throw new IllegalArgumentException("Bad user end date!");
        this.userEndDate = userEndDate;
    }
}
