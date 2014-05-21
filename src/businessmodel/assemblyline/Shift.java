package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

/**
 * A class that represents a Shift. A shift has eight Timeslot's.
 * 
 * @author SWOP 2014 team 10
 * 
 */
public abstract class Shift {

	/**
	 * A list that holds all the TimeSlot's of this shift.
	 */
	private LinkedList<TimeSlot> timeslots;

	/**
	 * A variable that holds the number of WorkPost's that a AssymblyLine has.
	 */
	private int numberofworkposts;

	/**
	 * A constructor for the class Shift.
	 * @param	hours
	 * 			the number of hours that a shift consist of.
	 * @param 	numberofworkposts
	 * 			the number of WorkPost's that an AssemblyLine has.
	 */
	protected Shift(int hours, int numberofworkposts){
		this.setNumberOfWorkPosts(numberofworkposts);
		generateTimeSlots(hours);
	}

	/**
	 * A method to check if an order can be added to this shift.
	 * @param	order
	 * 			the order that needs to be added.
	 * @return	returns a list of TimeSlot's if the order can be scheduled.
	 * 			null if the order cannot be added.
	 */
	protected ArrayList<TimeSlot> canAddOrder(Order order){
		return null;
	}

	/**
	 * A method to expand the shift. A new TimeSlot will be created and added to the shift.
	 */
	protected void addTimeSlot(){
		TimeSlot slot = new TimeSlot(this.getNumberOfWorkPosts());
		this.getTimeSlots().add(slot);
	}

	/**
	 * A method to remove the last TimeSlot of this Shift.
	 * @return	the last order that needs to be rescheduled.
	 */
	protected Order removeLastTimeSlot(){
		Order temp = this.getTimeSlots().getLast().getLastOrderOfLastWorkSLot();
        for(TimeSlot slot : this.timeslots){
            for(WorkSlot workslot: slot.getWorkSlots()){
                if(workslot.getOrder() == temp)
                    workslot.addOrder(null);
            }
        }
		this.getTimeSlots().removeLast();
		return temp;
	}

	/**
	 * Method to add order to time slots
	 * 
	 * @param order
	 * @param timeslots
	 */
	protected void addOrderToSlots(Order order, ArrayList<TimeSlot> timeslots){
		int count = 0;
		for(TimeSlot timeslot: timeslots){
			timeslot.addOrderToWorkSlot(order, count++);
		}
	}

	/**
	 * A method to get the TimeSlot that comes after the given TimeSlot.
	 * @param 	timeslot
	 * 			the current TimeSlot.
	 * @return	the TimeSlot that comes after the given TimeSlot.
	 */
	protected TimeSlot getNextTimeSlot(TimeSlot timeslot){
		int index = this.getTimeSlots().indexOf(timeslot);
		if(index + 1 >= this.getTimeSlots().size()|| this.getTimeSlots().size() < 0)
			return null;
		else
			return this.getTimeSlots().get(index+1);
	}

	/**
	 * A method to get the next order that needs to be scheduled on the AssemblyLine.
	 */
	protected Order getNextOrderForAssemblyLine() {
		TimeSlot newtimeslot = this.getTimeSlots().pollFirst();
		return newtimeslot.getNextOrder();
	}

	/**
	 * A method to get the TimeSlots of this Shift.
	 * @return the TimeSlot's of this shift.
	 */
	// TODO protected maken
	public LinkedList<TimeSlot> getTimeSlots() {
		return timeslots;
	}

	/**
	 * A method to get the number of WorkPost's.
	 * @return the number of WorkPost's.
	 */
	protected int getNumberOfWorkPosts() {
		return this.numberofworkposts;
	}

	/**
	 * A method that generates the TimeSlot's of this Shift. 
	 * 
	 * @param	hours
	 * 			The amount of TimeSlot's that need to bee created.
	 */
	private void generateTimeSlots(int hours){
		this.timeslots = new LinkedList<TimeSlot>();
		for(int i = 0;i < hours;i++){
			TimeSlot slot = new TimeSlot(this.getNumberOfWorkPosts());
			this.getTimeSlots().add(slot);
		}
	}

	/**
	 * A method to set the number of WorkPost's to the given number.
	 * @param	numberofworkposts
	 * 			the new number of WorkPost's of this Shift.
	 */
	private void setNumberOfWorkPosts(int numberofworkposts) {
		this.numberofworkposts = numberofworkposts;
	}
}
