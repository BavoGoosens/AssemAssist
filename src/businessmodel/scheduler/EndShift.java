package businessmodel.scheduler;

public class EndShift extends Shift {

	public EndShift(int hours) {
		super(hours);
	}
	
	@Override
	// dummy method
	protected void removeLastTimeSlot(){
		this.getTimeSlots().peekLast().terminate();
		this.getTimeSlots().removeLast();
	}
}
