package businessmodel.assemblyline;

import businessmodel.order.Order;

import java.util.ArrayList;

/**
 * Created by michielvandendriessche on 14/05/14.
 */
public class SpecificationBatch2 extends SchedulingAlgorithm {

    protected SpecificationBatch2(AssemblyLineScheduler scheduler) {
        super(scheduler);
    }

    @Override
    protected void scheduleOrder(Order order) {
        ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        for (Shift sh: this.getScheduler().getShifts()){
            timeSlots = sh.canAddOrder(order);
            if(timeSlots!= null){
                sh.addOrderToSlots(order,timeSlots);
                break;
            }
        }
    }
}
