package businessmodel;

public class EndShift extends Shift {

	private void generateTimeSlots(int hours){
		for(int i = 0;i < hours-1;i++){
			NormalTimeSlot slot = new NormalTimeSlot(3);
			getTimeSlots().add(slot);
		}
		RestrictedTimeSlot slot1 = new RestrictedTimeSlot(3);
		this.getTimeSlots().add(slot1);
	}
}
