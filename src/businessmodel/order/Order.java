package businessmodel.order;

import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A class representing an abstract order.
 *
 * @author SWOP Team 10
 */
public abstract class Order {

    private User user;
    private DateTime estimatedDeliveryDate;
    private DateTime timestamp;
    private DateTime orderPlacedOnAssemblyLine;
    private DateTime completionDate;
    private boolean completed;
    private VehicleModel carModel;
    private Period standardTimeToFinish;

    /**
     * Creates a new order with a given user.
     *
     * @throws IllegalArgumentException
     * @throws NoClearanceException
     * @throws UnsatisfiedRestrictionException
     * @param    user The user that placed the order.
     */
    public Order(User user, VehicleModel model) throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
        setUser(user);
        setVehicleModel(model);
    }

    /**
     * Get VehicleOptions of the order.
     *
     * @return VehicleOptions
     */
    public abstract ArrayList<VehicleOption> getOptions();

    /**
     * Returns the user that placed the order.
     *
     * @return The user that placed the order.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Set the user for the order.
     *
     * @param user
     * @throws IllegalArgumentException
     * @throws NoClearanceException
     */
    private void setUser(User user) throws IllegalArgumentException, NoClearanceException {
        if (user == null)
            throw new IllegalArgumentException("Bad user!");
        if (!user.canPlaceOrder())
            throw new NoClearanceException(user);
        this.user = user;
    }

    /**
     * Returns whether the order is completed.
     *
     * @return True if the order is completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Updates the estimated delivery date to the given date with the given delay.
     *
     * @param delay The given delay.
     */
    public void updateEstimatedDate(int delay) {
        this.setEstimatedDeliveryDateOfOrder(this.getEstimatedDeliveryDate().plusMinutes(delay));
    }

    /**
     * Returns the time the order was placed.
     *
     * @return The time the order was placed.
     */
    public DateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Set timestamp.
     *
     * @param timestamp
     */
    private void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the completion date of the order.
     *
     * @return The completion date of the order.
     */
    public DateTime getCompletionDate() {
        return completionDate;
    }

    /**
     * Set completion date with the given date.
     *
     * @param completionDate
     * @throws IllegalArgumentException
     */
    private void setCompletionDate(DateTime completionDate) throws IllegalArgumentException {
        if (completionDate == null)
            throw new IllegalArgumentException("Bad completion date!");
        this.completionDate = completionDate;
    }

    /**
     * Returns the date the user wants the order to be finished.
     * Applies only to Single Task Orders.
     *
     * @return The date the user wants the order to be finished.
     */
    public DateTime getUserEndDate() {
        return null;
    }

    /**
     * Returns the estimated delivery date of the order.
     *
     * @return The estimated delivery date of the order.
     */
    public DateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    /**
     * Set the estimated delivery date with the given date.
     *
     * @param estimatedDeliveryDate
     * @throws IllegalArgumentException
     */
    private void setEstimatedDeliveryDate(DateTime estimatedDeliveryDate) throws IllegalArgumentException {
        if (estimatedDeliveryDate == null)
            throw new IllegalArgumentException("Bad estimated delivery date!");
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    /**
     * Returns the time the order was placed on the assembly line.
     *
     * @return The time the order was placed on the assembly line.
     */
    public DateTime getOrderPlacedOnAssemblyLine() {
        return orderPlacedOnAssemblyLine;
    }

    /**
     * Set the date of the order when it was placed.
     *
     * @param orderPlacedOnAssemblyLine
     * @throws IllegalArgumentException
     */
    private void setOrderPlacedOnAssemblyLine(DateTime orderPlacedOnAssemblyLine) throws IllegalArgumentException {
        if (orderPlacedOnAssemblyLine == null)
            throw new IllegalArgumentException("Bad order placed on assembly line date!");
        this.orderPlacedOnAssemblyLine = orderPlacedOnAssemblyLine;
    }

    /**
     * Sets the order to completed.
     */
    public void setCompleted() {
        this.completed = true;
    }

    /**
     * Sets the time the order was placed to the given time.
     *
     * @param timestamp The time the order as placed.
     * @throws IllegalArgumentException | If timestamp is equal to 'null'
     * | timestamp == null
     */
    public void setTimestampOfOrder(DateTime timestamp) throws IllegalArgumentException {
        if (timestamp == null) throw new IllegalArgumentException("Bad timestamp");
        this.setTimestamp(timestamp);
    }

    /**
     * Sets the completion date of the order to the given completion date.
     *
     * @param deliverydate The new delivery date of the order.
     * @throws IllegalArgumentException | If deliverydate is equal to 'null'
     *                                  | deliverydate == null
     */
    public void setCompletionDateOfOrder(DateTime deliverydate) throws IllegalArgumentException {
        this.setCompletionDate(deliverydate);
    }

    /**
     * Sets the time the order was placed on the assembly line to the given time.
     *
     * @param placedOnAssemblyLine The new time the order was placed on the assembly line.
     * @throws IllegalArgumentException | If placedOnAssemblyLine is equal to 'null'
     * | placedOnAssemblyLine == null
     */
    public void setPlacedOnAssemblyLineOfOrder(DateTime placedOnAssemblyLine) throws IllegalArgumentException {
        this.setOrderPlacedOnAssemblyLine(placedOnAssemblyLine);
    }

    /**
     * Sets the estimated delivery date of the order to the given date.
     *
     * @param estimatedDeliveryDate The new estimated delivery date.
     */
    public void setEstimatedDeliveryDateOfOrder(DateTime estimatedDeliveryDate) {
        this.setEstimatedDeliveryDate(estimatedDeliveryDate);
    }

    /**
     * Returns the car model that is ordered.
     *
     * @return The car model that is ordered.
     */
    public VehicleModel getVehicleModel() {
        return this.carModel;
    }

    /**
     * Sets the car model of the order to the given car model.
     *
     * @param model The car model that is ordered.
     */
    private void setVehicleModel(VehicleModel model) throws IllegalArgumentException {
        this.carModel = model;
    }

    @Override
    public String toString() {
        if (this.completed)
            return "Completion date = " + this.completionDate.toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT);
        else
            return "Estimated delivery date = " + this.estimatedDeliveryDate.toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT);
    }

    /**
     * A method to get the standardTimeToFinish.
     */
    public Period getStandardTimeToFinish() {
        return this.standardTimeToFinish;
    }

    /**
     * A method to get the standardTimeToFinish.
     */
    public void setStandardTimeToFinish(int minutes) {
        this.standardTimeToFinish = new Period(minutes * 60000);
    }
}
