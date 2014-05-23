package businessmodel.assemblyline;

import businessmodel.order.Order;

import java.util.LinkedList;

/**
 * A class that represents a TimeSlot. Each TimeSlot has a number of WorkPosts.
 *
 * @author SWOP 2014 team 10
 */
public class TimeSlot {

    /**
     * A list that holds the WorkSlot's of this TimeSlot.
     */
    private LinkedList<WorkSlot> workslots;

    /**
     * A constructor for the class TimeSlot. A number of WorkSlots will be generated.
     *
     * @param    sizeworkposts the number of workSlots that will be generated.
     */
    protected TimeSlot(int sizeworkposts) {
        generateWorkSlots(sizeworkposts);
    }

    /**
     * A method to plan an order into a WorkSlot.
     *
     * @param workpost the number of the WorkSlot.
     * @param    order the order that needs to be placed in a WorkSlot.
     */
    protected void addOrderToWorkSlot(Order order, int workpost) throws IllegalArgumentException {
        if (workpost < 0 || workpost > this.getWorkSlots().size() - 1)
            throw new IllegalArgumentException("Wrong workpost.");
        this.getWorkSlots().get(workpost).addOrder(order);
    }

    /**
     * A method to get the WorkSlot's of this TimeSlot.
     *
     * @return this.workslots
     */
    protected LinkedList<WorkSlot> getWorkSlots() {
        return this.workslots;
    }

    /**
     * A method that returns if the i WorkSlot is occupied.
     *
     * @param workslot the number of the WorkSlot.
     * @return true if there is an order placed.
     */
    protected boolean workSlotOccupied(int workslot) {
        return this.getWorkSlots().get(workslot).isOccupied();
    }

    /**
     * A method to get the order of the last WorkSlot.
     *
     * @return the last order of the last WorkSlot of this TimeSlot.
     */
    protected Order getLastOrderOfLastWorkSLot() {
        return this.getWorkSlots().getLast().getOrder();
    }

    /**
     * A method to get the next order of this TimeSlot.
     *
     * @return the first order of this TimeSlot.
     */
    protected Order getNextOrder() {
        return getWorkSlots().get(0).getOrder();
    }

    /**
     * A method that generates the WorkSlot's of this TimeSlot.
     *
     * @param    sizeworkposts the number of WorkSlot's that will be generated.
     */
    private void generateWorkSlots(int sizeworkposts) throws IllegalArgumentException {
        if (sizeworkposts <= 0)
            throw new IllegalArgumentException("Size of WorkPosts must be greater than 0");
        this.workslots = new LinkedList<WorkSlot>();
        for (int i = 0; i < sizeworkposts; i++) {
            WorkSlot temp = new WorkSlot();
            this.getWorkSlots().add(temp);
        }
    }
}
