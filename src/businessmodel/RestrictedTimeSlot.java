package businessmodel;

public class RestrictedTimeSlot extends TimeSlot{

	public RestrictedTimeSlot(int sizeworkposts){
		super(sizeworkposts);
	}
	
	@Override
	protected boolean IsAvailable(int i){
		return (i < 0 && getOrders()[i] == null);
	} 
}
