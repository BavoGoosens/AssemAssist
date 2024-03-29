package businessmodel.assemblyline;

import businessmodel.order.Order;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class representing a free shift.
 *
 * @author SWOP team 10 2013-2014
 */
public class FreeShift extends Shift {

    private Shift nextShift;

    /**
     * Creates a free shift with an amount of hours, number of work posts and a next shift.
     *
     * @param hours             The amount of hours.
     * @param numberofworkposts The number of work posts.
     * @param nextShift         The next shift.
     */
    protected FreeShift(int hours, int numberofworkposts, Shift nextShift) {
        super(hours, numberofworkposts);
        this.setNextShift(nextShift);
    }

    /**
     * A method to get the next shift of the day.
     *
     * @return the next shift of the day
     */
    protected Shift getNextShift() {
        return this.nextShift;
    }

    /**
     * Method to set the next shift.
     *
     * @param nextShift
     */
    private void setNextShift(Shift nextShift) {
        this.nextShift = nextShift;
    }

    /**
     * A method to check if a number of Timeslot's are available to handle an order.
     *
     * @param timeslot the first slot to check.
     * @return a list of slots if there is room. null if there is no place available.
     */
    private ArrayList<TimeSlot> checkTimeSlots(TimeSlot timeslot) {
        ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
        for (int numberofworkslot = 0; numberofworkslot < this.getNumberOfWorkPosts(); numberofworkslot++) {
            if (timeslot == null | timeslot.workSlotOccupied(numberofworkslot))
                return null;
            timeslots.add(timeslot);
            timeslot = this.getNextTimeSlot(timeslot);
        }
        return timeslots;
    }

    @Override
    protected ArrayList<TimeSlot> canAddOrder(Order order) {
        ArrayList<TimeSlot> timeslots = null;
        LinkedList<TimeSlot> temp = this.getTimeSlots();
        for (int i = 0; i < this.getNumberOfWorkPosts() - 1; i++)
            temp.add(this.getNextShift().getTimeSlots().get(i));
        for (int i = 0; i < temp.size() - (this.getNumberOfWorkPosts() - 1); i++) {
            timeslots = checkTimeSlots(temp.get(i));
            if (timeslots != null)
                break;
        }
        for (int i = 0; i < this.getNumberOfWorkPosts() - 1; i++)
            temp.removeLast();
        return timeslots;
    }

}
