package businessmodel.assemblyline;

import businessmodel.order.Order;

import java.util.ArrayList;

/**
 * Class representing an end shift.
 *
 * @author SWOP team 10 2013-2014
 */
public class EndShift extends Shift {

    /**
     * Create an end shift with an amount of hours and work posts.
     *
     * @param hours             The hours of the end shift.
     * @param numberofworkposts The number of work posts of the end shift.
     */
    protected EndShift(int hours, int numberofworkposts) {
        super(hours, numberofworkposts);
    }

    /**
     * A method to check if a number of Timeslots are available to handle an order.
     *
     * @param timeslot the first slot to check.
     * @return a list of slots if there is room. null if there is no place available.
     */
    private ArrayList<TimeSlot> checkTimeSlots(TimeSlot timeslot) {
        ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
        for (int numberofworkslot = 0; numberofworkslot < this.getNumberOfWorkPosts(); numberofworkslot++) {
            if (timeslot.workSlotOccupied(numberofworkslot))
                return null;
            timeslots.add(timeslot);
            timeslot = this.getNextTimeSlot(timeslot);
        }
        return timeslots;
    }

    @Override
    protected ArrayList<TimeSlot> canAddOrder(Order order) {
        ArrayList<TimeSlot> timeslots;
        for (int i = 0; i < this.getTimeSlots().size() - (this.getNumberOfWorkPosts() - 1); i++) {
            TimeSlot slot = this.getTimeSlots().get(i);
            timeslots = checkTimeSlots(slot);
            if (timeslots != null)
                return timeslots;
        }
        return null;
    }

}
