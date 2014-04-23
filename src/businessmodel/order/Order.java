
package businessmodel.order;

import java.util.ArrayList;
import java.util.Locale;

import org.joda.time.DateTime;

import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;

/**
 * A class representing an abstract order.
 * 
 * @author SWOP Team 10 2014
 *
 */
public abstract class Order {

	private User user;

	private DateTime estimatedDeliveryDate;

	private DateTime timestamp;

	private DateTime orderPlacedOnWorkPost;

	private DateTime standardTimeOnAssemblyLine;

	private DateTime completionDate;

	private boolean completed;

	public Order(User user) throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		setUser(user);
	}
	
	public User getUser() {
		return this.user;
	}
	
	// andere naam
	public DateTime getEstimateDate() {
		return this.estimatedDeliveryDate;
	}
	
	public DateTime getTimestamp(){
		return this.timestamp;
	}
	
	// andere naam
	public DateTime getOrder_placed_on_workpost() {
		return this.orderPlacedOnWorkPost;
	}
	
	// andere naam
	public DateTime getStandardtime_on_assemblyline() {
		return this.standardTimeOnAssemblyLine;
	}
	
	// andere naam
	public DateTime getCompletionDate(){
		return this.completionDate;
	}
	
	public boolean isCompleted() {
		return this.completed;
	}

	private void setUser(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) throw new NoClearanceException(user);
		this.user = user;
	}
	
	// andere naam
	public void setEstimateDate(DateTime estimatedDeliveryDate) throws IllegalArgumentException {
		if(estimatedDeliveryDate == null) throw new IllegalArgumentException("Bad estimated delivery date!");
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}
	
	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	// andere naam en 1 vd 2 verwijderen
	public void setPlacedOnWorkpost(DateTime date){
		this.setOrder_placed_on_workpost(date);
	}
	
	// andere naam en 1 vd 2 verwijderen
	public void setOrder_placed_on_workpost(DateTime orderPlacedOnWorkPost) {
		this.orderPlacedOnWorkPost = orderPlacedOnWorkPost;
	}
	
	// andere naam
	public void setStandardtime_on_assemblyline(DateTime standardTimeOnAssemblyLine) {
		this.standardTimeOnAssemblyLine = standardTimeOnAssemblyLine;
	}
	
	// andere naam en 1 vd 2 verwijderen
	public void setCompletionDate(DateTime date){
		this.completionDate = date;
	}
	
	// andere naam en 1 vd 2 verwijderen
	public void updateCompletionTime(DateTime date){
		this.completionDate = date;
	}
	
	public void updateEstimatedDate(int delay) {
		this.getEstimateDate().plusMinutes(delay);		
	}
	
	public void setCompleted() {
		this.completed = true;
	}
	
	public abstract ArrayList<CarOption> getOptions();

	public DateTime getUserEndDate() {
		return null;
	}
	
	@Override
	public String toString() {
		return "user: " + this.user.toString() + ", delivery date= " + this.estimatedDeliveryDate.toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT); 
	}
}
