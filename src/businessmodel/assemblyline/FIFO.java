package businessmodel.assemblyline;

import businessmodel.order.Order;

import java.util.ArrayList;

/**
 * Class representing a first come first served algorithm.
 *
 * @author SWOP team 10
 */
public class FIFO extends SchedulingAlgorithm {

    /**
     * Create a first come first serve algorithm with a given AssemblyLine.
     *
     * @param scheduler The assembly line for the algorithm.
     */
    protected FIFO(AssemblyLineScheduler scheduler) {
        super(scheduler);
    }

    @Override
    protected boolean scheduleOrder(Order order) {
        ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
        for (Shift sh : this.getScheduler().getShifts()) {
            timeslots = sh.canAddOrder(order);
            if (timeslots != null) {
                sh.addOrderToSlots(order, timeslots);
                return true;
            }
        }
        return false;
    }

}

