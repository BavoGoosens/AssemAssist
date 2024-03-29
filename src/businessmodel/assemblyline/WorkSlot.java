package businessmodel.assemblyline;

import businessmodel.order.Order;

/**
 * A class that represents a work slot. Each time slot has a number of work slots. Each work slot represents a hour on an work post.
 *
 * @author SWOP 2014 team 10
 */
public class WorkSlot {

    private Order order;
    private boolean occupied;

    /**
     * Creates a new WorkSlot.
     */
    public WorkSlot() {
        this.order = null;
    }

    /**
     * Adds a new order to the work slot.
     *
     * @param    order The new order for the work slot.
     */
    protected void addOrder(Order order) {
        this.setOrder(order);
        this.setOccupied(true);
    }

    protected void removeOrder() {
        this.setOrder(null);
        this.setOccupied(false);
    }

    /**
     * Checks if the work slot is occupied.
     *
     * @return True if the work slot is occupied.
     */
    protected boolean isOccupied() {
        return this.getOccupied();
    }

    /**
     * Returns the order of the work slot.
     *
     * @return The current order of the work slot.
     */
    // protected maken
    public Order getOrder() {
        return this.order;
    }

    /**
     * A method to set the order of this work slot to the given order.
     *
     * @param    order The new order of this work slot.
     * @throws IllegalArgumentException | If the order is equal to 'null'
     * | order == null
     */
    private void setOrder(Order order) throws IllegalArgumentException {
        this.order = order;
    }

    /**
     * Checks if the work slot is occupied.
     *
     * @return True if the work slot is occupied.
     */
    private boolean getOccupied() {
        return this.occupied;
    }

    /**
     * Sets the occupation of this work slot to the given occupation.
     */
    private void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
